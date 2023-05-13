package com.app.shopdodientu.model;

import java.io.Serializable;

public class CartItemModel implements Serializable {
    private int id;
    private int quantity;
    private float unitPrice;
    private int status;
    private int productId;
    private int cartId;
    private String image;
    private String productName;

    public CartItemModel() {
    }

    public CartItemModel(int id, int quantity, float unitPrice, int status, int productId, int cartId, String image, String productName) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = status;
        this.productId = productId;
        this.cartId = cartId;
        this.image = image;
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
