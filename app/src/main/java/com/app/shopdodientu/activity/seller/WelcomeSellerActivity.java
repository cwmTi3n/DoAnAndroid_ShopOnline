package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.MyAccountActivity;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

public class WelcomeSellerActivity extends AppCompatActivity {
    private TextView tvBack;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_welcome_seller);

        tvBack = (TextView) findViewById(R.id.tvBack);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        UIHelper.gotoRegisterStore(btnRegister, this);

        TextViewBackClicked();

    }

    private void TextViewBackClicked(){
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}