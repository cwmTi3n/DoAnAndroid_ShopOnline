package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SignupActivity extends AppCompatActivity {
    private TextView tvLogin;
    private EditText edtEmail;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        MapItemView();
        gotoLogin();
        Signup();
    }

    private void MapItemView() {
        tvLogin = (TextView) findViewById(R.id.tvlogin);
        edtEmail = (EditText) findViewById(R.id.edtemail);
        edtUsername = (EditText) findViewById(R.id.edtusername);
        edtPassword = (EditText) findViewById(R.id.edtpass);
        btnSignup = (Button) findViewById(R.id.btnlogin);
    }
    private void gotoLogin() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Signup() {
        String email = edtEmail.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService = ApiClient.getApiService();
                apiService.signup(username, password, email, "0000000000")
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                UserModel userModel = response.body();
                                if(userModel != null) {
                                    Toast.makeText(SignupActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(SignupActivity.this, "Đăng ký tài thất bại", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Toast.makeText(SignupActivity.this, "Đăng ký tài thất bại", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

    }


}