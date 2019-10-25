package cn.pxy.ysframework.utils;

import java.io.Serializable;

public class Message implements Serializable {
    private String Code;
    private String Message;
    private String Data;

    public Message(String data) {
        Code = "200";
        Message = "成功";
        Data = data;
    }

    public Message() {
        Code = "200";
        Message = "成功";
    }

    public Message(String code, String message) {
        Code = code;
        Message = message;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

}
