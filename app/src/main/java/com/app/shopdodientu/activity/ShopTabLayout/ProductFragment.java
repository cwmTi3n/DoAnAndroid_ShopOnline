package com.app.shopdodientu.activity.ShopTabLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.shopdodientu.R;
import com.app.shopdodientu.databinding.FragmentDistrictBinding;
import com.app.shopdodientu.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {

    FragmentProductBinding binding;
    TextView tvRelated, lineRelated, tvLatest, lineLatest, tvBest, lineBest, tvPrice;
    TextView currentTextView, currentLine;
    public ProductFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding = FragmentProductBinding.inflate(inflater, container, false);

        MapItemView();
        currentTextView = tvRelated;
        currentLine = lineRelated;
        TextViewRelatedClicked();
        TextViewLatestClicked();
        TextViewBestClicked();
        TextViewPriceClicked();

        //vi tri load du lieu: recyclerview
        return binding.getRoot();
    }

    private void MapItemView(){
        tvRelated = binding.getRoot().findViewById(R.id.tvRelated);
        lineRelated = binding.getRoot().findViewById(R.id.lineRelated);
        tvLatest = binding.getRoot().findViewById(R.id.tvLatest);
        lineLatest  = binding.getRoot().findViewById(R.id.lineLatest);
        tvBest = binding.getRoot().findViewById(R.id.tvBest);
        lineBest = binding.getRoot().findViewById(R.id.lineBest);
        tvPrice = binding.getRoot().findViewById(R.id.tvPrice);
    }

    private void TextViewRelatedClicked(){
        tvRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvRelated){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvRelated);
                SetEffectLineCurrentTextViewTop(lineRelated);
                currentTextView = tvRelated;
                currentLine = lineRelated;
            }
        });
    }

    private void TextViewLatestClicked(){
        tvLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvLatest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvLatest);
                SetEffectLineCurrentTextViewTop(lineLatest);
                currentTextView = tvLatest;
                currentLine = lineLatest;
            }
        });
    }

    private void TextViewBestClicked(){
        tvBest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentTextView != tvBest){
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                if(currentTextView == tvPrice){
                    SetDrawablePriceMove();
                }
                SetEffectCurrentTextViewTop(tvBest);
                SetEffectLineCurrentTextViewTop(lineBest);
                currentTextView = tvBest;
                currentLine = lineBest;
            }
        });
    }

    private void TextViewPriceClicked(){
        tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTextView != null) {
                    SetEffectLastTextViewTop(currentTextView);
                    SetEffectLineLastTextViewTop(currentLine);
                }
                SetDrawablePriceClicked();
                SetEffectCurrentTextViewTop(tvPrice);
                currentTextView = tvPrice;
            }
        });
    }

    private void SetEffectLastTextViewTop(TextView last){
        last.setTypeface(null, Typeface.NORMAL);
        last.setTextColor(Color.parseColor("#8B7C7C"));
    }
    private void SetEffectCurrentTextViewTop(TextView current){
        current.setTypeface(null, Typeface.BOLD);
        current.setTextColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineCurrentTextViewTop(TextView line){
        line.setBackgroundColor(Color.parseColor("#FFA500"));
    }
    private void SetEffectLineLastTextViewTop(TextView line) {
        line.setBackgroundColor(Color.parseColor("#F1E6E6"));
    }

    private void SetDrawablePriceClicked(){
        Drawable[] drawables = tvPrice.getCompoundDrawables();
        Drawable rightDrawable = drawables[2];
        Drawable myDrawable = getResources().getDrawable(R.drawable.unfold);
        Drawable drawableUpArrow = getResources().getDrawable(R.drawable.up_arrow);
        Drawable drawableDownArrow = getResources().getDrawable(R.drawable.down_arrow);


        if (rightDrawable != null && myDrawable != null && rightDrawable.getConstantState().equals(myDrawable.getConstantState())) {
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);

        } else if (rightDrawable != null && drawableUpArrow != null && rightDrawable.getConstantState().equals(drawableUpArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableDownArrow, null);

        } else if (rightDrawable != null && drawableDownArrow != null && rightDrawable.getConstantState().equals(drawableDownArrow.getConstantState())){
            tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableUpArrow, null);
        }
    }
    private void SetDrawablePriceMove(){
        Drawable drawableRight = getResources().getDrawable(R.drawable.unfold);
        tvPrice.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawableRight , null);
    }
}
