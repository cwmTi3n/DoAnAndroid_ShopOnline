package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.MyAccountActivity;

import org.w3c.dom.Text;

public class WelcomeSellerActivity extends AppCompatActivity {
    private TextView tvBack;
    private Button btnRegister;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_seller);

        tvBack = (TextView) findViewById(R.id.tvBack);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WelcomeSellerActivity.this, MyAccountActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(WelcomeSellerActivity.this, RegisterSellerActivity.class);
                startActivity(intent);
            }
        });



    }
}