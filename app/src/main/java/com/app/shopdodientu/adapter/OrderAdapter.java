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
import com.app.shopdodientu.activity.FeedbackActivity;
import com.app.shopdodientu.model.CartItemModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private Context context;
    private List<CartItemModel> cartItemModels;

    public OrderAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.product_in_checkout_delivered, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemModel cartItemModel = cartItemModels.get(position);
        holder.tvProductname.setText(cartItemModel.getProductName());
        Glide.with(context)
                .load(cartItemModel.getImage())
                .into(holder.imvProduct);
        holder.tvQuantity.setText(String.valueOf(cartItemModel.getQuantity()));
        holder.tvPrice.setText(String.valueOf(cartItemModel.getUnitPrice()));
        holder.tvShopname.setText(cartItemModel.getShopName());
        if(cartItemModel.getStatus() != 3) {
            holder.tvFeedback.setVisibility(View.GONE);
        }
        else {
            holder.tvFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FeedbackActivity.class);
                    intent.putExtra("productname", cartItemModel.getProductName());
                    intent.putExtra("productId", cartItemModel.getProductId());
                    intent.putExtra("image", cartItemModel.getImage());
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    //Goto feedback;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvProduct;
        private TextView tvProductname, tvPrice, tvQuantity, tvShopname, tvFeedback;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvProduct = itemView.findViewById(R.id.imgProduct);
            tvProductname = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvAmount);
            tvShopname = itemView.findViewById(R.id.tvShopName);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
        }
    }
}
