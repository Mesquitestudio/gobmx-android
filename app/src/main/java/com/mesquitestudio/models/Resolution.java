package com.mesquitestudio.models;

import java.io.Serializable;

/**
 * Created by paulmoreno on 10/16/14.
 */
public class Resolution implements Serializable{

    String question;
    String answer;

    public Resolution(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
