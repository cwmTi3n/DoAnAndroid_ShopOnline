package com.app.shopdodientu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.app.shopdodientu.activity.LoginActivity;
import com.app.shopdodientu.activity.ProfileActivity;
import com.app.shopdodientu.model.UserModel;

public class UIHelper {
    public static void fullscreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void gotoProfile(ImageView imvProfile, UserModel userModel, Context context) {
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(userModel == null) {
                    intent = new Intent(context, LoginActivity.class);
                }
                else {
                    intent = new Intent(context, ProfileActivity.class);
                    intent.putExtra("user", userModel);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public static void gotoHome(ImageView imvHome, Activity activity) {
        imvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
                activity.startActivity(activity.getIntent());
            }
        });

    }
}
