package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 10/6/14.
 */
public class Cost implements Serializable{

    String type, amount;

    public Cost(String type, String amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
