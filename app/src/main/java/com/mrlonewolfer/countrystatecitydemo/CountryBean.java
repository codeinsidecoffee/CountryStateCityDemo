package com.mrlonewolfer.countrystatecitydemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class CountryBean {
    String countryName,countryCode;


    public CountryBean(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    protected CountryBean(Parcel in) {
        countryName = in.readString();
        countryCode = in.readString();
    }



    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    @Override
    public String toString() {
        return countryName;
    }
}
