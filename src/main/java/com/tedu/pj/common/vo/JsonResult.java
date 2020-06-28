package com.tedu.pj.common.vo;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = -5115300382095800134L;

    private int state = 1;
    private String message = "ok";
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(Throwable t) {
        this.state = 0;
        this.message = t.getMessage();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
