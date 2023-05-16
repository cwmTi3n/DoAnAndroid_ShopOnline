package com.app.shopdodientu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.shopdodientu.R;
import com.app.shopdodientu.activity.SearchActivity;
import com.app.shopdodientu.model.FeedbackModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder>{
    private Context context;
    private List<FeedbackModel> feedbackModels;

    public FeedbackAdapter(Context context, List<FeedbackModel> feedbackModels) {
        this.context = context;
        this.feedbackModels = feedbackModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View itemView = li.inflate(R.layout.feedback, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedbackModel feedbackModel = feedbackModels.get(position);
        Glide.with(context)
                .load(feedbackModel.getAvatar())
                .into(holder.imgAvatarUser);
        Glide.with(context)
                .load(feedbackModel.getImage())
                .into(holder.imgFeedback1);
        holder.tvUsername.setText(feedbackModel.getUsername());
        holder.tvFeedback.setText(feedbackModel.getContent());
        holder.tvDate.setText(feedbackModel.getTime());
        holder.ratingBar.setRating(feedbackModel.getStar());
    }

    @Override
    public int getItemCount() {
        return feedbackModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatarUser, imgFeedback1;
        private TextView tvUsername, tvDate, tvFeedback;
        private RatingBar ratingBar;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgAvatarUser = itemView.findViewById(R.id.imgAvatarUser);
            imgFeedback1 = itemView.findViewById(R.id.imgFeedback1);
            tvUsername = itemView.findViewById(R.id.tvUserName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
