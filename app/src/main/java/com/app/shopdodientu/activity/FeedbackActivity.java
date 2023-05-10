package com.app.shopdodientu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.app.shopdodientu.R;

import org.w3c.dom.Text;

public class FeedbackActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private TextView tvLevelRating, tvNameAccountFeedback, tvBack, tvSend, tvProductName;
    private Switch switchName;
    private ImageView imgProduct, imgFeedback1, imgFeedback2;
    private EditText edtFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        MapItemView();
        RatingBarChange();
        SwitchChange();
    }

    private void MapItemView(){
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvLevelRating = (TextView) findViewById(R.id.tvLevel);
        tvNameAccountFeedback = (TextView) findViewById(R.id.tvSetName);
        switchName = (Switch) findViewById(R.id.switchName);
        tvBack = findViewById(R.id.tvBack);
        tvSend = findViewById(R.id.tvSend);
        tvProductName = findViewById(R.id.tvProductName);
        imgProduct = findViewById(R.id.imgProduct);
        imgFeedback1 = findViewById(R.id.imgFeedback1);
        imgFeedback2 = findViewById(R.id.imgFeedback2);
        edtFeedback = findViewById(R.id.edtFeedback);
    }

    private void RatingBarChange(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating = ratingBar.getRating();

                if(rating <= 1 && rating >0){
                    tvLevelRating.setText("Bad");
                }
                else if(rating > 1 && rating <= 2){
                    tvLevelRating.setText("Unsatisfied");
                }
                else if(rating > 2 && rating <= 3){
                    tvLevelRating.setText("Normal");
                }
                else if(rating > 3 && rating <= 4){
                    tvLevelRating.setText("Satisfied");
                }
                else {
                    tvLevelRating.setText("Wonderful");
                }
            }
        });
    }

    private void SwitchChange(){
        switchName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    tvNameAccountFeedback.setText("Account name will show up as s***");
                }
                else {
                    tvNameAccountFeedback.setText("Account name will show up as NAME");
                }
            }
        });
    }
}