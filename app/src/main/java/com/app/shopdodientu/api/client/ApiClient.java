package com.app.shopdodientu.api.client;

import android.util.Log;

import com.app.shopdodientu.api.service.ApiService;

public class ApiClient extends BaseClient{

    private static final String BASE_URL = "http://172.16.37.41/";
//    private static final String BASE_URL = "https://apishoponline.cfapps.ap21.hana.ondemand.com/";


    public static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = createService(ApiService.class, BASE_URL, null, null);
        }
        return apiService;
    }

    public static ApiService login(String username, String password) {
        apiService = createService(ApiService.class, BASE_URL, username, password);
        return apiService;
    }

    public static void restApiService() {
        apiService = null;
    }
}
