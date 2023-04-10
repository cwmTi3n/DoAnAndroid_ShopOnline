package com.app.shopdodientu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.CategoryAdapter;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.model.UserModel;
import com.app.shopdodientu.util.UIHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner snPrice;
    private ImageView imvHome, imvProfile, imvCart, imvSupport, imvLogOut;
    private TextView tvHome, tvProfile, tvCart, tvSupport, tvLogout;
    private LinearLayout linearHome, linearProfile, linearCart, linearSupport, linearLogout;
    private TextView currentTextViewBottom;
    private ImageView currentImgBottom;
    private LinearLayout currentLinear;


    private RecyclerView rcvCategory;
    private List<CategoryModel> categories;
    private CategoryAdapter categoryAdapter;
    private UserModel userModel;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<ProductModel> products;
    private int page;
    private int total;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_main);
        userModel = (UserModel) getIntent().getSerializableExtra("user");
        MapItemView();
        getAllCategory();
        getLastProduct();
        UIHelper.gotoProfile(imvProfile, userModel, getApplicationContext());
        UIHelper.gotoHome(imvHome, this);
//        gotoProfile();
//        gotoHome();


        //BOTTOM
        currentTextViewBottom = tvHome;
        currentImgBottom = imvHome;
        currentLinear = linearHome;

        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearHome){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvHome);
                    SetEffectCurrentImgBottom(imvHome);
                    currentTextViewBottom = tvHome;
                    currentImgBottom = imvHome;
                    currentLinear = linearHome;
                }
            }
        });
        linearProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearProfile){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvProfile);
                    SetEffectCurrentImgBottom(imvProfile);
                    currentTextViewBottom = tvProfile;
                    currentImgBottom = imvProfile;
                    currentLinear = linearProfile;
                }
            }
        });

        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearCart){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvCart);
                    SetEffectCurrentImgBottom(imvCart);
                    currentTextViewBottom = tvCart;
                    currentImgBottom = imvCart;
                    currentLinear = linearCart;
                }
            }
        });

        linearSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearSupport){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvSupport);
                    SetEffectCurrentImgBottom(imvSupport);
                    currentTextViewBottom = tvSupport;
                    currentImgBottom = imvSupport;
                    currentLinear = linearSupport;
                }
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLinear != linearLogout){
                    if(currentLinear != null){
                        SetEffectLastTextViewBottom(currentTextViewBottom);
                        SetEffectLastImgBottom(currentImgBottom);
                    }
                    SetEffectCurrentTextViewBottom(tvLogout);
                    SetEffectCurrentImgBottom(imvLogOut);
                    currentTextViewBottom = tvLogout;
                    currentImgBottom = imvLogOut;
                    currentLinear = linearLogout;
                }
            }
        });
    }
    private void gotoProfile() {
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(userModel == null) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                else {
                    intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("user", userModel);
                }
                startActivity(intent);
            }
        });
    }

    private void gotoHome() {
        imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

    }


    private void MapItemView() {
        rcvCategory = (RecyclerView) findViewById(R.id.rcvcategory);
        rcvProduct = (RecyclerView) findViewById(R.id.rcvproduct);
        //snPrice = findViewById(R.id.snprice);
        imvHome = (ImageView) findViewById(R.id.imgHome);
        imvProfile = (ImageView) findViewById(R.id.imgProfile);
        imvCart = (ImageView) findViewById(R.id.imgCart);
        imvSupport = (ImageView) findViewById(R.id.imgSupport);
        imvLogOut = (ImageView) findViewById(R.id.imgLogout);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvProfile = (TextView) findViewById(R.id.tvProfile);
        tvCart = (TextView) findViewById(R.id.tvCart);
        tvSupport = (TextView) findViewById(R.id.tvSupport);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
        linearHome = (LinearLayout) findViewById(R.id.home);
        linearProfile = (LinearLayout) findViewById(R.id.profile);
        linearCart = (LinearLayout) findViewById(R.id.cart);
        linearSupport = (LinearLayout) findViewById(R.id.support);
        linearLogout = (LinearLayout) findViewById(R.id.logout);
    }

    private void SetEffectLastTextViewBottom(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.BLACK);
    }
    private void SetEffectCurrentTextViewBottom(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLastImgBottom(ImageView last){
        Bitmap bitmap;
        if (imvHome.equals(last)) {
            imvHome.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn1);
            imvHome.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvProfile.equals((last))){
            imvProfile.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2);
            imvProfile.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvCart.equals((last))){
            imvCart.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn3);
            imvCart.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvSupport.equals((last))){
            imvSupport.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn4);
            imvSupport.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else {
            imvLogOut.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn5);
            imvLogOut.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }

    }

    private void SetEffectCurrentImgBottom(ImageView current){
        Bitmap bitmap;
        if (imvHome.equals(current)) {
            imvHome.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn1_color);
            imvHome.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvProfile.equals((current))){
            imvProfile.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn2_color);
            imvProfile.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvCart.equals((current))){
            imvCart.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn3_color);
            imvCart.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else if (imvSupport.equals((current))){
            imvSupport.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn4_color);
            imvSupport.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }
        else {
            imvLogOut.setImageBitmap(null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_btn5_color);
            imvLogOut.setImageDrawable(new BitmapDrawable(getResources(), bitmap));
        }

    }

//    private void AddItemSpinnerPrice() {
//
//        String[] listCate = {"Price", "High", "Low"};
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        snPrice.setAdapter(adapter);
//
//
//        snPrice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedOption = parent.getItemAtPosition(position).toString();
//                // Do something with the selected option
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Do something when nothing is selected
//            }
//        });
//    }

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

    private void getLastProduct() {
        ApiService apiService = ApiClient.getApiService();
        apiService.lastProduct(0)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        products = pageProduct.getContent();
                        page = pageProduct.getIndex();
                        total = pageProduct.getTotal();
                        productAdapter = new ProductAdapter(getApplicationContext(), products);
                        rcvProduct.setHasFixedSize(true);
                        rcvProduct.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        rcvProduct.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
        //load more
        final NestedScrollView nestedScrollView = findViewById(R.id.ncvMain);
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // At bottom of NestedScrollView, load more data for RecyclerView
                    loadmoreProduct();
                }
            }
        });
    }
    private void loadmoreProduct() {
        ApiService apiService = ApiClient.getApiService();
        page = page + 1;
        if(page >= total) {
            return;
        }
        apiService.lastProduct(page)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        assert pageProduct != null;
                        products.addAll(pageProduct.getContent());
                        productAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

}