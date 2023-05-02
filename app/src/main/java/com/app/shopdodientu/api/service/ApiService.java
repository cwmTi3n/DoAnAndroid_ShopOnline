package com.app.shopdodientu.api.service;

import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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

    @GET("find-product?orderby=desccreateDate")
    Call<PageModel<ProductModel>> lastProduct(@Query("page") int page);

    @PUT("account/update-name")
    @FormUrlEncoded
    Call<UserModel> updateName(@Field("id") int id, @Field("fullname") String fullname);
}
