package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.DestinationsModel;

import java.util.List;

public class DestinationsLineAdapter extends RecyclerView.Adapter < DestinationsLineViewHolder > {

    private Context mContext;
    private List < DestinationsModel > mFlowerList;

    public DestinationsLineAdapter(Context mContext, List < DestinationsModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public DestinationsLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new DestinationsLineViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final DestinationsLineViewHolder holder, int position) {
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

class DestinationsLineViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    DestinationsLineViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
    }

}
