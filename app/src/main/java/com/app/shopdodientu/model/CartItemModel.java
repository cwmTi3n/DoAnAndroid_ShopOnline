package com.app.shopdodientu.model;

import java.io.Serializable;

public class CartItemModel implements Serializable {
    private int id;
    private int quantity;
    private float unitPrice;
    private int productId;
    private int cartId;

    public CartItemModel() {
    }

    public CartItemModel(int id, int quantity, float unitPrice, int productId, int cartId) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productId = productId;
        this.cartId = cartId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getCartId() {
        return cartId;
    }
}
