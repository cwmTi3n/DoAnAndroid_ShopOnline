package com.app.shopdodientu.model;

import java.io.Serializable;
import java.util.Date;

public class CartModel implements Serializable {
    private int id;
    private int status;
    private Date buyDate;
    private int userId;

    public CartModel() {
    }

    public CartModel(int id, int status, Date buyDate, int userId) {
        this.id = id;
        this.status = status;
        this.buyDate = buyDate;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public int getUserId() {
        return userId;
    }
}
