package com.app.shopdodientu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.room.entity.XaEntity;

import java.util.List;

public class XaAdapter extends RecyclerView.Adapter<XaAdapter.ViewHolder>{
    private Context context;
    private List<XaEntity> xaEntities;

    public XaAdapter(Context context, List<XaEntity> xaEntities) {
        this.context = context;
        this.xaEntities = xaEntities;
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
        XaEntity xaEntity = xaEntities.get(position);
        holder.tvLocation.setText(xaEntity.getName());
    }

    @Override
    public int getItemCount() {
        return xaEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLocation;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
