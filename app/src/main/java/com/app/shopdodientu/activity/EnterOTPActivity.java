package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class EnterOTPActivity extends AppCompatActivity {
    private TextView tvBack, tvEmail;
    private EditText edtNumber1, edtNumber2, edtNumber3, edtNumber4, edtNumber5, edtNumber6;
    private Button btnNext;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        MapItemView();
        ButtonNextClicked();
        TextViewBackClick();
        RequestFocusAndOneChar();
        TextWatcher();
    }

    private void MapItemView(){
        tvBack = findViewById(R.id.tvBack);
        tvEmail = findViewById(R.id.tvEmail);
        edtNumber1 = findViewById(R.id.edtNumber1);
        edtNumber2 = findViewById(R.id.edtNumber2);
        edtNumber3 = findViewById(R.id.edtNumber3);
        edtNumber4 = findViewById(R.id.edtNumber4);
        edtNumber5 = findViewById(R.id.edtNumber5);
        edtNumber6 = findViewById(R.id.edtNumber6);
        btnNext = findViewById(R.id.btnNext);
    }

    private void TextViewBackClick(){
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
                intent = new Intent(EnterOTPActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TextWatcher(){
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ee4d2d")));
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C6BAB8")));
                    btnNext.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        edtNumber6.addTextChangedListener(textWatcher);
    }

    private void RequestFocusAndOneChar(){
        edtNumber1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    edtNumber2.setEnabled(true);
                    edtNumber2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        edtNumber2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    edtNumber3.setEnabled(true);
                    edtNumber3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        edtNumber3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    edtNumber4.setEnabled(true);
                    edtNumber4.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        edtNumber4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    edtNumber5.setEnabled(true);
                    edtNumber5.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });

        edtNumber5.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1) {
                    edtNumber6.setEnabled(true);
                    edtNumber6.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });
    }
}