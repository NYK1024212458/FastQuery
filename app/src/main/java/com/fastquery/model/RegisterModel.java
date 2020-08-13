package com.fastquery.model;

/**
 * Create By 唐辉
 * 2020/7/14
 * 类说明：
 */
public class RegisterModel {

    /**
     * startTime : 2020-07-19 13:55:24
     * code : -101
     * desc : 无法注册，用户已经存在！
     * data : null
     * token : null
     * name : null
     * page : 0
     * pageSize : 0
     * maxRow : 0
     * sum : 0
     * pageIndex : 0
     */

    private String startTime;
    private int code;
    private String desc;
    private Object data;
    private Object token;
    private Object name;
    private int page;
    private int pageSize;
    private int maxRow;
    private int sum;
    private int pageIndex;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
