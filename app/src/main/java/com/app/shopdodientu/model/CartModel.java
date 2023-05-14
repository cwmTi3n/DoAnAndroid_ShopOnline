package com.app.shopdodientu.model;

import java.io.Serializable;
import java.util.Date;

public class CartModel implements Serializable {
    private int id;
    private int status;

    private String avatar;

    private int sumProduct;

    private int sumPrice;
    private String buyDate;
    private int userId;

    public CartModel() {
    }

    public CartModel(int id, int status, String avatar, int sumProduct, int sumPrice, String buyDate, int userId) {
        this.id = id;
        this.status = status;
        this.avatar = avatar;
        this.sumProduct = sumProduct;
        this.sumPrice = sumPrice;
        this.buyDate = buyDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(int sumProduct) {
        this.sumProduct = sumProduct;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
