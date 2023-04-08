package com.app.shopdodientu.api.client;

import com.app.shopdodientu.api.service.ApiService;

public class ApiClient extends BaseClient{
    private static final String BASE_URL = "http://192.168.47.1/";
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            return createService(ApiService.class, BASE_URL);
        }
        return apiService;
    }
    public static ApiService getApiLoginService(String username, String password) {
        return  loginService(ApiService.class, BASE_URL, username, password);
    }
}
