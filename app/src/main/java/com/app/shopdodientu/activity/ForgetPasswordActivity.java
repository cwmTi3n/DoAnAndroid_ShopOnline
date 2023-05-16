package com.app.shopdodientu.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    private String code;
    private ActivityResultLauncher<Intent> gotoCode = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        code = data.getStringExtra("code");
                        renderResult();

                    }
                }
            }
    );
    private TextView tvBack;
    private ImageView imgSupport;
    private EditText edtEmail;
    private Button btnNext, btnChange;
    private TextInputEditText tfpass, tfconfirmpass;
    private LinearLayout linearChangePass, linearUsername;
    private String username;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_forget_password);

        MapItemView();
        UIHelper.gotoSupport(imgSupport, this);
        TextViewBackClicked();
        ButtonNextClicked();
        TextWatcher();
    }

    private void renderResult() {
        linearUsername.setVisibility(View.GONE);
        linearChangePass.setVisibility(View.VISIBLE);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = String.valueOf(tfpass.getText());
                String cf = String.valueOf(tfconfirmpass.getText());
                if(password.equals(cf)) {
                    ApiService apiService = ApiClient.getApiService();
                    apiService.changePassword(username, password, code)
                            .enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                    if(response.body() != null) {
                                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void MapItemView() {
        tvBack = findViewById(R.id.tvBack);
        imgSupport = findViewById(R.id.imgSupport);
        edtEmail = findViewById(R.id.edtEmail);
        btnNext = findViewById(R.id.btnNext);
        tfpass = findViewById(R.id.tfpass);
        tfconfirmpass = findViewById(R.id.tfconfirmpass);
        btnChange = findViewById(R.id.btnChange);
        linearChangePass = findViewById(R.id.linearChangePass);
        linearUsername = findViewById(R.id.linearUsername);
    }

    private void TextViewBackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void ButtonNextClicked(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingDialog loadingDialog = new LoadingDialog(ForgetPasswordActivity.this);
                loadingDialog.show();
                username = String.valueOf(edtEmail.getText());
                ApiService apiService = ApiClient.getApiService();
                apiService.getOTP(username)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                loadingDialog.dismiss();
                                Intent intent1 = new Intent(ForgetPasswordActivity.this, EnterOTPActivity.class);
                                gotoCode.launch(intent1);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                loadingDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void TextWatcher(){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần xử lý ở đây
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ee4d2d")));
                    // Kích hoạt Button
                    btnNext.setEnabled(true);
                } else {
                    // Nếu không có nội dung trong EditText, thiết lập màu nền mặc định cho Button
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C6BAB8")));
                    // Vô hiệu hóa Button
                    btnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        };

        edtEmail.addTextChangedListener(textWatcher);
    }
}