package com.app.shopdodientu.api.client;

import com.app.shopdodientu.api.AuthInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {
    private static HttpLoggingInterceptor sLogging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder sHttpClient =
            new OkHttpClient.Builder();

    static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        if (!sHttpClient.interceptors().contains(sLogging)) {
        }
        sHttpClient.addInterceptor(sLogging);
        builder.client(sHttpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    static <S> S loginService(Class<S> serviceClass, String baseUrl, String username, String password) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        if (!sHttpClient.interceptors().contains(sLogging)) {
            sHttpClient.addInterceptor(sLogging);
            AuthInterceptor authInterceptor = new AuthInterceptor(username, password);
            sHttpClient.addInterceptor(authInterceptor);
            builder.client(sHttpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
