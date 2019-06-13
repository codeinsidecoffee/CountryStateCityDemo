package com.mrlonewolfer.countrystatecitydemo;

import android.os.Parcelable;

import androidx.annotation.NonNull;

public class RegionBean {
    String regioname,countycode;


    public RegionBean(String regioname, String countycode) {
        this.regioname = regioname;
        this.countycode = countycode;
    }

    public String getRegioname() {
        return regioname;
    }

    public void setRegioname(String regioname) {
        this.regioname = regioname;
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode;
    }

    @NonNull
    @Override
    public String toString() {
        return regioname;
    }
}
