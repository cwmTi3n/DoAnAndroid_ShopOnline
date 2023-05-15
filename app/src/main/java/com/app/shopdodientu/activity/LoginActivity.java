package com.app.shopdodientu.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvSignup;
    private TextView tvForgetPass;
    private Button btnLogin;
    private EditText edtUsername;
    private TextInputEditText tiedPassword;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_login);
        MapItemView();
        gotoSignup();
        login();
        forgetPass();

    }


    private void MapItemView() {
        tvSignup = (TextView) findViewById(R.id.tvsignup);
        tvForgetPass = (TextView) findViewById(R.id.tvforgetpass);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        edtUsername = (EditText) findViewById(R.id.edtusername);
        tiedPassword = (TextInputEditText) findViewById(R.id.tfpass);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("username", ""));
        tiedPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("check", false));
    }

    private void gotoSignup() {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tvSignup.getText().toString();
                SpannableString content = new SpannableString(text);
                content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
                tvSignup.setText(content);
                tvSignup.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.purple_700)));

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
                String password = tiedPassword.getText().toString();
                if (cbRemember.isChecked()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("check", true);
                    editor.apply();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("username");
                    editor.remove("password");
                    editor.remove("check");
                    editor.apply();
                }

                callApiLogin(username, password);
            }
        });
    }
    private void callApiLogin(String username, String password) {
        LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);
        loadingDialog.show();
        ApiService apiService = ApiClient.login(username, password);
        apiService.login(username, password)
                .enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        loadingDialog.dismiss();
                        UserModel userModel = response.body();
                        if(userModel == null) {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                            ApiClient.restApiService();
                        }
                        else {
                            Constant.userLogin = userModel;
                            Intent resultIntent = new Intent();
                            setResult(Activity.RESULT_OK, resultIntent);
                            finish();
                        }

                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        loadingDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                        ApiClient.restApiService();
                    }
                });
    }

    private void forgetPass(){

        tvForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tvForgetPass.getText().toString();
                SpannableString content = new SpannableString(text);
                content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
                tvForgetPass.setText(content);
                tvForgetPass.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.purple_700)));

                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}