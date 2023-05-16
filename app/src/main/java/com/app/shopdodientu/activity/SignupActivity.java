package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private TextView tvLogin;
    private EditText edtEmail;
    private EditText edtUsername;
    private TextInputEditText tiedPassword;
    private TextInputEditText tiedtConfirm;
    private EditText edtPhone;
    private Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_signup);
        MapItemView();
        gotoLogin();
        Signup();
    }

    private void MapItemView() {
        tvLogin = (TextView) findViewById(R.id.tvlogin);
        edtEmail = (EditText) findViewById(R.id.edtemail);
        edtUsername = (EditText) findViewById(R.id.edtusername);
        tiedPassword = (TextInputEditText) findViewById(R.id.tfpass);
        tiedtConfirm = (TextInputEditText) findViewById(R.id.tfconfirmpass);
        edtPhone = (EditText) findViewById(R.id.edtphone);
        btnSignup = (Button) findViewById(R.id.btnsignup);
    }
    private void gotoLogin() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tvLogin.getText().toString();
                SpannableString content = new SpannableString(text);
                content.setSpan(new UnderlineSpan(), 0, text.length(), 0);
                tvLogin.setText(content);
                tvLogin.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.purple_700)));


                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Signup() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String username = edtUsername.getText().toString();
                String password = tiedPassword.getText().toString();
                String confirm = tiedtConfirm.getText().toString();
                String phone = edtPhone.getText().toString();

                if (email.isEmpty() || !email.contains("@") || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignupActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty() || confirm.isEmpty())
                {
                    Toast.makeText(SignupActivity.this, "Dữ liệu không được trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirm)) {
                    Toast.makeText(SignupActivity.this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                    return;
                }
                LoadingDialog loadingDialog = new LoadingDialog(SignupActivity.this);
                loadingDialog.show();
                ApiService apiService = ApiClient.getApiService();
                apiService.signup(username, password, email, phone)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                UserModel userModel = response.body();
                                if(userModel != null) {
                                    Toast.makeText(SignupActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
                                    loadingDialog.dismiss();
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignupActivity.this, "Đăng ký tài thất bại", Toast.LENGTH_LONG).show();
                                    loadingDialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Toast.makeText(SignupActivity.this, "Đăng ký tài thất bại", Toast.LENGTH_LONG).show();
                                loadingDialog.dismiss();
                            }
                        });
            }
        });

    }


}