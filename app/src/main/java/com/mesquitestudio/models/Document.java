package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 10/2/14.
 */
public class Document implements Serializable{

    int id;
    String name, description;

    public Document(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
