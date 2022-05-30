package com.example.springboot.control.dto;

import java.time.LocalDateTime;

/**
 * <h4>springboot</h4>
 * <p></p>
 *
 * @author : shenbh
 * @date : 2022-05-16 17:11
 **/
public class UserInfoDo {
    private String userName;
    private String passWord;
    private Integer userId;
    private String telephone;
    private LocalDateTime registerTime;
    private String popedom;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public String getPopedom() {
        return popedom;
    }

    public void setPopedom(String popedom) {
        this.popedom = popedom;
    }
}
