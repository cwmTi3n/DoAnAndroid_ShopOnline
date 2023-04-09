package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvSignup;
    private Button btnLogin;
    private TextView edtUsername;
    private TextView edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MapItemView();
        gotoSignup();
        login();

    }


    private void MapItemView() {
        tvSignup = (TextView) findViewById(R.id.tvsignup);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        edtUsername = (EditText) findViewById(R.id.edtusername);
        //edtPassword = (EditText) findViewById(R.id.edtpass);
    }

    private void gotoSignup() {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                ApiService apiService = ApiClient.getApiLoginService(username, password);
                apiService.login(username, password)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                UserModel userModel = response.body();
                                if(userModel == null) {
                                    Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("user", userModel);
                                    startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Log.d("test", "no");
                            }
                        });
            }
        });

    }

}