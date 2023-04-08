package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvCategory;
    private ImageView imvProfile;
    private ImageView imvHome;
    private List<CategoryModel> categories;
    private CategoryAdapter categoryAdapter;
    private UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userModel = (UserModel) getIntent().getSerializableExtra("user");
        MapItemView();
        getAllCategory();
        gotoProfile();
        gotoHome();
    }
    private void gotoProfile() {
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userModel == null) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("user", userModel);
                    startActivity(intent);
                }
            }
        });
    }

    private void gotoHome() {
        imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("user", userModel);
                startActivity(intent);
            }
        });

    }


    private void MapItemView() {
        rcvCategory = (RecyclerView) findViewById(R.id.rcvcategory);
        imvProfile = (ImageView) findViewById(R.id.imgProfile);
        imvHome = (ImageView) findViewById(R.id.imgHome);
    }

    private void getAllCategory() {
        ApiService apiService = ApiClient.getApiService();
        apiService.getAllCategory()
                .enqueue(new Callback<List<CategoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                        categories = response.body();
                        categoryAdapter = new CategoryAdapter(getApplicationContext(), categories);
                        rcvCategory.setHasFixedSize(true);
                        rcvCategory.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rcvCategory.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                        Log.d("mainactivity", "call get all category fail");
                    }
                });
    }
}