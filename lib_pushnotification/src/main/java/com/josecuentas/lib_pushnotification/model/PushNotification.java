package com.josecuentas.lib_pushnotification.model;

import com.josecuentas.lib_pushnotification.Push;

import java.util.Map;

/**
 * Created by jcuentas on 25/01/17.
 */

public class PushNotification {
    private String title;
    private String body;
    private String data;
    private String type;

    private Map<String, String> mMap;

    public PushNotification() {

    }

    public PushNotification(Map<String, String> map) {
        mMap = map;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getMap() {
        return mMap;
    }

    public void setMap(Map<String, String> map) {
        mMap = map;
    }

    private String populateData() {
        if (mMap.size() > 0) return mMap.get(Push.DATA);
        else return "";
    }

    private String populateType() {
        if (mMap.size() > 0) return mMap.get(Push.TYPE);
        else return "";
    }

    private void validate() {
        if (mMap == null || mMap.isEmpty()) throw new NullPointerException("mMap is null or is empty");
    }
}
