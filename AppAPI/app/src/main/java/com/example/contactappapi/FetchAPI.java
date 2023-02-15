package com.example.contactappapi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchAPI extends AsyncTask<Void, Void, Boolean> {
    String jsonData = "";
    final MainActivity activity;
    public FetchAPI(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            URL url = new URL("https://lebavui.github.io/jsons/users.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            Log.v("TAG", "response code: " + responseCode);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                jsonData = jsonData + line + "\n";
            reader.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            try {
                List<ContactModel> contacts = new ArrayList<ContactModel>();
                JSONArray _jsonDatas = new JSONArray(jsonData);

                for(int i = 0; i < _jsonDatas.length(); i++) {
                    ContactModel cm = new ContactModel(_jsonDatas.getJSONObject(i));
                    contacts.add(cm);
//                    Log.v("TAG", cm.toString());
                }
                activity.InitAdapter(contacts);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            Log.v("TAG", "ĐÃ XẢY RA LỖI");
        }
    }
}
