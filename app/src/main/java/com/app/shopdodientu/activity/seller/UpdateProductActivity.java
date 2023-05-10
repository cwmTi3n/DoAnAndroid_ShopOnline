package com.app.shopdodientu.activity.seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.shopdodientu.R;

public class UpdateProductActivity extends AppCompatActivity {
    private TextView tvBack;
    private ImageView imgProduct;
    private EditText edtname, edtprice, edtstock, edtdescription;
    private RadioButton radioButtonOn, radioButtonOff;
    private Button btnSave;
    private Spinner sncateName;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        MapItemView();
        AddItemsToSpinnerCate();


    }

    private void MapItemView(){
        sncateName = findViewById(R.id.sncateName);
        tvBack = findViewById(R.id.tvBack);
        imgProduct = findViewById(R.id.imgProduct);
        edtname = findViewById(R.id.edtname);
        edtprice = findViewById(R.id.edtprice);
        edtstock = findViewById(R.id.edtstock);
        edtdescription = findViewById(R.id.edtdescription);
        radioButtonOn = findViewById(R.id.radioButtonOn);
        radioButtonOff = findViewById(R.id.radioButtonOff);
        btnSave = findViewById(R.id.btnSave);
    }

    private void AddItemsToSpinnerCate(){
        String[] listCate = {"Select an item", "Item 1", "Item 2", "Item 3"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sncateName.setAdapter(adapter);
    }


}