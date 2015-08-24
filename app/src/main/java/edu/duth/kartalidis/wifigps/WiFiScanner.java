package edu.duth.kartalidis.wifigps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolas on 25/11/2014.
 */
public class WiFiScanner {

    static BroadcastReceiver wifiScanReceiver;
    static Context globalcontext = null;
    private static  ArrayList<WiFiData> wifiList = new ArrayList<WiFiData>();
    private static ArrayList<WiFiData> oldWifiList = new ArrayList<WiFiData>();
    private static int counter = 0;

    public static void WiFiScanner(Context context) {

        globalcontext = context;

        final WifiManager wifimanager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

        wifiList = new ArrayList<WiFiData>();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

        if (!wifimanager.isWifiEnabled()) {
            wifimanager.setWifiEnabled(true);

            try {
                Thread.sleep(2000);
            }catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        wifiScanReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {



                List<ScanResult> scanresult = wifimanager.getScanResults();

                oldWifiList = wifiList;
                wifiList = new ArrayList<WiFiData>();

                for (int i = 0; i < scanresult.size(); i++) {
                    WiFiData wifidata = new WiFiData(scanresult.get(i).level, scanresult.get(i).SSID, scanresult.get(i).frequency, scanresult.get(i).BSSID);
                    wifiList.add(wifidata);
                }

                counter++;
                wifimanager.startScan();

            }

        };

        globalcontext.registerReceiver(wifiScanReceiver, intentFilter);
        wifimanager.startScan();


    }

    public static int getCounter() {
        return counter;
    }

    public static ArrayList<WiFiData> getWifiList() {
        return wifiList;
    }

    public static ArrayList<WiFiData> getOldWifiList() {
        return oldWifiList;
    }

}
