package com.codingdemos.vacapedia.handlers;

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
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.DetailActivity;
import com.codingdemos.vacapedia.data.DestinationsModel;

import java.util.List;

public class DestinationsTimeLineAdapter extends RecyclerView.Adapter < DestinationsTimeLineViewHolder > {

    private Context mContext;
    private List < DestinationsModel > mFlowerList;
    private List < DestinationsModel > destinationsArrayListBuffer;

    public DestinationsTimeLineAdapter(Context mContext, List < DestinationsModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public DestinationsTimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_destination_time_line, parent, false);
        return new DestinationsTimeLineViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final DestinationsTimeLineViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.default_image);
        Glide.with(mContext)
                .load(mFlowerList.get(position).getImage())
                .apply(options)
                .into(holder.mImage);
        holder.tvTime.setText("8.00 WITA");
        holder.tvCosts.setText(mFlowerList.get(position).getTotal_cost());
        holder.mTitle.setText(mFlowerList.get(position).getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", mFlowerList.get(position).getPostID());
                intent.putExtra("title", mFlowerList.get(position).getName());
                intent.putExtra("menu_id", mFlowerList.get(position).getMenuID());
                intent.putExtra("name", mFlowerList.get(position).getName());
                intent.putExtra("location", mFlowerList.get(position).getLocation());
                intent.putExtra("image", mFlowerList.get(position).getImage());
                Intent mIntent = intent;
                destinationsArrayListBuffer = mFlowerList;
                mIntent.putExtra("_id", destinationsArrayListBuffer.get(position).get_id());
                mIntent.putExtra("name", destinationsArrayListBuffer.get(position).getName());
                mIntent.putExtra("image", destinationsArrayListBuffer.get(position).getImage());
                mIntent.putExtra("category", destinationsArrayListBuffer.get(position).getCategory());
                mIntent.putExtra("location", destinationsArrayListBuffer.get(position).getLocation());
                mIntent.putExtra("description", destinationsArrayListBuffer.get(position).getDescription());
                mIntent.putExtra("latitude", destinationsArrayListBuffer.get(position).getLatitude());
                mIntent.putExtra("longitude", destinationsArrayListBuffer.get(position).getLongitude());
                mIntent.putExtra("address", destinationsArrayListBuffer.get(position).getAddress());
                mIntent.putExtra("distance", destinationsArrayListBuffer.get(position).getDistance());
                mIntent.putExtra("note", destinationsArrayListBuffer.get(position).getNote());
                mIntent.putExtra("costs", destinationsArrayListBuffer.get(position).getCosts());
                mIntent.putExtra("total_cost", destinationsArrayListBuffer.get(position).getTotal_cost());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class DestinationsTimeLineViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle, tvCosts, tvTime;
    CardView mCardView;

    DestinationsTimeLineViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.card_image);
        tvCosts = itemView.findViewById(R.id.tvCosts);
        tvTime = itemView.findViewById(R.id.tvTime);
    }

}
