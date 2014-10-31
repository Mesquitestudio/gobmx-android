package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 10/16/14.
 */
public class Aditional implements Serializable {

    String information;
    String Detail;

    public Aditional(String information, String detail) {
        this.information = information;
        Detail = detail;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
