package com.app.shopdodientu.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String username;
    private String password;

    public AuthInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .header("Authorization", Credentials.basic(username, password));
        request = builder.build();
        return chain.proceed(request);
    }
}
