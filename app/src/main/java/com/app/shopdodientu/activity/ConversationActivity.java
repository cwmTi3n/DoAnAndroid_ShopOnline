package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

import java.util.Map;

public class ConversationActivity extends AppCompatActivity {

    private ImageView imvBack, imvAvatarShop;
    private TextView tvShopName, tvMessage;
    private RecyclerView rcvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        UIHelper.fullscreen(this);
        setContentView(R.layout.activity_conversation);

        MapItemView();
    }

    private void MapItemView(){
        imvBack = findViewById(R.id.imvBack);
        imvAvatarShop = findViewById(R.id.imvAvatarShop);
        tvShopName = findViewById(R.id.tvShopName);
        tvMessage = findViewById(R.id.tvMessage);
        rcvMessage = findViewById(R.id.rcvMessage);
    }
}