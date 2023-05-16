package com.app.shopdodientu.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.WaittingOrderInforActivity;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartModel;
import com.app.shopdodientu.util.LoadingDialog;
import com.bumptech.glide.Glide;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.tvDetailOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WaittingOrderInforActivity.class);
                intent.putExtra("cart", cartModel);
                context.startActivity(intent);
            }
        });
        if(cartModel.getStatus() == 1) {
            holder.btnCancel.setVisibility(View.VISIBLE);
            holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadingDialog loadingDialog = new LoadingDialog(context);
                    loadingDialog.show();
                    ApiService apiService = ApiClient.getApiService();
                    apiService.cancelOrder(cartModel.getId())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    loadingDialog.dismiss();
                                    cartModels.remove(cartModel);
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    loadingDialog.dismiss();
                                }
                            });
                }
            });
            holder.tvChat.setVisibility(View.GONE);
        }
        else if(cartModel.getStatus() == 2) {
        }
        else {
            holder.btnReBuy.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imvAvatar;
        private TextView tvBuyDate, tvAmount, tvTotalPrice, tvChat, tvDetailOrder;
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
            tvDetailOrder = itemView.findViewById(R.id.tvDetailOrder);
        }
    }
}
