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
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.EditPromoActivity;
import com.codingdemos.vacapedia.data.PromosModel;

import java.util.List;

public class PromosAdapter extends RecyclerView.Adapter < PromoViewHolder > {

    private Context mContext;
    private List <PromosModel> mFlowerList;

    public PromosAdapter(Context mContext, List < PromosModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new PromoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final PromoViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                .into(holder.mImage);
        holder.mTitle.setText(mFlowerList.get(position).getTitle());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, EditPromoActivity.class);
                mIntent.putExtra("_id", mFlowerList.get(holder.getAdapterPosition()).get_id());
                mIntent.putExtra("body_copy", mFlowerList.get(holder.getAdapterPosition()).getBody_copy());
                mIntent.putExtra("category", mFlowerList.get(holder.getAdapterPosition()).getCategory());
                mIntent.putExtra("title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                mIntent.putExtra("description", mFlowerList.get(holder.getAdapterPosition()).getDescription());
                mIntent.putExtra("image", mFlowerList.get(holder.getAdapterPosition()).getImage());
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class PromoViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    PromoViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }

}
