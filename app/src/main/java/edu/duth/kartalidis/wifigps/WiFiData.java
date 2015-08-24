package edu.duth.kartalidis.wifigps;

/**
 * Created by Nikolas on 26/11/2014.
 */
public class WiFiData {

    private int signalStrength;
    private String SSID;
    private int frequency;
    private String BSSID;

    public WiFiData(int signal, String id, int freq, String bid) {
        signalStrength = signal;
        SSID = id;
        frequency = freq;
        BSSID = bid;
    }

    public int getRSS() {
        return signalStrength;
    }

    public String getSSID() {
        return SSID;
    }

    public String getBSSID() {
        return BSSID;
    }

    public int getFrequency() {
        return frequency;
    }

}
