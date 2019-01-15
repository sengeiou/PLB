package com.example.administrator.plb.entity;

public class qustionBeanYes {
    private int aIcon;
    private String aQuestion;
    private String aReason;

    public qustionBeanYes() {
        super();
    }

    public qustionBeanYes(int aIcon, String aQuestion, String aReason) {
        this.aIcon = aIcon;
        this.aQuestion = aQuestion;
        this.aReason = aReason;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }

    public String getaQuestion() {
        return aQuestion;
    }

    public void setaQuestion(String aQuestion) {
        this.aQuestion = aQuestion;
    }

    public String getaReason() {
        return aReason;
    }

    public void setaReason(String aReason) {
        this.aReason = aReason;
    }
}
