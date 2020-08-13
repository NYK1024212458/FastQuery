package com.fastquery.model;

public class FindLocationModel {


    /**
     * user_phone : 15507057581
     * positionx : 112.5173102320078
     * positiony : 32.99727905768408
     * time : 2020-08-05 18:32:44
     */




    private int code;
    private String sms;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }



    private String user_phone;
    private String positionx;
    private String positiony;
    private String time;

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getPositionx() {
        return positionx;
    }

    public void setPositionx(String positionx) {
        this.positionx = positionx;
    }

    public String getPositiony() {
        return positiony;
    }

    public void setPositiony(String positiony) {
        this.positiony = positiony;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
