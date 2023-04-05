package com.app.shopdodientu.api.client;

import com.app.shopdodientu.api.service.ApiService;

public class ApiClient extends BaseClient{
    private static final String BASE_URL = "http://app.iotstar.vn/appfoods/";
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            return createService(ApiService.class, BASE_URL);
        }
        return apiService;
    }
}
