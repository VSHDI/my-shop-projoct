package com.funtl.my.shop.domain;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String password;
    private String username;
    private Boolean isRemember;

    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
