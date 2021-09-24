package com.example.android.quakereport;

public class Earthquake {
    private double mMagnitude;
    private String mCountry;
    private long mDate;
    private String mUrl;

    public Earthquake(double magnitude, String country, long date, String url) {
        mMagnitude = magnitude;
        mCountry = country;
        mDate = date;
        mUrl = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmCountry() {
        return mCountry;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl(){
        return mUrl;
    }
}
