package com.example.springboot.control.dto;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-16 16:47
 **/
public class JsonObject<T> {
    private String message;
    private Integer code;
    private T date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public JsonObject(String message, Integer code, T date) {
        this.message = message;
        this.code = code;
        this.date = date;
    }

    public JsonObject() {
    }
}
