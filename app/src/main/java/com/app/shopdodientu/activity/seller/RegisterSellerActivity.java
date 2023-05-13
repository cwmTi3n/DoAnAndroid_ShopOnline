package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.AddressTabLayout.LocationActivity;
import com.app.shopdodientu.util.UIHelper;

public class RegisterSellerActivity extends AppCompatActivity {

    private ImageView imgBanner, imgAvatar;
    private TextView tvUploadBanner, tvUploadAvatar;

    private EditText edtShopName, editAddress, editEmail, edtPhone;
    private Button btnSave, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_register_seller);

        MapItemView();
        TextViewAddressClicked();
        UIHelper.gotoMainSellerByButton(btnSave, this);
    }

    private void MapItemView(){
        imgBanner = findViewById(R.id.imgBanner);
        imgAvatar = findViewById(R.id.imgAvatar);
        tvUploadBanner = findViewById(R.id.tvUploadBanner);
        tvUploadAvatar = findViewById(R.id.tvUploadAvatar);
        edtShopName = findViewById(R.id.edtShopName);
        editEmail = findViewById(R.id.editEmail);
        edtPhone = findViewById(R.id.edtPhone);
        editAddress = findViewById(R.id.editAddress);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);
    }

    private void TextViewAddressClicked() {
        editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterSellerActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
    }
}