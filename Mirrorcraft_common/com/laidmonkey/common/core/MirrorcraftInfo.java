package com.laidmonkey.common.core;


public class MirrorcraftInfo {

    public final String MirrorcraftName;

    public final int colourR;
    public final int colourG;
    public final int colourB;

    public MirrorcraftInfo()
    {
        MirrorcraftName = "";
        colourR = 0;
        colourG = 0;
        colourB = 0;
    }

    public MirrorcraftInfo(String name)
    {
        MirrorcraftName = name;
        colourR = 0;
        colourG = 0;
        colourB = 0;
    }

    public MirrorcraftInfo(String name, int r, int g, int b)
    {
        MirrorcraftName = name;
        colourR = r;
        colourG = g;
        colourB = b;
    }

}
