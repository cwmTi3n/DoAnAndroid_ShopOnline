package com.app.shopdodientu.activity.seller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.ImageModel;

import java.util.List;

public class ImagesViewPageAdapter extends PagerAdapter {
    private List<ImageModel> imagesList;
    public ImagesViewPageAdapter(List<ImageModel>imagesList){
        this.imagesList=imagesList;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position)
    {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_images,container,false);
        ImageView imageView = view.findViewById(R.id.imgView);
        ImageModel images= imagesList.get(position);
        imageView.setImageResource(images.getImageId());
        container.addView(view);
        return view;
    }
    @Override
    public int getCount()
    {
        if(imagesList !=null)
        {
            return imagesList.size();
        }
        return 0;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object object)
    {
        return view== object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,@NonNull Object object)
    {
        container.removeView((View) object);
    }
}
