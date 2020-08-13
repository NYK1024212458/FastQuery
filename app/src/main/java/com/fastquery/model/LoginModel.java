package com.fastquery.model;

public class LoginModel {


    /**
     * code : 200
     * sms : 验证码输入正确，注册成功
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

}
