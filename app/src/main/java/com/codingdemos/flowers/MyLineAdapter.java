package com.codingdemos.flowers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MyLineAdapter extends RecyclerView.Adapter < FlowerViewHolder > {

    private Context mContext;
    private List < DestinationData > mFlowerList;

    public MyLineAdapter(Context mContext, List < DestinationData > mFlowerList) {
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
        holder.mImage.setImageResource(mFlowerList.get(position).getDestinationImage());
        holder.mTitle.setText(mFlowerList.get(position).getDestinationName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getDestinationName());
                mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getDestinationDescription());
                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getDestinationImage());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

}