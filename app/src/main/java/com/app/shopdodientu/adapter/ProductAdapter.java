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
import com.app.shopdodientu.activity.ProductDetailActivity;
import com.app.shopdodientu.model.ProductModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context context;
    private List<ProductModel> productModels;

    public ProductAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.product, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel productModel = productModels.get(position);
        holder.tvProductname.setText(productModel.getName());
        holder.tvProductprice.setText(String.valueOf(productModel.getPrice()));
        holder.tvShop.setText(productModel.getShopname());
        holder.tvAmount.setText(String.valueOf("Đã bán " +  productModel.getAmount()));
        Glide.with(context)
                .load(productModel.getImage())
                .into(holder.imvProduct);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvProduct;
        private TextView tvProductname, tvProductprice, tvShop, tvAmount;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvProduct = (ImageView) itemView.findViewById(R.id.productIView);
            tvProductname = (TextView) itemView.findViewById(R.id.tvproductName);
            tvProductprice = (TextView) itemView.findViewById(R.id.tvprice);
            tvShop = (TextView) itemView.findViewById(R.id.tvshopName);
            tvAmount = itemView.findViewById(R.id.tvamount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("product", productModels.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
