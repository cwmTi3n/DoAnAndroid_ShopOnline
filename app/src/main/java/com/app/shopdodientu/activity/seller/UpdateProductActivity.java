package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.app.shopdodientu.R;

public class UpdateProductActivity extends AppCompatActivity {

    private Spinner sncateName, snstatus;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        MapItemView();
        AddItemsToSpinnerCate();


    }

    private void MapItemView(){
        snstatus = findViewById(R.id.spinnerstatus);
        sncateName = findViewById(R.id.spinnercateName);
    }

    private void AddItemsToSpinnerCate(){
        String[] listCate = {"Select an item", "Item 1", "Item 2", "Item 3"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sncateName.setAdapter(adapter);
    }


}