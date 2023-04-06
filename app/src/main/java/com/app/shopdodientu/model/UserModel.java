package com.app.shopdodientu.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private int id;
    private String username;
    private String email;
    private String fullname;
    private String avatar;
    private String phone;
    private int status;
    private String role;

    public UserModel() {
    }

    public UserModel(int id, String username, String email, String fullname, String avatar, String phone, int status, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.avatar = avatar;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
