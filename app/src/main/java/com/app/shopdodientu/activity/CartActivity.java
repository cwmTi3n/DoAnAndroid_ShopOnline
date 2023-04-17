package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.app.shopdodientu.R;
import com.app.shopdodientu.util.UIHelper;

public class CartActivity extends AppCompatActivity {
    private ImageView imvDeleteAll;

    //BOTTOM
    private LinearLayout linearCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        MapItemView();
        DeleteAllClicked();

        UIHelper.gotoCart(linearCart, this);
    }

    private void MapItemView(){

        imvDeleteAll = (ImageView) findViewById(R.id.imvDeleteAll);
        linearCart = (LinearLayout) findViewById(R.id.cart);
    }

    private void DeleteAllClicked() {
        imvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(CartActivity.this, imvDeleteAll);
                popupMenu.getMenuInflater().inflate(R.menu.menu_delete_cart, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                Toast.makeText(CartActivity.this, "Deleted All", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                // Hiển thị PopupMenu
                popupMenu.show();
            }

        });
    }
}