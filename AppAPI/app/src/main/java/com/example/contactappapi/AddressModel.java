package com.example.contactappapi;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
public class AddressModel implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String lat;
    private String lng;

    public AddressModel(JSONObject json) {
        try {
            this.street = json.getString("street");
            this.suite = json.getString("suite");
            this.city = json.getString("city");
            this.zipcode = json.getString("zipcode");
            this.lat = new JSONObject(json.getString("geo")).getString("lat");
            this.lng = new JSONObject(json.getString("geo")).getString("lng");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "AddressModel{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
