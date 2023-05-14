package com.app.shopdodientu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.util.LoadingDialog;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemCheckAdapter extends RecyclerView.Adapter<CartItemCheckAdapter.ViewHolder>{
    private Context context;
    private List<CartItemModel> cartItemModels;

    public CartItemCheckAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.shoporder_checking, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItemModel cartItemModel = cartItemModels.get(position);
        Glide.with(context)
                .load(cartItemModel.getImage())
                .into(holder.imvProduct);
        holder.tvPrice.setText(String.valueOf(cartItemModel.getUnitPrice()));
        holder.tvProductName.setText(cartItemModel.getProductName());
        holder.tvAmount.setText(String.valueOf(cartItemModel.getQuantity()));
        holder.tvTotal.setText(String.valueOf(cartItemModel.getQuantity()*cartItemModel.getUnitPrice()));
        if(cartItemModel.getStatus() != 1) {
            holder.btnDone.setVisibility(View.GONE);
            holder.btnCancel.setVisibility(View.GONE);
        }
        else {
            holder.btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadingDialog loadingDialog = new LoadingDialog(context);
                    loadingDialog.show();
                    ApiService apiService = ApiClient.getApiService();
                    apiService.conformCartItem(cartItemModel.getId(), 2)
                            .enqueue(new Callback<CartItemModel>() {
                                @Override
                                public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
                                    loadingDialog.dismiss();
                                    if(response.isSuccessful()) {
                                        if(response.body() != null) {
                                            cartItemModels.remove(position);
                                            notifyDataSetChanged();
                                        }
                                        else {
                                            Toast.makeText(context, "Sản phẩm đã bị xóa hoặc hết hàng", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    else {
                                        Toast.makeText(context, "Sản phẩm đã bị xóa hoặc hết hàng", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<CartItemModel> call, Throwable t) {
                                    Toast.makeText(context, "Sản phẩm đã bị xóa hoặc hết hàng", Toast.LENGTH_SHORT).show();
                                    loadingDialog.dismiss();
                                }
                            });
                }
            });
            holder.btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoadingDialog loadingDialog = new LoadingDialog(context);
                    loadingDialog.show();
                    ApiService apiService = ApiClient.getApiService();
                    apiService.conformCartItem(cartItemModel.getId(), 4)
                            .enqueue(new Callback<CartItemModel>() {
                                @Override
                                public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
                                    loadingDialog.dismiss();
                                    if(response.body() != null) {
                                        cartItemModels.remove(position);
                                        notifyDataSetChanged();
                                    }
                                    else {
                                        Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<CartItemModel> call, Throwable t) {
                                    loadingDialog.dismiss();
                                    Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                                }
                            });
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
        private TextView tvProductName;
        private TextView tvPrice;
        private TextView tvAmount;
        private TextView tvTotal;
        private TextView tvDetailOrder;
        private Button btnCancel, btnDone;


        public ViewHolder(final View itemView) {
            super(itemView);
            imvProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvDetailOrder = itemView.findViewById(R.id.tvDetailOrder);
            btnCancel = itemView.findViewById(R.id.btnCancel);
            btnDone = itemView.findViewById(R.id.btnDone);
        }
    }
}
