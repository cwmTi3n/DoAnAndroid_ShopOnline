package com.app.shopdodientu.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.ShopTabLayout.HomeShopActivity;
import com.app.shopdodientu.activity.seller.UpdateProductActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.model.ProductModel;
import com.app.shopdodientu.util.Constant;
import com.app.shopdodientu.util.LoadingDialog;
import com.app.shopdodientu.util.UIHelper;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> gotoCart = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        if (Constant.userLogin != null) {
                            Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> gotoCheckout = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Hiển thị địa chỉ đã chọn trên giao diện thanh toán
                        if (Constant.userLogin != null) {
                            gotoCheckout();
                        }
                    }
                }
            }
    );

    private RatingBar ratingBar;
    private ImageView imgProduct, imgAvatar, imgCartProduct, imgBack;
    private TextView tvEdit, tvAmountSelled, tvPrice, tvNameproduct, tvDescription, tvShopName, tvamountProduct, tvviewShop, tvBuyNow;
    private RecyclerView rcvFeedback;
    private LinearLayout linearChat, linearAddToCart;
    private ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_product_detail);
        MapItemView();
        renderView();
        addToCart();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvviewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, HomeShopActivity.class);
                intent.putExtra("sellerId", productModel.getUserId());
                startActivity(intent);
            }
        });
        tvBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(productModel.getStock() < 1) {
                    Toast.makeText(ProductDetailActivity.this, "Sản phẩm tạm hết hàng", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(Constant.userLogin == null) {
                        Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                        gotoCheckout.launch(intent);
                    }
                    else {
                        gotoCheckout();
                    }
                }
            }
        });

        linearChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        imgCartProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(Constant.userLogin == null) {
                    intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                    gotoCart.launch(intent);
                }
                else {
                    intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void MapItemView() {
        ratingBar = findViewById(R.id.ratingBar);
        imgProduct = (ImageView) findViewById(R.id.imgproduct);
        imgAvatar = (ImageView) findViewById(R.id.imgavatar);
        tvAmountSelled = findViewById(R.id.tvAmountSelled);
        tvPrice = (TextView) findViewById(R.id.tvprice);
        tvNameproduct = (TextView) findViewById(R.id.tvnameproduct);
        tvDescription = (TextView) findViewById(R.id.tvdescription);
        tvShopName = (TextView) findViewById(R.id.tvshopName);
        tvamountProduct = findViewById(R.id.tvamountProduct);
        tvviewShop = findViewById(R.id.tvviewShop);
        rcvFeedback = findViewById(R.id.rcvFeedback);
        linearChat = findViewById(R.id.linearchat);
        linearAddToCart = findViewById(R.id.linearAddToCart);
        tvBuyNow = findViewById(R.id.tvBuyNow);
        tvEdit = findViewById(R.id.tvEdit);
        imgBack = findViewById(R.id.imgBack);
        imgCartProduct = findViewById(R.id.imgCartProduct);
    }
    private void gotoCheckout() {
        Intent intent = new Intent(ProductDetailActivity.this, CheckOutActivity.class);
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setProductId(productModel.getId());
        cartItemModel.setQuantity(1);
        cartItemModel.setProductName(productModel.getName());
        cartItemModel.setImage(productModel.getImage());
        cartItemModel.setUnitPrice(productModel.getPrice());
        cartItemModel.setShopName(productModel.getShopname());
        intent.putExtra("cartItem", cartItemModel);
        startActivity(intent);
    }

    private void renderView() {
        productModel = (ProductModel) getIntent().getSerializableExtra("product");
        if(Constant.userLogin != null && productModel.getUserId() == Constant.userLogin.getId() && Constant.userLogin.getRole().equals("SELLER")) {
            tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProductDetailActivity.this, UpdateProductActivity.class);
                    intent.putExtra("product", productModel);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else {
            tvEdit.setVisibility(View.GONE);
        }
        tvAmountSelled.setText("Đã bán " + String.valueOf(productModel.getAmount()));
        tvPrice.setText(String.valueOf(productModel.getPrice()));
        tvNameproduct.setText(productModel.getName());
        tvDescription.setText(productModel.getDescription());
        tvShopName.setText(productModel.getShopname());
        Glide.with(getApplicationContext())
                .load(productModel.getImage())
                .into(imgProduct);
        Glide.with(getApplicationContext())
                .load(productModel.getAvatarShop())
                .into(imgAvatar);
    }

    private void addToCart() {
        linearAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constant.userLogin != null) {
                    dialogAddToCart();
                }
                else {
                    Toast.makeText(ProductDetailActivity.this,"Bạn cần đăng nhập để thực hiện yêu cầu", Toast.LENGTH_SHORT).show();
                }
//                ApiService apiService = ApiClient.getApiService();
//                apiService.addToCart(productModel.getId(), 1)
//                        .enqueue(new Callback<CartItemModel>() {
//                            @Override
//                            public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
//                                if(response.body() != null) {
//                                    Toast.makeText(getApplicationContext(), "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<CartItemModel> call, Throwable t) {
//                                Toast.makeText(getApplicationContext(), "Thêm sản phẩm không thành công", Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });
    }

    private void dialogAddToCart() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_item_to_cart);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        EditText edtAmount = dialog.findViewById(R.id.edtAmount);
        TextView tvMinus = dialog.findViewById(R.id.tvMinus);
        TextView tvPlus = dialog.findViewById(R.id.tvPlus);
        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAmount.setText(String.valueOf(Integer.parseInt(edtAmount.getText().toString()) + 1));
            }
        });
        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(edtAmount.getText().toString());
                if(quantity > 1) {
                    edtAmount.setText(String.valueOf(quantity - 1));
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadingDialog loadingDialog = new LoadingDialog(ProductDetailActivity.this);
                loadingDialog.show();
                ApiService apiService = ApiClient.getApiService();
                int quantity = Integer.parseInt(edtAmount.getText().toString());
                apiService.addToCart(productModel.getId(), quantity)
                        .enqueue(new Callback<CartItemModel>() {
                            @Override
                            public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
                                if(response.body() != null) {
                                    loadingDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    loadingDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Sản phẩm tạm hết hàng", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<CartItemModel> call, Throwable t) {
                                loadingDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Sản phẩm tạm hết hàng", Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}