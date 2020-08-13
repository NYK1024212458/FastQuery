package com.fastquery.bean;

public class VipSuccessEvent {


    private boolean isVip;


    public VipSuccessEvent(boolean isVip){


        this.isVip=isVip;

    }


    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
