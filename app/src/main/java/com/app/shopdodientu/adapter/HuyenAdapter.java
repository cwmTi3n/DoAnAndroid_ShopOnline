package com.app.shopdodientu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.room.entity.HuyenEntity;
import com.app.shopdodientu.room.entity.TinhEntity;

import java.util.List;

public class HuyenAdapter extends RecyclerView.Adapter<HuyenAdapter.ViewHolder>{
    private Context context;
    private List<HuyenEntity> huyenEntities;
    private OnHuyenClickListener onHuyenClickListener;

    public void setOnHuyenClickListener(OnHuyenClickListener onHuyenClickListener) {
        this.onHuyenClickListener = onHuyenClickListener;
    }

    public HuyenAdapter(Context context, List<HuyenEntity> huyenEntities) {
        this.context = context;
        this.huyenEntities = huyenEntities;
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
        HuyenEntity huyen = huyenEntities.get(position);
        holder.tvLocation.setText(huyen.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHuyenClickListener.onHuyenClick(huyen.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return huyenEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocation;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }

    public interface OnHuyenClickListener {
        void onHuyenClick(String huyenId);
    }
}
