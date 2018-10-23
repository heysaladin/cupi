package com.codingdemos.vacapedia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingdemos.flowers.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter < NotificationViewHolder > {

    private Context mContext;
    private List < NotificationsModel > mFlowerList;

    public NotificationAdapter(Context mContext, List < NotificationsModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new NotificationViewHolder(mView);
    }

//    @Override
//    public void onBindViewHolder(final FlowerViewHolder holder, int position) {
//        holder.mImage.setImageResource(mFlowerList.get(position).getDestinationImage());
//        holder.mTitle.setText(mFlowerList.get(position).getDestinationName());
//        holder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(mContext, DetailActivity.class);
//                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getDestinationName());
//                mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getDestinationDescription());
//                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getDestinationImage());
//                mContext.startActivity(mIntent);
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(final NotificationViewHolder holder, int position) {
//        holder.mImage.setImageResource(mFlowerList.get(position).getDestinationImage());
//        holder.mTitle.setText(mFlowerList.get(position).getDestinationName());
//        holder.mImage.setImageResource(Integer.parseInt(mFlowerList.get(position).getImage()));
        Glide.with(mContext)
                .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                .into(holder.mImage);
        holder.mTitle.setText(mFlowerList.get(position).getTitle());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
//                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getDestinationName());
//                mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getDestinationDescription());
//                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getDestinationImage());
                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                mIntent.putExtra("Description", "desc");
                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getImage());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class NotificationViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    NotificationViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}