package com.mesquitestudio.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulmoreno on 10/2/14.
 */
public class Services implements Serializable{

    int id;
    String dependency, name, html, imageUrl, phone, web, address, location;
    ArrayList<Document> documentList;
    ArrayList<Cost> costsList;
    ArrayList<Resolution> resolutionList;
    ArrayList<Additional> additionalList;

    public Services(int id, String name, String dependency, String html, String imageUrl, String phone, String web, String address) {
        this.id = id;
        this.dependency = dependency;
        this.name = name;
        this.html = html;
        this.imageUrl = imageUrl;
        this.phone = phone;
        this.web = web;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(ArrayList<Document> documentList) {
        this.documentList = documentList;
    }

    public ArrayList<Cost> getCostsList() {
        return costsList;
    }

    public void setCostsList(ArrayList<Cost> costsList) {
        this.costsList = costsList;
    }

    public ArrayList<Resolution> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(ArrayList<Resolution> resolutionList) {
        this.resolutionList = resolutionList;
    }

    public ArrayList<Additional> getAdditionalList() {
        return additionalList;
    }

    public void setAditionalList(ArrayList<Additional> aditionalList) {
        this.additionalList = aditionalList;
    }
}
