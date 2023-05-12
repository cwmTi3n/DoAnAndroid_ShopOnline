package com.app.shopdodientu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.CartItemModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private Context context;
    private List<CartItemModel> cartItemModels;

    public CartItemAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.product_in_cart, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemModel cartItemModel = cartItemModels.get(position);
        holder.tvPrice.setText(String.valueOf(cartItemModel.getUnitPrice()));
        holder.tvAmount.setText(String.valueOf(cartItemModel.getQuantity()));
        holder.tvProductName.setText(cartItemModel.getProductName());
        Glide.with(context)
                .load(cartItemModel.getImage())
                .into(holder.imvProduct);
    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvProduct;
        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvAmount;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
        }
    }
}
