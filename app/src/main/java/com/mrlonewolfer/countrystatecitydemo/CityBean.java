package com.mrlonewolfer.countrystatecitydemo;

import androidx.annotation.NonNull;

public class CityBean {
    String cityname,regioncode,countrycode;
    Double latitude,longitude;

    public CityBean(String cityname, String regioncode, String countrycode, Double latitude, Double longitude) {
        this.cityname = cityname;
        this.regioncode = regioncode;
        this.countrycode = countrycode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getRegioncode() {
        return regioncode;
    }

    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return cityname;
    }
}
