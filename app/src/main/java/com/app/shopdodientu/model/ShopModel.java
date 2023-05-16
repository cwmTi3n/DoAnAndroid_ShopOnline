package com.app.shopdodientu.model;

public class ShopModel {
    private String address;
    private String bannerShop;

    public ShopModel() {
    }

    public ShopModel(String address, String bannerShop) {
        this.address = address;
        this.bannerShop = bannerShop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBannerShop() {
        return bannerShop;
    }

    public void setBannerShop(String bannerShop) {
        this.bannerShop = bannerShop;
    }
}
