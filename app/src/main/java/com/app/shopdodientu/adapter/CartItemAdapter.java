package com.app.shopdodientu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.api.client.ApiClient;
import com.app.shopdodientu.api.service.ApiService;
import com.app.shopdodientu.model.CartItemModel;
import com.app.shopdodientu.util.Constant;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder>{
    private Context context;
    private List<CartItemModel> cartItemModels;
    private List<CartItemModel> cartItemSelects;
    private boolean isChecked;
    private double totalPrice = 0.0;
    private OnTotalAmountChangedListener onTotalAmountChangedListener;

    public CartItemAdapter(Context context, List<CartItemModel> cartItemModels) {
        this.context = context;
        this.cartItemModels = cartItemModels;
        cartItemSelects = new ArrayList<>();
        isChecked = false;
    }

    public List<CartItemModel> getCartItemSelects() {
        return cartItemSelects;
    }
    public List<Integer> getCartItemIds() {
        List<Integer> cartItemIds = new ArrayList<>();
        for(CartItemModel ci : cartItemSelects) {
            cartItemIds.add(ci.getId());
        }
        return cartItemIds;
    }

    public void setOnTotalAmountChangedListener(OnTotalAmountChangedListener onTotalAmountChangedListener) {
        this.onTotalAmountChangedListener = onTotalAmountChangedListener;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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
        holder.cbSelect.setChecked(isChecked);
        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    cartItemSelects.add(cartItemModel);
                    totalPrice += cartItemModel.getUnitPrice()*cartItemModel.getQuantity();
                }
                else {
                    cartItemSelects.remove(cartItemModel);
                    totalPrice -= cartItemModel.getUnitPrice()*cartItemModel.getQuantity();
                }
                if (onTotalAmountChangedListener != null) {
                    onTotalAmountChangedListener.onTotalAmountChanged(totalPrice);
                }
            }
        });
        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = cartItemModel.getQuantity() + 1;
                holder.tvAmount.setText(String.valueOf(amount));
                ApiService apiService = ApiClient.getApiService();
                apiService.updateQuantityItem(cartItemModel.getId(), amount)
                        .enqueue(new Callback<CartItemModel>() {
                            @Override
                            public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
                                CartItemModel cartItemRp = response.body();
                                if(cartItemRp != null) {
                                    cartItemModel.setQuantity(cartItemRp.getQuantity());
                                    cartItemModel.setQuantity(cartItemRp.getQuantity());
                                    if(cartItemSelects.contains(cartItemModel)) {
                                        totalPrice += cartItemRp.getUnitPrice();
                                        if (onTotalAmountChangedListener != null) {
                                            onTotalAmountChangedListener.onTotalAmountChanged(totalPrice);
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<CartItemModel> call, Throwable t) {

                            }
                        });
            }
        });
        holder.tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = cartItemModel.getQuantity();
                if(amount > 1) {
                    holder.tvAmount.setText(String.valueOf(--amount));
                    ApiService apiService = ApiClient.getApiService();
                    apiService.updateQuantityItem(cartItemModel.getId(), amount)
                            .enqueue(new Callback<CartItemModel>() {
                                @Override
                                public void onResponse(Call<CartItemModel> call, Response<CartItemModel> response) {
                                    CartItemModel cartItemRp = response.body();
                                    if(cartItemRp != null) {
                                        cartItemModel.setQuantity(cartItemRp.getQuantity());
                                        if(cartItemSelects.contains(cartItemModel)) {
                                            totalPrice -= cartItemRp.getUnitPrice();
                                            if (onTotalAmountChangedListener != null) {
                                                onTotalAmountChangedListener.onTotalAmountChanged(totalPrice);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<CartItemModel> call, Throwable t) {

                                }
                            });
                }
            }
        });

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
        private TextView tvMinus;
        private TextView tvPlus;
        private CheckBox cbSelect;

        public ViewHolder(final View itemView) {
            super(itemView);
            imvProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvAmount = (TextView) itemView.findViewById(R.id.tvAmount);
            tvPlus = (TextView) itemView.findViewById(R.id.tvPlus);
            tvMinus = (TextView) itemView.findViewById(R.id.tvMinus);
            cbSelect = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
    public interface OnTotalAmountChangedListener {
        void onTotalAmountChanged(double totalPrice);
    }
}
