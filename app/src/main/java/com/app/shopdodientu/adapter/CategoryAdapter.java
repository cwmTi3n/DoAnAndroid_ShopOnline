package com.app.shopdodientu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.model.CategoryModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private Context context;
    private List<CategoryModel> categoryModels;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModels.get(position);
        holder.tvCategory.setText(categoryModel.getName());
        Glide.with(context)
                .load(categoryModel.getImage())
                .into(holder.imvCategory);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvCategory;
        private TextView tvCategory;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvCategory = (ImageView) itemView.findViewById(R.id.categoryIView);
            tvCategory = (TextView) itemView.findViewById(R.id.tvnameCate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SearchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("category", categoryModels.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
