package com.app.shopdodientu.model;

import java.util.List;

public class CheckoutModel {
    List<Integer> data;
    String address;

    public CheckoutModel() {
    }

    public CheckoutModel(List<Integer> data, String address) {
        this.data = data;
        this.address = address;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
