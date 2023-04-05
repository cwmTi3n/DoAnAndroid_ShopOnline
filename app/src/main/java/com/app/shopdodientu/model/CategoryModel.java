package com.app.shopdodientu.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int id;
    private String name;
    private String images;
    private int status;

    public CategoryModel() {
    }

    public CategoryModel(int id, String name, String images, int status) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImages() {
        return images;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
