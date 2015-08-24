package edu.duth.kartalidis.wifigps;

/**
 * Created by Nikolas on 27/11/2014.
 */
public class Position {

    public static double getDistance (double signalStrength, double frequency) {
        double exp = (27.55- 20 * Math.log10(frequency) -signalStrength) / 20;
        return Math.pow(10.0, exp);
    }

    public static double getNewDistance(double newRSS, double oldRSS, double frequency) {
        double expOld = (27.55 - 20 * Math.log10(frequency) - oldRSS) / 20;
        double expNew = (27.55 - 20 * Math.log10(frequency) - newRSS) / 20;
        return Math.abs(Math.pow(10.0, expNew) - Math.pow(10.0, expOld));

    }

    public static double getEstimatedMovement(double currentDistance, double previouseDistance) {
        double upperBound = currentDistance+previouseDistance;
        double lowerBound = Math.abs(previouseDistance - currentDistance);

        if(Math.max(previouseDistance, currentDistance)-Math.min(previouseDistance, currentDistance) < 1.5) {
            return 0;
        } else {
            return (upperBound+lowerBound)/2;
        }
    }

    public static int[] getNewPosition(int currentX, int currentY, float angle, double length) {
        int[] coordinates = new int[2];

        coordinates[0] = (int)(currentX+Math.cos(Math.toRadians(Math.abs(90-angle)))*length);
        coordinates[1] = (int)(currentY+Math.sin(Math.toRadians(Math.abs(90-angle)))*length);

        return coordinates;
    }

}
