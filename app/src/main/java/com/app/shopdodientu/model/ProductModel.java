package com.app.shopdodientu.model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private int id;
    private String name;
    private int code;
    private String description;
    private float price;
    private int amount;
    private int stock;
    private String images;
    private int status;
    private int categoryId;
    private int userId;

    public ProductModel() {
    }

    public ProductModel(int id, String name, int code, String description, float price, int amount, int stock, String images, int status, int categoryId, int userId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.stock = stock;
        this.images = images;
        this.status = status;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getStock() {
        return stock;
    }

    public String getImages() {
        return images;
    }

    public int getStatus() {
        return status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUserId() {
        return userId;
    }
}
