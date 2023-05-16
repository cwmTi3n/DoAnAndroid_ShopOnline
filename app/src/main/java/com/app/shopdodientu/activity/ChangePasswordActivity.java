package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private ImageView imvBack;
    private TextView tvChangePw;
    private TextInputEditText tifOldpw, tifNewpw, tifConform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_change_password);
        MapItemView();
        changPw();
    }

    private void MapItemView(){
        tifOldpw = (TextInputEditText) findViewById(R.id.tfOldpw);
        tifNewpw = (TextInputEditText) findViewById(R.id.tfNewpw);
        tifConform = (TextInputEditText) findViewById(R.id.tfConform);
        tvChangePw = (TextView) findViewById(R.id.tvChangPw);
    }

    private void changPw() {
        tvChangePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(ChangePasswordActivity.this);
                loadingDialog.show();
                String oldpw = String.valueOf(tifOldpw.getText());
                String newpw = String.valueOf(tifNewpw.getText());
                String conform = String.valueOf(tifConform.getText());
                if(conform.equals(newpw)) {
                    int id = Constant.userLogin.getId();
                    ApiService apiService = ApiClient.getApiService();
                    apiService.updatePw(id, oldpw, newpw)
                            .enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                    loadingDialog.dismiss();
                                    UserModel userModel = response.body();
                                    if(userModel != null) {
                                        ApiClient.login(userModel.getUsername(), newpw);
                                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                                        loadingDialog.dismiss();

                                    }
                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                    Toast.makeText(ChangePasswordActivity.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }
                            });
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this,"Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }
            }
        });
    }

}