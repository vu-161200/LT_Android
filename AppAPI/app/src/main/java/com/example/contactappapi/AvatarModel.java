package com.example.contactappapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class AvatarModel implements Serializable {
    private String thumb;
    private String wall;
    public AvatarModel(JSONObject json) {
        try {
            this.thumb = json.getString("thumbnail").replace("/thumbs/", "").replace(".jpg", "");
            this.wall = json.getString("photo").replace("/walls/", "").replace(".jpg", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    @Override
    public String toString() {
        return "AvatarModel{" +
                "thumb='" + thumb + '\'' +
                ", wall='" + wall + '\'' +
                '}';
    }
}
