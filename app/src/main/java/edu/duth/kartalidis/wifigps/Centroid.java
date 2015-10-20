package edu.duth.kartalidis.wifigps;

/**
 * Created by Nikolas on 20/8/2015.
 */
public class Centroid {

    public static double weight (double sig1, double sig2, double sig3, double sig4) {
        double exp1 = sig1/10;
        double exp2 = sig2/10;
        double exp3 = sig3/10;
        double exp4 = sig4/10;
        double p1 = Math.sqrt(Math.pow(10,exp1));
        double p2 = Math.sqrt(Math.pow(10,exp2));
        double p3 = Math.sqrt(Math.pow(10,exp3));
        double p4 = Math.sqrt(Math.pow(10,exp4));
        double denom = p1+p2+p3+p4;
        return p1/denom;
    }

    public static double weightEnh (double w, int n) {

        double exp = 2*w;
        return w*Math.pow(n,exp);

    }


}
