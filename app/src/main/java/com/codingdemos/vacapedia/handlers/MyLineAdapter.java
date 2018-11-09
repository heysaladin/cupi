package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.DetailActivity;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.handlers.FlowerViewHolder;

import java.util.List;

public class MyLineAdapter extends RecyclerView.Adapter <FlowerViewHolder> {

    private Context mContext;
    private List <DestinationsModel> mFlowerList;

    public MyLineAdapter(Context mContext, List < DestinationsModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_line_row_item, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, int position) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.default_image);
        Glide.with(mContext)
                .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                .apply(options)
                .into(holder.mImage);
        holder.mTitle.setText(mFlowerList.get(position).getName());
        holder.tvDesc.setText(mFlowerList.get(position).getLocation());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("name", mFlowerList.get(holder.getAdapterPosition()).getName());
                mIntent.putExtra("Description", "desc");
                mIntent.putExtra("image", mFlowerList.get(holder.getAdapterPosition()).getImage());
                mIntent.putExtra("description", mFlowerList.get(holder.getAdapterPosition()).getDescription());
//                intent.putExtra("name", mFlowerList.get(holder.getAdapterPosition()).getTitle());
////                intent.putExtra("location", mFlowerList.get(holder.getAdapterPosition()).getLocation());
//                intent.putExtra("image", mFlowerList.get(holder.getAdapterPosition()).getImage());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

}
