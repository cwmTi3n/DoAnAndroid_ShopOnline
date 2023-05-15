package com.app.shopdodientu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.model.CategoryModel;
import com.app.shopdodientu.room.entity.TinhEntity;

import java.util.List;

public class TinhAdapter extends RecyclerView.Adapter<TinhAdapter.ViewHolder>{
    private Context context;
    private List<TinhEntity> tinhEntitys;
    private OnTinhClickListener onTinhClickListener;

    public void setOnTinhClickListener(OnTinhClickListener onTinhClickListener) {
        this.onTinhClickListener = onTinhClickListener;
    }

    public TinhAdapter(Context context, List<TinhEntity> tinhEntitys) {
        this.context = context;
        this.tinhEntitys = tinhEntitys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.location, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TinhEntity tinh = tinhEntitys.get(position);
        holder.tvLocation.setText(tinh.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTinhClickListener.onTinhClick(tinh.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tinhEntitys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocation;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
    public interface OnTinhClickListener {
        void onTinhClick(String tinhId);
    }
}
