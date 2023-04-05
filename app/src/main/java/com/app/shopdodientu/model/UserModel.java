package com.app.shopdodientu.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    private int id;
    private String username;
    private String email;
    private String fullname;
    private String images;
    private String phone;
    private int status;
    private String role;

    public UserModel() {
    }

    public UserModel(int id, String username, String email, String fullname, String images, String phone, int status, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.images = images;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }

    public String getImages() {
        return images;
    }

    public String getPhone() {
        return phone;
    }

    public int getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }
}
