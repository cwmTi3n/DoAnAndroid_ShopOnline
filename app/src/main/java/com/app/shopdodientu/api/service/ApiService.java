package com.app.shopdodientu.api.service;

import com.app.shopdodientu.model.CategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("category")
    Call<List<CategoryModel>> getAllCategory();
}
