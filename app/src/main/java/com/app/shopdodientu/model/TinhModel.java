package com.app.shopdodientu.model;

import java.util.List;

public class TinhModel {
    private String name;
    private String slug;
    private String type;
    private String name_with_type;
    private String code;
    List<HuyenModel> districts;

    public TinhModel() {
    }

    public TinhModel(String name, String slug, String type, String name_with_type, String code, List<HuyenModel> districts) {
        this.name = name;
        this.slug = slug;
        this.type = type;
        this.name_with_type = name_with_type;
        this.code = code;
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName_with_type() {
        return name_with_type;
    }

    public void setName_with_type(String name_with_type) {
        this.name_with_type = name_with_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<HuyenModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<HuyenModel> districts) {
        this.districts = districts;
    }
}
