package com.app.shopdodientu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.shopdodientu.activity.CartActivity;
import com.app.shopdodientu.activity.CatalogActivity;
import com.app.shopdodientu.activity.ChangePasswordActivity;
import com.app.shopdodientu.activity.HomeShopActivity;
import com.app.shopdodientu.activity.LoginActivity;
import com.app.shopdodientu.activity.MainActivity;
import com.app.shopdodientu.activity.MyAccountActivity;
import com.app.shopdodientu.activity.MyOrderActivity;
import com.app.shopdodientu.activity.ProductActivity;
import com.app.shopdodientu.activity.ProfileActivity;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.activity.seller.AddProductActivity;
import com.app.shopdodientu.activity.seller.MainSellerActivity;
import com.app.shopdodientu.activity.seller.RegisterSellerActivity;
import com.app.shopdodientu.activity.seller.WelcomeSellerActivity;
import com.app.shopdodientu.model.UserModel;

public class UIHelper {
    public static void fullscreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void gotoAccount(LinearLayout linear, UserModel userModel, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(userModel == null) {
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

    public static void gotoWelcomeStore(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, WelcomeSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }


    public static void gotoRegisterStore(Button button, Context context) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, RegisterSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoMainSellerByButton(Button btn, Context context) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MainSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoMainSellerByLinear(LinearLayout linear, Context context) {
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MainSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }

//    public static void gotoMyOrder(TextView tv, Context context) {
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent;
//                intent = new Intent(context, MyOrderActivity.class);
//                context.startActivity(intent);
//            }
//        });
//    }



    public static void gotoHomeShop(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, HomeShopActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public static void gotoProductShop(LinearLayout linear, Context context){
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, ProductActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoCatalogShop(LinearLayout linear, Context context){
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, CatalogActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoAddProduct(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, AddProductActivity.class);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoSetUpStore(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, RegisterSellerActivity.class);
                context.startActivity(intent);
            }
        });
    }

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

    public static void backtoMyAccount(TextView tv, Context context) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, MyAccountActivity.class);
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
