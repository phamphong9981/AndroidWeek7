package com.example.android_week7;

public class Mail {
    String icon;
    String subject;
    String content1;
    String content2;
    String time;
    String color;
    boolean checkbox;


    public Mail(String subject, String content1, String content2, String time, String color) {
        this.subject = subject;
        this.content1 = content1;
        this.content2 = content2;
        this.time = time;
        this.color=color;
        icon=Character.toString(subject.charAt(0));
        checkbox=false;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }


    public String getSubject() {
        return subject;
    }

    public String getContent1() {
        return content1;
    }

    public String getContent2() {
        return content2;
    }

    public String getTime() {
        return time;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
