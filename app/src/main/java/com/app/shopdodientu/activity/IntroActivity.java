package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.HuyenModel;
import com.app.shopdodientu.model.TinhModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.model.XaModel;
import com.app.shopdodientu.room.dao.HuyenDao;
import com.app.shopdodientu.room.dao.TinhDao;
import com.app.shopdodientu.room.dao.XaDao;
import com.app.shopdodientu.room.database.DiaChiDatabase;
import com.app.shopdodientu.room.entity.HuyenEntity;
import com.app.shopdodientu.room.entity.TinhEntity;
import com.app.shopdodientu.room.entity.XaEntity;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.LoadingSettingDialog;
import com.app.shopdodientu.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_intro);
        login();
        SharedPreferences sharedPreferences = getSharedPreferences("address_setting", MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("isSetting_address", false)) {
            LoadingSettingDialog loadingDialog = new LoadingSettingDialog(IntroActivity.this);
            loadingDialog.show();
            ApiService apiService = ApiClient.getApiService();
            apiService.getAddress()
                    .enqueue(new Callback<List<TinhModel>>() {
                        @Override
                        public void onResponse(Call<List<TinhModel>> call, Response<List<TinhModel>> response) {
                            List<TinhModel> address = response.body();
                            if(address != null) {
                                addAddressToRoom(address);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isSetting_address", true);
                                editor.apply();
                                loadingDialog.dismiss();
                            }
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<List<TinhModel>> call, Throwable t) {
                        }
                    });
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000); // Đợi 2 giây trước khi chuyển sang SecondActivity
        }

    }

    private void login() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("check", false)) {
            String username = sharedPreferences.getString("username", null);
            String password = sharedPreferences.getString("password", null);
            ApiService login = ApiClient.getApiService();
            login.login(username, password)
                    .enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            UserModel userLogin = response.body();
                            if(userLogin != null) {
                                Constant.userLogin = userLogin;
                                ApiClient.login(username, password);
                            }
                            else {
                                ApiClient.restApiService();

                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            ApiClient.restApiService();
                        }
                    });
        }
    }

    private void addAddressToRoom(List<TinhModel> tinhModels) {
        DiaChiDatabase database = DiaChiDatabase.getInstance(IntroActivity.this);
        TinhDao tinhDao = database.tinhDao();
        HuyenDao huyenDao = database.huyenDao();
        XaDao xaDao = database.xaDao();
        for(TinhModel t : tinhModels) {
            String tinhId = t.getCode();
            TinhEntity tinhEntity = new TinhEntity(tinhId, t.getName());
            tinhEntity.setId(t.getCode());
            tinhDao.insertAll(tinhEntity);
            List<HuyenModel> huyenModels = t.getDistricts();
            for(HuyenModel h : huyenModels) {
                String huyenId = h.getCode();
                HuyenEntity huyenEntity = new HuyenEntity(huyenId, h.getName(), tinhId);
                huyenDao.insertAll(huyenEntity);
                List<XaModel> xaModels = h.getWards();
                for(XaModel x : xaModels) {
                    XaEntity xaEntity = new XaEntity(x.getCode(), x.getName(), huyenId);
                    xaDao.insertAll(xaEntity);
                }
            }
        }
    }
}