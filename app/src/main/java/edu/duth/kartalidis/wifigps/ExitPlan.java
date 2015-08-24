package edu.duth.kartalidis.wifigps;


/**
 * Created by Nikolas on 9/4/2015.
 */
public class ExitPlan {

    private int i, j, x, y, n, s, w, e, nw, ne, sw, se, c, ln, ls, lw, le, lnw, lne, lsw, lse, lc;
    private int xout = 240;
    private int yout = 1;
    private int xin1 = 120;
    private int yin1 = 100;
    private int xin2 = 120;
    private int yin2 = 700;
    private int xin3 = 359;
    private int yin3 = 100;
    private int xin4 = 359;
    private int yin4 = 700;

    int[][] start = new int [480][800];
    int[][] finish = new int [480][800];

    public void setStart(int[][] start) {

        //perifra3h
        for (j=0; j<798; j++){
            start[0][j+1] = 2;
            start[479][j+1] = 2;
        }

        for (i=0; i<478; i++){
            start[i+1][0] = 2;
            start[i+1][799] = 2;
        }

        start[0][0] = 2;
        start[0][799] = 2;
        start[479][0] = 2;
        start[479][799] = 2;

        //dhmiourgia dwmatiwn

        //dwmatio 1
        for (i=0; i<120; i++){
            start[i+1][199] = 2;
        }

        for (j=0; j<200; j++){
            start[120][j+1] = 2;
        }

        //dwmatio 2
        for (i=0; i<120; i++){
            start[i+1][599] = 2;
        }

        for (j=598; j<797; j++){
            start[120][j+1] = 2;
        }

        //dwmatio 3
        for (i=358; i<477; i++){
            start[i+1][199] = 2;
        }

        for (j=0; j<200; j++){
            start[358][j+1] = 2;
        }

        //dwmatio 4
        for (i=358; i<477; i++){
            start[i+1][599] = 2;
        }

        for (j=598; j<797; j++){
            start[358][j+1] = 2;
        }

        //pules
        start[xout][yout] = 3;
        start[xin1][yin1] = 4;
        start[xin2][yin2] = 4;
        start[xin3][yin3] = 4;
        start[xin4][yin4] = 4;
    }

    public void wayOut() {

        for (int t = 1; t <= 60; t++){

            for (i = 0; i < 480; i++) {

                for (j = 0; j < 800; j++) {
                    if (i > 0 & i < 120 & j > 0 & j < 200) {
                        x = xin1;
                        y = yin1;
                    } else if (i > 0 & i < 120 & j > 599 & j < 799) {
                        x = xin2;
                        y = yin2;
                    } else if (i > 359 & i < 479 & j > 0 & j < 200) {
                        x = xin3;
                        y = yin3;
                    } else if (i > 359 & i < 479 & j > 599 & j < 799){
                        x = xin4;
                        y = yin4;
                    } else {
                        x = xout;
                        y = yout;
                    }

                    //orismos kupselidwn kai apostasewn apo thn pulh
                    if (i != 0 & i != 479 & j != 0 & j != 799) {
                        n = start[i-1][j];
                        ln = (Math.abs(x - (i-1)) + Math.abs(y - j));
                        s = start[i+1][j];
                        ls = (Math.abs(x - (i+10) + Math.abs(y - j)));
                        w = start[i][j-1];
                        lw = (Math.abs(x - i) + Math.abs(y - (j-1)));
                        e = start[i][j+1];
                        le = (Math.abs(x - i) + Math.abs(y - (j+1)));
                        nw = start[i-1][j-1];
                        lnw = (Math.abs(x - (i-1)) + Math.abs(y - (j-1)));
                        ne = start[i-1][j+1];
                        lne = (Math.abs(x - (i-1)) + Math.abs(y - (j+1)));
                        sw = start[i+1][j-1];
                        lsw = (Math.abs(x - (i+1)) + Math.abs(y - (j-1)));
                        se = start[i+1][j+1];
                        lse = (Math.abs(x - (i+1)) + Math.abs(y - (j+1)));
                        c = start[i][j];
                        lc = (Math.abs(x - i) + Math.abs(y - j));
                    }

                    //orismos parametrwn stis oriakes sun8hkes
                    if (i == 0 & j == 0){

                        n=0;
                        ln=1000;
                        w=0;
                        lw=1000;
                        e=start[i][j+1];
                        le=(Math.abs(x-i)+Math.abs(y-(j+1)));
                        s=start[i+1][j];
                        ls=(Math.abs(x-(i+1))+Math.abs(y-j));
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=0;
                        lnw=1000;
                        ne=0;
                        lne=1000;
                        sw=0;
                        lsw=1000;
                        se=start[i+1][j+1];
                        lse=(Math.abs(x-(i+1))+Math.abs(y-(j+1)));

                    }

                    if (i == 0 & j == 799){

                        n=0;
                        ln=100;
                        w=start[i][j-1];
                        lw=(Math.abs(x-i)+Math.abs(y-(j-1)));
                        e=0;
                        le=100;
                        s=start[i+1][j];
                        ls=(Math.abs(x-(i+1))+Math.abs(y-j));
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=0;
                        lnw=1000;
                        ne=0;
                        lne=1000;
                        sw=start[i+1][j-1];
                        lsw=(Math.abs(x-(i+1))+Math.abs(y-(j-1)));
                        se=0;
                        lse=1000;

                    }

                    if (i == 479 & j == 0){

                        n=start[i-1][j];
                        ln=(Math.abs(x-(i-1))+Math.abs(y-j));
                        w=0;
                        lw=100;
                        e=start[i][j+1];
                        le=(Math.abs(x-i)+Math.abs(y-(j+1)));
                        s=0;
                        ls=100;
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=0;
                        lnw=1000;
                        ne=start[i-1][j+1];
                        lne=(Math.abs(x-(i-1))+Math.abs(y-(j+1)));
                        sw=0;
                        lsw=1000;
                        se=0;
                        lse=1000;

                    }

                    if (i == 479 & j == 799){

                        n=start[i-1][j];
                        ln=(Math.abs(x-(i-1))+Math.abs(y-j));
                        w=start[i][j-1];
                        lw=(Math.abs(x-i)+Math.abs(y-(j-1)));
                        e=0;
                        le=100;
                        s=0;
                        ls=100;
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=start[i-1][j-1];
                        lnw=(Math.abs(x-(i-1))+Math.abs(y-(j-1)));
                        ne=0;
                        lne=1000;
                        sw=0;
                        lsw=1000;
                        se=0;
                        lse=1000;

                    }

                    if (i == 0 & j != 0 & j !=799){

                        n=0;
                        ln=100;
                        w=start[i][j-1];
                        lw=(Math.abs(x-i)+Math.abs(y-(j-1)));
                        e=start[i][j+1];
                        le=(Math.abs(x-i)+Math.abs(y-(j+1)));
                        s=start[i+1][j];
                        ls=(Math.abs(x-(i+1))+Math.abs(y-j));
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=0;
                        lnw=1000;
                        ne=0;
                        lne=1000;
                        sw=start[i+1][j-1];
                        lsw=(Math.abs(x-(i+1))+Math.abs(y-(j-1)));
                        se=start[i+1][j+1];
                        lse=(Math.abs(x-(i+1))+Math.abs(y-(j+1)));

                    }

                    if (i == 479 & j != 0 & j !=799){

                        n=start[i-1][j];
                        ln=(Math.abs(x-(i-1))+Math.abs(y-j));
                        w=start[i][j-1];
                        lw=(Math.abs(x-i)+Math.abs(y-(j-1)));
                        e=start[i][j+1];
                        le=(Math.abs(x-i)+Math.abs(y-(j+1)));
                        s=0;
                        ls=100;
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=start[i-1][j-1];
                        lnw=(Math.abs(x-(i-1))+Math.abs(y-(j-1)));
                        ne=start[i-1][j+1];
                        lne=(Math.abs(x-(i-1))+Math.abs(y-(j+1)));
                        sw=0;
                        lsw=1000;
                        se=0;
                        lse=1000;

                    }

                    if (j == 0 & i != 0 & i !=479){

                        n=start[i-1][j];
                        ln=(Math.abs(x-(i-1))+Math.abs(y-j));
                        w=0;
                        lw=100;
                        e=start[i][j+1];
                        le=(Math.abs(x-i)+Math.abs(y-(j+1)));
                        s=start[i+1][j];
                        ls=(Math.abs(x-(i+1))+Math.abs(y-j));
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=0;
                        lnw=1000;
                        ne=start[i-1][j+1];
                        lne=(Math.abs(x-(i-1))+Math.abs(y-(j+1)));
                        sw=0;
                        lsw=1000;
                        se=start[i+1][j+1];
                        lse=(Math.abs(x-(i+1))+Math.abs(y-(j+1)));

                    }

                    if (j == 799 & i != 0 & i !=479){

                        n=start[i-1][j];
                        ln=(Math.abs(x-(i-1))+Math.abs(y-j));
                        w=start[i][j-1];
                        lw=(Math.abs(x-i)+Math.abs(y-(j-1)));
                        e=0;
                        le=100;
                        s=start[i+1][j];
                        ls=(Math.abs(x-(i+1))+Math.abs(y-j));
                        c=start[i][j];
                        lc=(Math.abs(x-i)+Math.abs(y-j));
                        nw=start[i-1][j-1];
                        lnw=(Math.abs(x-(i-1))+Math.abs(y-(j-1)));
                        ne=0;
                        lne=1000;
                        sw=start[i+1][j-1];
                        lsw=(Math.abs(x-(i+1))+Math.abs(y-(j-1)));
                        se=0;
                        lse=1000;

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

                        //e3odos apo pulh
                        if ((lc==1| lnw==0 | lne==0 | lsw==0 | lse==0)&(n==3| ne==3 | se==3 | nw==3 | sw==3)) {

                            finish[i][j] = 0;

                        } else if (((i>0)&(i<120)&(j>0)&(j<200)|(i>359)&(i<479)&(j>0)&(j<200))&(e==4)&(finish[i][j+2]==0)) {

                            //e3odos apo eswterikh pulh
                            finish[i][j]=0;
                            finish[i][j+2]=1;

                        } else if (((i>0)&(i<120)&(j>0)&(j<200)|(i>359)&(i<479)&(j>0)&(j<200))&(ne==4)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if (((i>0)&(i<120)&(j>599)&(j<799)|(i>359)&(i<479)&(j>599)&(j<799))&(w==4)&(finish[i][j-2]==0)) {

                            finish[i][j]=0;
                            finish[i][j-2]=1;

                        } else if (((i>0)&(i<120)&(j>599)&(j<799)|(i>359)&(i<479)&(j>599)&(j<799))&(nw==4)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(ls<ln)&(finish[i+1][j]==0)) {

                            //apofugh katw empodiwn
                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lsw<ln)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lse<ln)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lnw<=le)&(lnw<=lw)&(lnw<=lne)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lne<=le)&(lne<=lw)&(lne<=lnw)&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(le<=lw)&(finish[i][j+1]==0)) {

                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lw<=le)&(finish[i][j-1]==0)) {

                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(ln<ls)&(finish[i-1][j]==0)) {

                            //apofugh anw empodiwn
                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(lnw<ls)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(lne<ln)&(finish[i-1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(lsw<=le)&(lsw<=lw)&(lsw<=lse)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(lse<=le)&(lse<=lw)&(lse<=lsw)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((s==2)&(le<=lw)&(finish[i][j+1]==0)) {

                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if ((n==2)&(lw<=le)&(finish[i][j-1]==0)) {

                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(lw<le)&(finish[i][j-1]==0)) {

                            //apofugh de3iwn empodiwn
                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(lsw<le)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(lnw<le)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(lne<=ln)&(lne<=ls)&(lne<=lse)&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(lse<=ln)&(lse<=ls)&(lse<=lne)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(ln<=ls)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if ((e==2)&(ls<=ln)&(finish[i+1][j]==0)) {

                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(le<lw)&(finish[i][j+1]==0)) {

                            //apofugh aristerwn empodiwn
                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(lne<lw)&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(lse<lw)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(lnw<=ln)&(lnw<=ls)&(lnw<=lsw)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(lsw<=ln)&(lsw<=ls)&(lsw<=lnw)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(ln<=ls)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if ((w==2)&(ls<=ln)&(finish[i+1][j]==0)) {

                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if (((ne==2)|(nw==2))&(ln<=ls)&(n==0)&(finish[i-1][j]==0)) {

                            //apofugh gwniakwn empodiwn
                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if (((ne==2)|(nw==2))&(ls<=ln)&(s==0)&(finish[i+1][j]==0)) {

                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if (((se==2)|(sw==2))&(ls<=ln)&(s==0)&(finish[i+1][j]==0)) {

                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if (((se==2)|(sw==2))&(ln<=ls)&(n==0)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if (((ls<ln)&(ls<le)&(ls<lw)&(ls<lnw)&(ls<lne)&(ls<lsw)&(ls<lse))&(finish[i+1][j]==0)) {

                            //euresh taxuterhs diadromhs k kinhsh me elegxo an h epomenh kupselida einai eleu8erh
                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if (((lw<ln)&(lw<le)&(lw<ls)&(lw<lnw)&(lw<lne)&(lw<lsw)&(lw<lse))&(finish[i][j-1]==0)) {

                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if (((le<ln)&(le<ls)&(le<lw)&(le<lnw)&(le<lne)&(le<lsw)&(le<lse))&(finish[i][j+1]==0)) {

                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if (((ln<ls)&(ln<le)&(ln<lw)&(ln<lnw)&(ln<lne)&(ln<lsw)&(ln<lse))&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if (((lnw<ln)&(lnw<le)&(lnw<lw)&(lnw<ls)&(lnw<lne)&(lnw<lsw)&(lnw<lse))&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if (((lne<ln)&(lne<le)&(lne<lw)&(lne<ls)&(lne<lnw)&(lne<lsw)&(lne<lse))&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if (((lsw<ln)&(lsw<le)&(lsw<lw)&(lsw<ls)&(lsw<lne)&(lsw<lnw)&(lsw<lse))&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if (((lse<ln)&(lse<le)&(lse<lw)&(lse<ls)&(lse<lne)&(lse<lnw)&(lse<lsw))&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if (((ls<=ln)&(ls<=le)&(ls<=lw)&(ls<=lnw)&(ls<=lne)&(ls<=lsw)&(ls<=lse))&(s==0)&(finish[i+1][j]==0)) {

                            //euresh deuterhs grhgoroterhs diadromhs
                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if (((lw<=ln)&(lw<=le)&(lw<=ls)&(lw<=lnw)&(lw<=lne)&(lw<=lsw)&(lw<=lse))&(w==0)&(finish[i][j-1]==0)) {

                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if (((le<=ln)&(le<=ls)&(le<=lw)&(le<=lnw)&(le<=lne)&(le<=lsw)&(le<=lse))&(e==0)&(finish[i][j+1]==0)) {

                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if (((ln<=ls)&(ln<=le)&(ln<=lw)&(ln<=lnw)&(ln<=lne)&(ln<=lsw)&(ln<=lse))&(n==0)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if (((lnw<=ln)&(lnw<=le)&(lnw<=lw)&(lnw<=ls)&(lnw<=lne)&(lnw<=lsw)&(lnw<=lse))&(nw==0)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if (((lne<=ln)&(lne<=le)&(lne<=lw)&(lne<=ls)&(lne<=lnw)&(lne<=lsw)&(lne<=lse))&(ne==0)&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if (((lsw<=ln)&(lsw<=le)&(lsw<=lw)&(lsw<=ls)&(lsw<=lne)&(lsw<=lnw)&(lsw<=lse))&(sw==0)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if (((lse<=ln)&(lse<=le)&(lse<=lw)&(lse<=ls)&(lse<=lne)&(lse<=lnw)&(lse<=lsw))&(se==0)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((ls<=(ln)&(le)&(lw)&(lnw)&(lne)&(lsw)&(lse))&(s==0)&(finish[i+1][j]==0)) {

                            //euresh diadromis se periptwsh apotuxias
                            finish[i+1][j]=1;
                            finish[i][j]=0;

                        } else if ((lw<=(ln)&(le)&(ls)&(lnw)&(lne)&(lsw)&(lse))&(w==0)&(finish[i][j-1]==0)) {

                            finish[i][j-1]=1;
                            finish[i][j]=0;

                        } else if ((le<=(ln)&(lw)&(ls)&(lnw)&(lne)&(lsw)&(lse))&(e==0)&(finish[i][j+1]==0)) {

                            finish[i][j+1]=1;
                            finish[i][j]=0;

                        } else if ((ln<=(lw)&(le)&(ls)&(lnw)&(lne)&(lsw)&(lse))&(n==0)&(finish[i-1][j]==0)) {

                            finish[i-1][j]=1;
                            finish[i][j]=0;

                        } else if ((lnw<=(ln)&(le)&(lw)&(ls)&(lne)&(lsw)&(lse))&(nw==0)&(finish[i-1][j-1]==0)) {

                            finish[i-1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((lne<=(ln)&(le)&(lw)&(ls)&(lnw)&(lsw)&(lse))&(ne==0)&(finish[i-1][j+1]==0)) {

                            finish[i-1][j+1]=1;
                            finish[i][j]=0;

                        } else if ((lsw<=(ln)&(le)&(lw)&(ls)&(lne)&(lnw)&(lse))&(sw==0)&(finish[i+1][j-1]==0)) {

                            finish[i+1][j-1]=1;
                            finish[i][j]=0;

                        } else if ((lse<=(ln)&(le)&(lw)&(ls)&(lne)&(lnw)&(lsw))&(se==0)&(finish[i+1][j+1]==0)) {

                            finish[i+1][j+1]=1;
                            finish[i][j]=0;

                        }
                    }

                }
            }

            start = finish;
        }

    }
}
