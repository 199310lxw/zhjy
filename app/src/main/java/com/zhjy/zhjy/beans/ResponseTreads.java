package com.zhjy.zhjy.beans;

import java.util.List;

public class ResponseTreads {
    private int code;
    private String message;
    private List<Treads> datas;
    private int total ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Treads> getDatas() {
        return datas;
    }

    public void setDatas(List<Treads> datas) {
        this.datas = datas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
