package com.app.shopdodientu.api.client;

import com.app.shopdodientu.api.service.ApiService;

public class ApiClient extends BaseClient{
//    private static final String BASE_URL = "http://192.168.1.4/";
    private static final String BASE_URL = "https://apishoponline.cfapps.ap21.hana.ondemand.com/";
    private static ApiService apiService;
    public static String username;
    public static String password;

    public static ApiService getApiService() {
        if (apiService == null) {
            return createService(ApiService.class, BASE_URL, username, password);
        }
        return apiService;
    }
}
