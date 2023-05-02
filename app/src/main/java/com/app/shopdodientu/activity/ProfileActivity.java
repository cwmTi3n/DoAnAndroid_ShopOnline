package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.UIHelper;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvFullname, tvEmail;
    private ImageView imgAvatar;
    private TextInputEditText tifFullname, tifUsername, tifEmail, tifPhone;
    private Button btnChangepass, btnUpdate;
    //BOTTOM
    private LinearLayout linearCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        MapItemView();

        UIHelper.gotoChangePassword(btnChangepass, this);

        //BOTTOM
        UIHelper.gotoCart(linearCart, this);
        renderView();
        updateFullname();
    }

    private void MapItemView() {

        btnChangepass = (Button) findViewById(R.id.btnchangepass);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        tvFullname = (TextView) findViewById(R.id.tvfullname);
        tvEmail = (TextView) findViewById(R.id.tvemail);
        imgAvatar = (ImageView) findViewById(R.id.imgavatar);
        btnUpdate = (Button) findViewById(R.id.btnupdate);
        tifFullname = (TextInputEditText) findViewById(R.id.tffullname);
    }

    private void renderView() {
        UserModel user = (UserModel) getIntent().getSerializableExtra("user");
        tvFullname.setText(user.getFullname());
        tvEmail.setText(user.getEmail());
        Glide.with(getApplicationContext())
                .load(user.getAvatar())
                .into(imgAvatar);
    }

    private void updateFullname() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = String.valueOf(tifFullname.getText());
                UserModel userModel = (UserModel) getIntent().getSerializableExtra("user");
                ApiService apiService = ApiClient.getApiService();
                apiService.updateName(userModel.getId(), fullname)
                        .enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                Log.d("Test", "Ok");
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Log.d("Test", "Fail");
                            }
                        });
            }
        });
    }

}