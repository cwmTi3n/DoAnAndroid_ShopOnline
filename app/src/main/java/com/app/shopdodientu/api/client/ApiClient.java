package com.app.shopdodientu.api.client;

import android.util.Log;

import com.app.shopdodientu.api.service.ApiService;

public class ApiClient extends BaseClient{
<<<<<<< HEAD
    private static final String BASE_URL = "http://192.168.1.10/";
//    private static final String BASE_URL = "https://apishoponline.cfapps.ap21.hana.ondemand.com/";
=======
//    private static final String BASE_URL = "http://192.168.1.12/";
    private static final String BASE_URL = "https://apishoponline.cfapps.ap21.hana.ondemand.com/";
>>>>>>> 852897caf9141a9675598f131c1ee87fd55e45f6
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
        apiService = createService(ApiService.class, BASE_URL, null, null);
    }
}
