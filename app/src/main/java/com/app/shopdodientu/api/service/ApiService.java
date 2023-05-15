package com.app.shopdodientu.api.service;

import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.CartModel;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.CheckoutModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.model.TinhModel;
import com.app.shopdodientu.model.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    @GET("find-product")
    Call<PageModel<ProductModel>> findProduct(@Query("categoryId") int id,
                                              @Query("keyword") String keyword,
                                              @Query("orderby") String orderby,
                                              @Query("page") int page);
    @PUT("account/update-name")
    @FormUrlEncoded
    Call<UserModel> updateName(@Field("id") int id, @Field("fullname") String fullname);

    @PUT("account/update-pw")
    @FormUrlEncoded
    Call<UserModel> updatePw(@Field("id") int id, @Field("oldpw") String oldpw, @Field("newpw") String newpw);

    @GET("logout")
    Call<String> logout();
    @Multipart
    @POST("seller/product")
    Call<ProductModel> addProduct(@Part("name") RequestBody name,
                                  @Part("description") RequestBody description,
                                  @Part("price") RequestBody price,
                                  @Part("stock") RequestBody stock,
                                  @Part MultipartBody.Part imageFile,
                                  @Part("categoryId") RequestBody categoryId);
    @POST("account/add-product-to-cart")
    @FormUrlEncoded
    Call<CartItemModel> addToCart(@Field("productId") int productId, @Field("quantity") int quantity);

    @GET("account/cart/{id}/items-in-cart")
    Call<List<CartItemModel>> getItemsInCart(@Path("id") int cartId);

    @GET("account/cart/items-in-cart-no-checkout")
    Call<List<CartItemModel>> getItemsInCartNoCheckOut();

    @GET("find-product-by-seller")
    Call<PageModel<ProductModel>> findProductsBySeller(@Query("sellerId") int sellerId,
                                                  @Query("categoryId") int categoryId,
                                                  @Query("keyword") String keyword,
                                                  @Query("page") int page,
                                                  @Query("orderby") String orderby);
    @PUT("seller/product")
    @Multipart
    Call<ProductModel> updateProduct(@Part("id") RequestBody id,
                                     @Part("userId") RequestBody userId,
                                     @Part("name") RequestBody name,
                                     @Part("description") RequestBody description,
                                     @Part("price") RequestBody price,
                                     @Part("stock") RequestBody stock,
                                     @Part MultipartBody.Part imageFile,
                                     @Part("categoryId") RequestBody categoryId,
                                     @Part("status") RequestBody status);

    @PUT("seller/product")
    @Multipart
    Call<ProductModel> updateProduct(@Part("id") RequestBody id,
                                     @Part("userId") RequestBody userId,
                                     @Part("name") RequestBody name,
                                     @Part("description") RequestBody description,
                                     @Part("price") RequestBody price,
                                     @Part("stock") RequestBody stock,
                                     @Part("categoryId") RequestBody categoryId,
                                     @Part("status") RequestBody status);

    @DELETE("seller/product/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

    @PUT("account/cart-item/update-quantity")
    @FormUrlEncoded
    Call<CartItemModel> updateQuantityItem(@Field("id") int id, @Field("quantity") int quantity);

    @PUT("account/delete-item-from-cart")
    Call<ResponseBody> deleteItemFromCart(@Body List<Integer> data);

    @GET("seller/checking")
    Call<PageModel<CartItemModel>> getCartItemsBySeller(@Query("sellerId") int sellerId,
                                                        @Query("page") int page,
                                                        @Query("status") int status);
    @POST("seller/conform-cart-item")
    @FormUrlEncoded
    Call<CartItemModel> conformCartItem(@Field("id") int id, @Field("status") int status);

    @GET("account/cart-by-user-and-status")
    Call<PageModel<CartModel>> getCarts(@Query("status") int status, @Query("page") int page);

    @GET("address")
    Call<List<TinhModel>> getAddress();

    @POST("account/checkout")
    Call<ResponseBody> checkout(@Body CheckoutModel checkoutModel);

    @POST("account/buy-now")
    @FormUrlEncoded
    Call<ResponseBody> buyNow(@Field("productId") int productId, @Field("quantity") int quantity, @Field("address") String address);

    @DELETE("account/cancel-order/{id}")
    Call<ResponseBody> cancelOrder(@Path("id") int id);

    @GET("account/cart/{id}")
    Call<CartModel> getCart(@Path("id") int id);
}
