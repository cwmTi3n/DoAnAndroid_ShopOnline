package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class ForgetPasswordActivity extends AppCompatActivity {
    private TextView tvBack;
    private ImageView imgSupport;
    private EditText edtEmail;
    private Button btnNext;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_forget_password);

        MapItemView();
        TextViewBackClicked();
        ButtonNextClicked();
        TextWatcher();
    }

    private void MapItemView() {
        tvBack = findViewById(R.id.tvBack);
        imgSupport = findViewById(R.id.imgSupport);
        edtEmail = findViewById(R.id.edtEmail);
        btnNext = findViewById(R.id.btnNext);
    }

    private void TextViewBackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ButtonNextClicked(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(ForgetPasswordActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_send_otp);
                TextView tvback = dialog.findViewById(R.id.tvBack);
                dialog.show();
                tvback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.dismiss();
                intent = new Intent(ForgetPasswordActivity.this, EnterOTPActivity.class);
                startActivity(intent);
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