package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 10/16/14.
 */
public class Additional implements Serializable {

    String information;
    String detail;

    public Additional(String information, String detail) {
        this.information = information;
        this.detail = detail;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
