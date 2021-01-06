package com.example.myrecview;

public class Item {

    private String mMertErtek;
    private String mDatum;
    private String mRajzszam;

    public Item(String mertertek, String datum, String rajzszam)
    {
        mMertErtek = mertertek;
        mDatum = datum;
        mRajzszam = rajzszam;
    }
    public String getmMertErtek()
    {
        return mMertErtek;
    }

    public String getmDatum() {
        return mDatum;
    }

    public String getmRajzszam() {
        return mRajzszam;
    }
}
