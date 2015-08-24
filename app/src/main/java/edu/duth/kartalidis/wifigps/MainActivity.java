package edu.duth.kartalidis.wifigps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity implements SensorEventListener {

    Activity activity = this;
    final int ACTIVITY_CHOOSE_FILE = 1;
    public static float currentPosition = 0;
    private boolean running = true;
    private int correction;
    private float[] magData = new float[3];
    private float[] accData = new float[3];
    private float[] mR = new float[16];
    private float[] mI = new float[16];
    private float[] mOrientation = new float[3];
    private SensorManager sensorManager = null;
    private Context globalcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout entire_view = (RelativeLayout) findViewById(R.id.entire_view);
        entire_view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                initialPosition((int) event.getX(), (int) event.getY());
                return true;
            }
        });

        final Button startnavigation = (Button) findViewById(R.id.startNavigation);
        startnavigation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView)findViewById(R.id.pointer);
                RelativeLayout.LayoutParams layoutparams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                int x = layoutparams.leftMargin;
                int y = layoutparams.topMargin;

                if(x==0 && y==0) {
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    x = size.x/2;
                    y = size.y/2;
                }

                SharedPreferences sharedPreferences = PreferenceManager .getDefaultSharedPreferences(getBaseContext());
                Editor editor = sharedPreferences.edit();
                editor.putInt("posX", x);
                editor.putInt("posY", y);



                editor.putFloat("startCompass", currentPosition);
                editor.commit();


                startNavigation();

            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        findViewById(R.id.pointer).setVisibility(View.GONE);
        findViewById(R.id.startNavigation).setVisibility(View.GONE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mapName = sharedPreferences.getString("imageUri", "false");

        if (mapName.equals("false")) {
            mapChooser();
        } else {
            try {
                loadSettings();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void startNavigation() {

        findViewById(R.id.startNavigation).setVisibility(View.GONE);

        Thread thread = new Thread() {

            public void run() {
                int i = 0;

                WiFiScanner.WiFiScanner(activity);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(globalcontext);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("signalCounter", 0);
                editor.commit();

                while(running) {
                    if(i%2 == 0) {
                        activity.runOnUiThread(new Runnable() {
                            public void run() {

                                findViewById(R.id.pointer).setVisibility(View.GONE);
                            }
                        });
                    } else {
                        activity.runOnUiThread(new Runnable() {
                            public void run() {
                                int signalCounter = WiFiScanner.getCounter();

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(globalcontext);
                                int oldCounter = sharedPreferences.getInt("signalCounter", 0);



                                if(signalCounter > oldCounter) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("signalCounter", signalCounter);
                                    editor.commit();

                                    ArrayList<WiFiData> oldScan = WiFiScanner.getOldWifiList();
                                    ArrayList<WiFiData> newScan = WiFiScanner.getWifiList();
                                    ArrayList<WiFiData> intersection = new ArrayList<WiFiData>();

                                    for(int m=0;m<oldScan.size();m++) {

                                        if(oldScan.get(m).getRSS() < -64) {
                                            continue;
                                        }

                                        for(int n=0;n<newScan.size();n++) {
                                            if(oldScan.get(m).getBSSID().equals(newScan.get(n).getBSSID())) {
                                                intersection.add(oldScan.get(m));
                                                intersection.add(newScan.get(n));
                                            }
                                        }
                                    }

                                    int totalValues = 0;
                                    double summarizedDistance = 0;

                                    int p = 0;
                                    while(p < intersection.size()) {

                                        summarizedDistance += Position.getNewDistance(intersection.get(p + 1).getRSS(), intersection.get(p).getRSS(), intersection.get(p).getFrequency());


                                        totalValues++;
                                        p=p+2;
                                    }

                                    int meanMovedDistanceInPixel = 0;
                                    float angle = 0;

                                    if(totalValues > 0) {
                                        double meanMovedDistance = summarizedDistance/totalValues;

                                        angle = currentPosition - sharedPreferences.getFloat("startCompass", (float)0.0);
                                        if(angle < 0) {
                                            angle = 360+angle;
                                        }


                                        meanMovedDistanceInPixel = (1080/5) * (int)meanMovedDistance;


                                        int posX = sharedPreferences.getInt("posX", 0);
                                        int posY = sharedPreferences.getInt("posY", 0);


                                        if(meanMovedDistanceInPixel != 0) {
                                            int[] newCoordinates = new int[2];
                                            newCoordinates = Position.getNewPosition(posX, posY, angle, meanMovedDistanceInPixel);

                                            int newX = newCoordinates[0];
                                            int newY = newCoordinates[1];

                                            editor.putInt("posX", newX);
                                            editor.putInt("posY", newY);
                                            editor.commit();

                                            setPosition(newX, newY);
                                        } else {
                                            setPosition(posX, posY);
                                        }

                                    }





                                    String values = "";

                                    values += "startangle " + String.valueOf(Math.abs(sharedPreferences.getFloat("startCompass", (float)0.0))) + "\n";
                                    values += "currentposition " + String.valueOf(currentPosition) + "\n";
                                    values += "angle " + String.valueOf(angle) + "\n";
                                    values += String.valueOf(meanMovedDistanceInPixel) + "\n";

                                    for(int j=0;j<oldScan.size();j++) {
                                        values += oldScan.get(j).getBSSID()+ ", " + oldScan.get(j).getRSS()+"\n";
                                    }

                                    values += "-----------\n";

                                    for(int j=0;j<newScan.size();j++) {
                                        double distance = Position.getDistance(newScan.get(j).getRSS(),newScan.get(j).getFrequency());
                                        values += newScan.get(j).getFrequency()+ ", " + newScan.get(j).getRSS()+ ", " + distance + "\n";
                                    }

                                    Toast.makeText(getApplicationContext(), String.valueOf(values), Toast.LENGTH_SHORT).show();

                                }

                                findViewById(R.id.pointer).setVisibility(View.VISIBLE);
                            }
                        });

                    }




                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }

        };

        thread.start();

    }

    public void initialPosition(int x, int y) {

        setPosition(x, y);

    }

    public void setPosition(int x, int y) {

        ImageView pointer = (ImageView)findViewById(R.id.pointer);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(pointer.getLayoutParams());
        layoutParams.setMargins(x, y, 0, 0);

        SharedPreferences sharedPreferences = PreferenceManager .getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("posX", x);
        editor.putInt("posY", y);
        editor.commit();

        pointer.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sensor gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor msensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor rsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, rsensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, gsensor, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, msensor, SensorManager.SENSOR_DELAY_GAME);
    }



    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void mapChooser () {

        final ImageView mapback = (ImageView) findViewById(R.id.map);
        mapback.setBackgroundResource(R.drawable.blank);

        Button button = (Button) this.findViewById(R.id.mapSelector);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a Map"), 1);
                setInitialPosition();
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int type = sensorEvent.sensor.getType();
        float[] data;
        if (type == Sensor.TYPE_ACCELEROMETER) {
            correction = (int)sensorEvent.values[0];
            data = accData;
        } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            data = magData;
        } else {
            return;
        }

        for (int i=0 ; i<3 ; i++)
            data[i] = sensorEvent.values[i];

        SensorManager.getRotationMatrix(mR, mI, accData, magData);
        SensorManager.getOrientation(mR,mOrientation);

        final float rad2deg = (float)(180.0f/Math.PI);

        if(Math.round(mOrientation[0]*rad2deg) < 0) {
            currentPosition = 360+Math.round(mOrientation[0]*rad2deg);
        } else {
            currentPosition = Math.round(mOrientation[0]*rad2deg);
        }

        if(currentPosition + correction*9 > 360) {
            currentPosition = currentPosition + correction*9 - 360;
        } else {
            currentPosition = currentPosition + correction*9;
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void hideUIElements() {
        findViewById(R.id.mapSelector).setVisibility(View.GONE);
        findViewById(R.id.startNavigation).setVisibility(View.GONE);
    }

    public void loadSettings() throws FileNotFoundException, IOException {

        hideUIElements();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String selectedimg = sharedPreferences.getString("imageUri", "loadGrid");

        if(!selectedimg.equals("loadGrid")) {
            final ImageView mapback = (ImageView) findViewById(R.id.map);
            mapback.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(selectedimg)));
        }

        setInitialPosition();
    }

    public void setInitialPosition() {
        globalcontext = this;
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Touch the map to set your current position. To calibrate the compass the right way, rotate and adjust your device relative to the map.");
        alertDialog.setTitle("Current position");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                findViewById(R.id.startNavigation).setVisibility(View.VISIBLE);
                findViewById(R.id.pointer).setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        alertDialog.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_CHOOSE_FILE: {
                if (resultCode == RESULT_OK) {

                    final ImageView mapback = (ImageView) findViewById(R.id.map);
                    Uri selectedimg = data.getData();
                    try {
                        mapback.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
//                        Bitmap btmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg);
//                        int height = btmp.getHeight();
//                        int width = btmp.getWidth();
//                        int[] pixels;
//                        pixels = new int[height * width];
//                        btmp.getPixels(pixels, 0, width, 1, 1, width - 1, height -1);
//                        int i = 1;


                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("imageUri", selectedimg.toString());
                        editor.commit();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    hideUIElements();

                }
            }
        }
    }
}
