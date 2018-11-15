package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.FamilyModel;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter < FamilyViewHolder > {

    private Context mContext;
    private List < FamilyModel > mFlowerList;

    public FamilyAdapter(Context mContext, List < FamilyModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FamilyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new FamilyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FamilyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mFlowerList.get(position).getFamily_photo().replace(" ", "%20"))
                .into(holder.mImage);
        holder.mTitle.setText(mFlowerList.get(position).getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class FamilyViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    FamilyViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}
