package com.app.shopdodientu.model;

public class ItemMenuModel {
    private String nameCate;
    private int icon;

    public ItemMenuModel(String nameCate, int icon) {
        this.nameCate = nameCate;
        this.icon = icon;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
