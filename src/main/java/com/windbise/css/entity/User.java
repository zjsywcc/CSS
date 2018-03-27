package com.windbise.css.entity;

/**
 * Created by wangchengcheng on 2018/3/2.
 */
public class User {

    // -1为guest
    private int id;
    private String username;
    private String nickname;
    private String password;
    // true为买家 false为卖家
    private boolean type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
