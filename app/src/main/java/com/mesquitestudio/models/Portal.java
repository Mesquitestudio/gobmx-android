package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 9/29/14.
 */
public class Portal implements Serializable{

    int id;
    String url;
    String name;

    public Portal(int id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public Portal(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
