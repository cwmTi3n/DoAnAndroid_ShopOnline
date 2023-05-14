package com.app.shopdodientu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.model.CartModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private Context context;
    private List<CartModel> cartModels;

    public CartAdapter(Context context, List<CartModel> cartModels) {
        this.context = context;
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.myorder, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel cartModel = cartModels.get(position);
        holder.tvTotalPrice.setText(String.valueOf(cartModel.getSumPrice()) + "VND");
        holder.tvAmount.setText(String.valueOf(cartModel.getSumProduct()) + " Products");
        Glide.with(context)
                .load(cartModel.getAvatar())
                .into(holder.imvAvatar);
        holder.tvBuyDate.setText(cartModel.getBuyDate());
        if(cartModel.getStatus() == 1) {
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.tvChat.setVisibility(View.VISIBLE);
        }
        else if(cartModel.getStatus() == 2) {
        }
        else {
            holder.btnReBuy.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvAvatar;
        private TextView tvBuyDate, tvAmount, tvTotalPrice, tvChat;
        private Button btnCancel, btnReBuy, btnReceived;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvAvatar = (ImageView) itemView.findViewById(R.id.imgProduct);
            tvBuyDate = (TextView) itemView.findViewById(R.id.tvTimeOrder);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvTotalPrice = itemView.findViewById(R.id.tvTotal);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnReceived = itemView.findViewById(R.id.btnConfirm);
            btnReBuy = itemView.findViewById(R.id.btnRepurchase);
            tvChat = itemView.findViewById(R.id.tvChat);
        }
    }
}
