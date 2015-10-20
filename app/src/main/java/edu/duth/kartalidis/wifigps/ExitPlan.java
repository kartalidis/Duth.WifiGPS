package edu.duth.kartalidis.wifigps;


/**
 * Created by Nikolas on 9/4/2015.
 */
public class ExitPlan {



    public static int[] wayOut(int xcoord, int ycoord) {

        int[][] start = new int [50][50];
        int[][] finish = new int [50][50];
        int[] trace = new int [2500];

        int i, j, x, y, n=0, s=0, w=0, e=0, nw=0, ne=0, sw=0, se=0, c=0, ln=0, ls=0, lw=0, le=0, lnw=0, lne=0, lsw=0, lse=0, lc=0;
        int xout = 24;
        int yout = 0;
        int xin1 = 14;
        int yin1 = 1;
        int xin2 = 34;
        int yin2 = 1;
        int xin3 = 14;
        int yin3 = 35;
        int xin4 = 34;
        int yin4 = 35;

        for (i=0; i<=49; i++){
            for (j=0; j<=49; j++){
                start[i][j] = 0;
                finish[i][j] = 0;
                trace[i+50*j] = 0;
            }
        }

        start[xcoord][ycoord] = 1;
        trace[xcoord + 50*ycoord] = 1;

        //perifra3h
        for (i=0; i<=49; i++){
            start[i][0] = 2;
            start[i][49] = 2;
        }

        for (j=1; j<=48; j++){
            start[0][j] = 2;
            start[49][j] = 2;
        }



        //dhmiourgia dwmatiwn

        //dwmatio 1
        for (i=0; i<=14; i++){
            start[i][14] = 2;
        }

        for (j=0; j<=14; j++){
            start[14][j] = 2;
        }

        //dwmatio 2
        for (i=34; i<=48; i++){
            start[i][14] = 2;
        }

        for (j=0; j<=14; j++){
            start[34][j] = 2;
        }

        //dwmatio 3
        for (i=0; i<=14; i++){
            start[i][34] = 2;
        }

        for (j=34; j<=48; j++){
            start[14][j] = 2;
        }

        //dwmatio 4
        for (i=34; i<=48; i++){
            start[i][34] = 2;
        }

        for (j=34; j<=48; j++){
            start[34][j] = 2;
        }

        //pules
        start[xout][yout] = 3;
        start[xin1][yin1] = 4;
        start[xin2][yin2] = 4;
        start[xin3][yin3] = 4;
        start[xin4][yin4] = 4;

        finish = start;


        for (int t = 1; t <= 60; t++) {

            for (i = 0; i < 49; i++) {

                for (j = 0; j < 49; j++) {
                    if (j > 0 & j < 14 & i > 0 & i < 14) {
                        x = xin1;
                        y = yin1;
                    } else if (j > 0 & j < 14 & i > 34 & i < 49) {
                        x = xin2;
                        y = yin2;
                    } else if (j > 34 & j < 49 & i > 0 & i < 14) {
                        x = xin3;
                        y = yin3;
                    } else if (j > 34 & j < 49 & i > 34 & i < 49) {
                        x = xin4;
                        y = yin4;
                    } else {
                        x = xout;
                        y = yout;
                    }

                    //orismos kupselidwn kai apostasewn apo thn pulh
                    if (j != 0 & i != 0 & j != 49 & i != 49) {
                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        nw = start[i - 1][j - 1];
                        lnw = (Math.abs(x - (i - 1)) + Math.abs(y - (j - 1)));
                        ne = start[i + 1][j - 1];
                        lne = (Math.abs(x - (i + 1)) + Math.abs(y - (j - 1)));
                        sw = start[i - 1][j + 1];
                        lsw = (Math.abs(x - (i - 1)) + Math.abs(y - (j + 1)));
                        se = start[i + 1][j + 1];
                        lse = (Math.abs(x - (i + 1)) + Math.abs(y - (j + 1)));
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));
                    }

                    //orismos parametrwn stis oriakes sun8hkes
                    if (i == 0 & j == 0) {

                        n = 0;
                        ln = 1000;
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = 0;
                        lw = 1000;
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        nw = 0;
                        lnw = 1000;
                        ne = 0;
                        lne = 1000;
                        sw = 0;
                        lsw = 1000;
                        se = start[i + 1][j + 1];
                        lse = (Math.abs(x - (i + 1)) + Math.abs(y - (j + 1)));
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));
                    }

                    if (i == 0 & j == 49) {

                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        w = 0;
                        lw = 1000;
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        s = 0;
                        ls = 1000;
                        nw = 0;
                        lnw = 1000;
                        ne = start[i + 1][j - 1];
                        lne = (Math.abs(x - (i + 1)) + Math.abs(y - (j - 1)));
                        sw = 0;
                        lsw = 1000;
                        se = 0;
                        lse = 1000;
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (i == 49 & j == 0) {

                        n = 0;
                        ln = 1000;
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        e = 0;
                        le = 1000;
                        nw = 0;
                        lnw = 1000;
                        ne = 0;
                        lne = 1000;
                        sw = start[i - 1][j + 1];
                        lsw = (Math.abs(x - (i - 1)) + Math.abs(y - (j + 1)));
                        se = 0;
                        lse = 1000;
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (i == 49 & j == 49) {

                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        e = 0;
                        le = 1000;
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        s = 0;
                        ls = 1000;
                        ne = 0;
                        lne = 1000;
                        nw = start[i - 1][j - 1];
                        lnw = (Math.abs(x - (i - 1)) + Math.abs(y - (j - 1)));
                        sw = 0;
                        lsw = 1000;
                        se = 0;
                        lse = 1000;
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (i == 0 & j != 0 & j != 49) {

                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = 0;
                        lw = 1000;
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        nw = 0;
                        lnw = 1000;
                        ne = start[i + 1][j - 1];
                        lne = (Math.abs(x - (i + 1)) + Math.abs(y - (j - 1)));
                        sw = 0;
                        lsw = 1000;
                        se = start[i + 1][j + 1];
                        lse = (Math.abs(x - (i + 1)) + Math.abs(y - (j + 1)));
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (i == 49 & j != 0 & j != 49) {

                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        e = 0;
                        le = 1000;
                        nw = start[i - 1][j - 1];
                        lnw = (Math.abs(x - (i - 1)) + Math.abs(y - (j - 1)));
                        ne = 0;
                        lne = 1000;
                        sw = start[i - 1][j + 1];
                        lsw = (Math.abs(x - (i - 1)) + Math.abs(y - (j + 1)));
                        se = 0;
                        lse = 1000;
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (j == 0 & i != 0 & i != 49) {

                        n = 0;
                        ln = 1000;
                        s = start[i][j + 1];
                        ls = (Math.abs(x - i) + Math.abs(y - (j + 1)));
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        nw = 0;
                        lnw = 1000;
                        ne = 0;
                        lne = 1000;
                        sw = start[i - 1][j + 1];
                        lsw = (Math.abs(x - (i - 1)) + Math.abs(y - (j + 1)));
                        se = start[i + 1][j + 1];
                        lse = (Math.abs(x - (i + 1)) + Math.abs(y - (j + 1)));
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    if (j == 49 & i != 0 & i != 49) {

                        n = start[i][j - 1];
                        ln = (Math.abs(x - i)) + Math.abs(y - (j - 1));
                        s = 0;
                        ls = 1000;
                        w = start[i - 1][j];
                        lw = (Math.abs(x - (i - 1)) + Math.abs(y - j));
                        e = start[i + 1][j];
                        le = (Math.abs(x - (i + 1)) + Math.abs(y - j));
                        nw = start[i - 1][j - 1];
                        lnw = (Math.abs(x - (i - 1)) + Math.abs(y - (j - 1)));
                        ne = start[i + 1][j - 1];
                        lne = (Math.abs(x - (i + 1)) + Math.abs(y - (j - 1)));
                        sw = 0;
                        lsw = 1000;
                        se = 0;
                        lse = 1000;
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));

                    }

                    //kanonas

                    //empodia
                    if (c == 2) {
                        finish[i][j] = 2;
                    }

                    //pules
                    if (c == 3) {
                        finish[i][j] = 3;
                    }

                    if (c == 4) {
                        finish[i][j] = 4;
                    }

                    //an8rwpoi
                    if (c == 1) {
                        trace[i+50*j] = 1;

                        //e3odos apo pulh
                        if ((lc == 1 | lnw == 0 | lne == 0 | lsw == 0 | lse == 0) & (n == 3 | ne == 3 | se == 3 | nw == 3 | sw == 3)) {

                            finish[i][j] = 1;

                            //e3odos apo eswterikh pulh
                        } else if ((((i > 0) & (i < 14) & (j > 0) & (j < 14)) | ((i > 0) & (i < 14) & (j > 34) & (j < 49))) & (e == 4) & (start[i + 2][j] == 0)) {


                            finish[i][j] = 0;
                            finish[i + 2][j] = 1;

                        } else if ((((i > 0) & (i < 14) & (j > 0) & (j < 14)) | ((i > 0) & (i < 14) & (j > 34) & (j < 49))) & (ne == 4) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((((i > 34) & (i < 49) & (j > 0) & (j < 14)) | ((i > 34) & (i < 49) & (j > 34) & (j < 49))) & (w == 4) & (start[i - 2][j] == 0)) {

                            finish[i][j] = 0;
                            finish[i - 2][j] = 1;

                        } else if ((((i > 34) & (i < 49) & (j > 0) & (j < 14)) | ((i > 34) & (i < 49) & (j > 34) & (j < 49))) & (nw == 4) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                            //apofugh anw empodiwn
                        } else if ((n == 2) & (ls < ln) & (start[i][j + 1] == 0)) {

                            finish[i][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lsw < ln) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lse < ln) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lnw <= le) & (lnw <= lw) & (lnw <= lne) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lne <= le) & (lne <= lw) & (lne <= lnw) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (le <= lw) & (start[i + 1][j] == 0)) {

                            finish[i + 1][j] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lw <= le) & (start[i - 1][j] == 0)) {

                            finish[i - 1][j] = 1;
                            finish[i][j] = 0;

                            //apofugh katw empodiwn
                        } else if ((s == 2) & (ln < ls) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((s == 2) & (lnw < ls) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((s == 2) & (lne < ln) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((s == 2) & (lsw <= le) & (lsw <= lw) & (lsw <= lse) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((s == 2) & (lse <= le) & (lse <= lw) & (lse <= lsw) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((s == 2) & (le <= lw) & (start[i + 1][j] == 0)) {

                            finish[i + 1][j] = 1;
                            finish[i][j] = 0;

                        } else if ((n == 2) & (lw <= le) & (start[i - 1][j] == 0)) {

                            finish[i - 1][j] = 1;
                            finish[i][j] = 0;

                            //apofugh de3iwn empodiwn
                        } else if ((e == 2) & (lw < le) & (start[i - 1][j] == 0)) {

                            finish[i - 1][j] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (lsw < le) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (lnw < le) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (lne <= ln) & (lne <= ls) & (lne <= lse) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (lse <= ln) & (lse <= ls) & (lse <= lne) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (ln <= ls) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((e == 2) & (ls <= ln) & (start[i][j + 1] == 0)) {

                            finish[i][j + 1] = 1;
                            finish[i][j] = 0;

                            //apofugh aristerwn empodiwn
                        } else if ((w == 2) & (le < lw) & (start[i + 1][j] == 0)) {

                            finish[i + 1][j] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (lne < lw) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (lse < lw) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (lnw <= ln) & (lnw <= ls) & (lnw <= lsw) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (lsw <= ln) & (lsw <= ls) & (lsw <= lnw) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (ln <= ls) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if ((w == 2) & (ls <= ln) & (start[i][j + 1] == 0)) {

                            finish[i][j + 1] = 1;
                            finish[i][j] = 0;

                            //apofugh gwniakwn empodiwn
                        } else if (((ne == 2) | (nw == 2)) & (ln <= ls) & (n == 0) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if (((ne == 2) | (nw == 2)) & (ls <= ln) & (s == 0) & (start[i][j + 1] == 0)) {

                            finish[i][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if (((se == 2) | (sw == 2)) & (ls <= ln) & (s == 0) & (start[i][j + 1] == 0)) {

                            finish[i][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if (((se == 2) | (sw == 2)) & (ln <= ls) & (n == 0) & (start[i][j - 1] == 0)) {

                            finish[i][j - 1] = 1;
                            finish[i][j] = 0;

                            //euresh taxuterhs diadromhs k kinhsh me elegxo an h epomenh kupselida einai eleu8erh
                        } else if (((ls < ln) & (ls < le) & (ls < lw) & (ls < lnw) & (ls < lne) & (ls < lsw) & (ls < lse)) & (start[i][j+1] == 0)) {

                            finish[i][j+1] = 1;
                            finish[i][j] = 0;

                        } else if (((lw < ln) & (lw < le) & (lw < ls) & (lw < lnw) & (lw < lne) & (lw < lsw) & (lw < lse)) & (start[i-1][j] == 0)) {

                            finish[i-1][j] = 1;
                            finish[i][j] = 0;

                        } else if (((le < ln) & (le < ls) & (le < lw) & (le < lnw) & (le < lne) & (le < lsw) & (le < lse)) & (start[i+1][j] == 0)) {

                            finish[i+1][j] = 1;
                            finish[i][j] = 0;

                        } else if (((ln < ls) & (ln < le) & (ln < lw) & (ln < lnw) & (ln < lne) & (ln < lsw) & (ln < lse)) & (start[i][j-1] == 0)) {

                            finish[i][j-1] = 1;
                            finish[i][j] = 0;

                        } else if (((lnw < ln) & (lnw < le) & (lnw < lw) & (lnw < ls) & (lnw < lne) & (lnw < lsw) & (lnw < lse)) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lne < ln) & (lne < le) & (lne < lw) & (lne < ls) & (lne < lnw) & (lne < lsw) & (lne < lse)) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lsw < ln) & (lsw < le) & (lsw < lw) & (lsw < ls) & (lsw < lne) & (lsw < lnw) & (lsw < lse)) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lse < ln) & (lse < le) & (lse < lw) & (lse < ls) & (lse < lne) & (lse < lnw) & (lse < lsw)) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                            //euresh deuterhs grhgoroterhs diadromhs
                        } else if (((ls <= ln) & (ls <= le) & (ls <= lw) & (ls <= lnw) & (ls <= lne) & (ls <= lsw) & (ls <= lse)) & (s == 0) & (start[i][j+1] == 0)) {

                            finish[i][j+1] = 1;
                            finish[i][j] = 0;

                        } else if (((lw <= ln) & (lw <= le) & (lw <= ls) & (lw <= lnw) & (lw <= lne) & (lw <= lsw) & (lw <= lse)) & (w == 0) & (start[i-1][j] == 0)) {

                            finish[i-1][j] = 1;
                            finish[i][j] = 0;

                        } else if (((le <= ln) & (le <= ls) & (le <= lw) & (le <= lnw) & (le <= lne) & (le <= lsw) & (le <= lse)) & (e == 0) & (start[i+1][j] == 0)) {

                            finish[i+1][j] = 1;
                            finish[i][j] = 0;

                        } else if (((ln <= ls) & (ln <= le) & (ln <= lw) & (ln <= lnw) & (ln <= lne) & (ln <= lsw) & (ln <= lse)) & (n == 0) & (start[i][j-1] == 0)) {

                            finish[i][j-1] = 1;
                            finish[i][j] = 0;

                        } else if (((lnw <= ln) & (lnw <= le) & (lnw <= lw) & (lnw <= ls) & (lnw <= lne) & (lnw <= lsw) & (lnw <= lse)) & (nw == 0) & (start[i - 1][j - 1] == 0)) {

                            finish[i - 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lne <= ln) & (lne <= le) & (lne <= lw) & (lne <= ls) & (lne <= lnw) & (lne <= lsw) & (lne <= lse)) & (ne == 0) & (start[i + 1][j - 1] == 0)) {

                            finish[i + 1][j - 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lsw <= ln) & (lsw <= le) & (lsw <= lw) & (lsw <= ls) & (lsw <= lne) & (lsw <= lnw) & (lsw <= lse)) & (sw == 0) & (start[i - 1][j + 1] == 0)) {

                            finish[i - 1][j + 1] = 1;
                            finish[i][j] = 0;

                        } else if (((lse <= ln) & (lse <= le) & (lse <= lw) & (lse <= ls) & (lse <= lne) & (lse <= lnw) & (lse <= lsw)) & (se == 0) & (start[i + 1][j + 1] == 0)) {

                            finish[i + 1][j + 1] = 1;
                            finish[i][j] = 0;

                        }

                    }

                }


            }

            start = finish;

        }
        return trace;
    }
}
