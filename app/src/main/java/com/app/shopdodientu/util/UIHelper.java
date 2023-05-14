package com.app.shopdodientu.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.CartActivity;
import com.app.shopdodientu.activity.CheckOutActivity;
import com.app.shopdodientu.activity.ShopTabLayout.HomeShopActivity;
import com.app.shopdodientu.activity.LoginActivity;
import com.app.shopdodientu.activity.MainActivity;
import com.app.shopdodientu.activity.MyAccountActivity;
import com.app.shopdodientu.activity.ProfileActivity;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.activity.SupportActivity;
import com.app.shopdodientu.activity.seller.AddProductActivity;
import com.app.shopdodientu.activity.seller.MainSellerActivity;
import com.app.shopdodientu.activity.seller.RegisterSellerActivity;
import com.app.shopdodientu.activity.seller.WelcomeSellerActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UIHelper {
    public static void fullscreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void gotoAccount(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(Constant.userLogin == null) {
                    intent = new Intent(context, LoginActivity.class);
                }
                else {
                    intent = new Intent(context, MyAccountActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoHome(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoCart(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoSupport(ImageView imv, Context context) {
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, SupportActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoCheckOUt(Button btn, Context context) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, CheckOutActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public static void logout(LinearLayout linear, TextView tv, ImageView imv, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiClient.restApiService();
                ApiService apiService = ApiClient.getApiService();
                apiService.logout()
                        .enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                            }
                        });
                SharedPreferences sharedPreferences = context.getSharedPreferences("dataLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                Constant.userLogin = null;
                tv.setText("LogIn");
                imv.setImageDrawable(null);
                imv.setBackgroundResource(R.drawable.login);
                Intent intent;
                intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }



    public static void gotoMainSeller(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MainSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }



    public static void gotoHomeShop(TextView tv, Context context, int sellerId) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, HomeShopActivity.class);
                intent.putExtra("sellerId", sellerId);
                context.startActivity(intent);
            }
        });
    }


//    public static void gotoAddProduct(TextView tv, Context context) {
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(context, AddProductActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }

//    public static void gotoSetUpStore(TextView tv, Context context) {
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(context, RegisterSellerActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }

    public static void gotoSearchByImageView(ImageView imv, Context context) {
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, SearchActivity.class);
                context.startActivity(intent);
            }
        });
    }
//    public static void gotoProfile(TextView tvProfile, Context context) {
//        tvProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(context, ProfileActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }

    public static void backtoProfile(ImageView imv, Context context) {
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);
            }
        });
    }


//    public static void gotoChangePassword(Button btn, Context context) {
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(context, ChangePasswordActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }
}
