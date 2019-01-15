package com.example.administrator.plb.entity;

public class qustionBeanNo {
    private int NoIcon;
    private String NoQuestion;

    public qustionBeanNo() {
        super();
    }

    public qustionBeanNo(int noIcon, String noQuestion) {
        NoIcon = noIcon;
        NoQuestion = noQuestion;
    }

    public int getNoIcon() {
        return NoIcon;
    }

    public void setNoIcon(int noIcon) {
        NoIcon = noIcon;
    }

    public String getNoQuestion() {
        return NoQuestion;
    }

    public void setNoQuestion(String noQuestion) {
        NoQuestion = noQuestion;
    }
}
