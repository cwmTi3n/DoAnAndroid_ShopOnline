package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.adapter.ProductAdapter;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.model.PageModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.UIHelper;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private TextView tvRelated, tvLatest, tvBestSeller, tvPrice, tvSearchFor;
    private TextView lineRelated, lineLatest, lineBest, lineHoriRelated, lineHoriLatest, lineHoriBest, lineHoriPrice;
    private TextView currentTextView, currentLine, currentLineHori;
    private String keyword;
    private int categoryId;
    private String orderby;
    private int page;
    private int total;
    private CategoryModel categoryModel;
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<ProductModel> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_search);

        MapItemView();

        //TOP
        currentTextView = tvRelated;
        currentLine = lineRelated;
        currentLineHori = lineHoriRelated;
        TextViewBestSellerClicked();
        TextViewLatestClicked();
        TextViewRelatedClick();
        TextViewPriceClicked();
        renderView();

        //load more
        final NestedScrollView nestedScrollView = findViewById(R.id.ncvSearch);
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

    private void renderView() {
        if(keyword != null) {
            tvSearchFor.setText("Search for \"" + keyword + "\"");
        }
        if(categoryModel != null) {
            tvSearchFor.setText("Search for \"" + categoryModel.getName() + "\"");
            categoryId = categoryModel.getId();
        }
        getProducts();

    }

    private void TextViewRelatedClick() {
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvRelated) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvRelated);
                    SetEffectLineCurrentTextViewTop(lineRelated, lineHoriRelated);
                    currentTextView = tvRelated;
                    currentLine = lineRelated;
                    currentLineHori = lineHoriRelated;
                }
                orderby = null;
                getProducts();
            }
        });
    }

    private void TextViewLatestClicked() {
        tvLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvLatest) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvLatest);
                    SetEffectLineCurrentTextViewTop(lineLatest, lineHoriLatest);
                    currentTextView = tvLatest;
                    currentLine = lineLatest;
                    currentLineHori = lineHoriLatest;
                }
                orderby = "desccreateDate";
                getProducts();
            }
        });
    }

    private void TextViewBestSellerClicked() {
        tvBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTextView != tvBestSeller) {
                    if (currentTextView != null) {
                        SetEffectLastTextViewTop(currentTextView);
                        SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                    }
                    if(currentTextView == tvPrice){
                        SetDrawablePriceMove();
                    }
                    SetEffectCurrentTextViewTop(tvBestSeller);
                    SetEffectLineCurrentTextViewTop(lineBest, lineHoriBest);
                    currentTextView = tvBestSeller;
                    currentLine = lineBest;
                    currentLineHori = lineHoriBest;
                }
                orderby = "descamount";
                getProducts();
            }
        });
    }

    private void TextViewPriceClicked(){
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTextView != null) {
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine, currentLineHori);
                }
                SetDrawablePriceClicked();
                SetEffectCurrentTextViewTop(tvPrice);
                lineHoriPrice.setBackgroundColor(Color.parseColor("#fb5533"));
                currentTextView = tvPrice;
                currentLineHori = lineHoriPrice;
            }
        });
    }


    private void MapItemView(){
        tvRelated = (TextView) findViewById(R.id.tvrelated);
        tvLatest = (TextView) findViewById(R.id.tvlatest);
        tvBestSeller = (TextView) findViewById(R.id.tvbestseller);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        lineRelated = (TextView) findViewById(R.id.lineRelated);
        lineLatest = (TextView) findViewById(R.id.lineLatest);
        lineBest = (TextView) findViewById(R.id.lineBestseller);

        lineHoriRelated = (TextView) findViewById(R.id.lineHoriRelated);
        lineHoriLatest = (TextView) findViewById(R.id.lineHoriLatest);
        lineHoriBest = (TextView) findViewById(R.id.lineHoriBest);
        lineHoriPrice = (TextView) findViewById(R.id.lineHoriPrice);
        tvSearchFor = (TextView) findViewById(R.id.tvsearchfor);
        keyword = getIntent().getStringExtra("keyword");
        categoryModel = (CategoryModel) getIntent().getSerializableExtra("category");
        rcvProduct = (RecyclerView) findViewById(R.id.rcvproduct);

    }



    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.parseColor("#8B7C7C"));
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#fb5533"));
    }
    private void SetEffectLineCurrentTextViewTop(TextView line, TextView lineHori){
        line.setBackgroundColor(Color.parseColor("#fb5533"));
        lineHori.setBackgroundColor(Color.parseColor("#fb5533"));
    }
    private void SetEffectLineLastTextViewTop(TextView line, TextView lineHori) {
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
        lineHori.setBackgroundColor(Color.parseColor("#F1E6E6"));
    }
    private void SetDrawablePriceClicked(){
        Drawable[] drawables = tvPrice.getCompoundDrawables();
        Drawable rightDrawable = drawables[2];
        Drawable myDrawable = getResources().getDrawable(R.drawable.unfold);
        Drawable drawableUpArrow = getResources().getDrawable(R.drawable.up_arrow);
        Drawable drawableDownArrow = getResources().getDrawable(R.drawable.down_arrow);


        if (rightDrawable != null && myDrawable != null && rightDrawable.getConstantState().equals(myDrawable.getConstantState())) {
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
            orderby = "price";
            getProducts();

        } else if (rightDrawable != null && drawableUpArrow != null && rightDrawable.getConstantState().equals(drawableUpArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableDownArrow, null);
            orderby = "descprice";
            getProducts();

        } else if (rightDrawable != null && drawableDownArrow != null && rightDrawable.getConstantState().equals(drawableDownArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
            orderby = "price";
            getProducts();
        }
    }

    private void SetDrawablePriceMove(){
        Drawable drawableRight = getResources().getDrawable(R.drawable.unfold);
        tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableRight , null);
    }

    private void loadmoreProduct() {
        page = page + 1;
        if(page >= total) {
            return;
        }
        ApiService apiService = ApiClient.getApiService();
        apiService.findProduct(categoryId, keyword, orderby, page)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        PageModel<ProductModel> pageProduct = response.body();
                        if(pageProduct != null) {
                            products.addAll(pageProduct.getContent());
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

    private void getProducts() {
        page = 0;
        ApiService apiService = ApiClient.getApiService();
        apiService.findProduct(categoryId, keyword, orderby, page)
                .enqueue(new Callback<PageModel<ProductModel>>() {
                    @Override
                    public void onResponse(Call<PageModel<ProductModel>> call, Response<PageModel<ProductModel>> response) {
                        if(response.body() != null) {
                            total = response.body().getTotal();
                            products = response.body().getContent();
                            productAdapter = new ProductAdapter(getApplicationContext(), products);
                            rcvProduct.setHasFixedSize(true);
                            rcvProduct.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
                            rcvProduct.setAdapter(productAdapter);
                            productAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageModel<ProductModel>> call, Throwable t) {

                    }
                });
    }

}