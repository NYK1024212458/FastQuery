package com.fastquery.model;

public class CustomerModel {


    /**
     * id : 2
     * name : 使用微信
     * type : wxwxwx
     * typeb : 20200728\0a019b1846b52591666884fad802f299.jpg
     * typec : 微信客服
     * statu : 1
     */

    private int id;
    private String name;
    private String type;
    private String typeb;
    private String typec;
    private String statu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeb() {
        return typeb;
    }

    public void setTypeb(String typeb) {
        this.typeb = typeb;
    }

    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }
}
