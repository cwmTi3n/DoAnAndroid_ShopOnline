package com.app.shopdodientu.api.service;

import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("category")
    Call<List<CategoryModel>> getAllCategory();

    @POST("signup")
    @FormUrlEncoded
    Call<UserModel> signup(@Field("username") String username,
                           @Field("password") String password,
                           @Field("email") String email,
                           @Field("phone") String phone);
    @POST("login")
    @FormUrlEncoded
    Call<UserModel> login(@Field("username") String username, @Field("password") String password);
}
