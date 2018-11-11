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
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.PlanModel;
import com.codingdemos.vacapedia.VacaplanActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter < PlanViewHolder > {

    private Context mContext;
    private List <PlanModel> mFlowerList;
//    private JSONArray jsonArrayUsersFamily;

    public PlanAdapter(Context mContext, List < PlanModel > mFlowerList
//            , JSONArray desPointsParam
    ) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
//        this.jsonArrayUsersFamily = desPointsParam;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType
//            , List<LatLng> desPoints
    ) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new PlanViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final PlanViewHolder holder, final int position) {
        /*
        if (mFlowerList.get(position).getImage() != "null" || mFlowerList.get(position).getImage() != null || mFlowerList.get(position).getImage() != "") {
            RequestOptions options = new RequestOptions();
            options.centerCrop();
            options.placeholder(R.drawable.default_image);
            Glide.with(mContext)
                    .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                    .apply(options)
                    .into(holder.mImage);
        }
        */
        RequestOptions options = new RequestOptions()
                //.signature(mFlowerList.get(position).get_id())
                .format(DecodeFormat.PREFER_RGB_565)
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(mContext)
                .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
                .apply(options)
                .thumbnail(0.5f)
                .into(holder.mImage);

        holder.big_price.setText(mFlowerList.get(position).getCost());
        holder.mTitle.setText(mFlowerList.get(position).getTitle());
        holder.target_date.setText(mFlowerList.get(position).getTarget_date());
        holder.textViewDesc.setText(mFlowerList.get(position).getBody_copy());
        holder.location.setText(mFlowerList.get(position).getLocation());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), VacaplanActivity.class);
                
                intent.putExtra("name", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("image", mFlowerList.get(holder.getAdapterPosition()).getImage());
//                intent.putExtra("body_copy", mFlowerList.get(holder.getAdapterPosition()).getBody_copy());
//                intent.putExtra("target_date", mFlowerList.get(holder.getAdapterPosition()).getTarget_date());
                intent.putExtra("location", "");

                intent.putExtra("_id", mFlowerList.get(holder.getAdapterPosition()).get_id());
                intent.putExtra("body_copy", mFlowerList.get(holder.getAdapterPosition()).getBody_copy());
                intent.putExtra("content", mFlowerList.get(holder.getAdapterPosition()).getContent());
                intent.putExtra("target_date", mFlowerList.get(holder.getAdapterPosition()).getTarget_date());
                intent.putExtra("title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("target_time", mFlowerList.get(holder.getAdapterPosition()).getTarget_time());
                intent.putExtra("destinations", String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()));

//                intent.putExtra("points", String.valueOf(jsonArrayUsersFamily));

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

}

class PlanViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle, big_price, target_date, location, textViewDesc;
    CardView mCardView;

    PlanViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.image);
        mTitle = itemView.findViewById(R.id.title);
        mCardView = itemView.findViewById(R.id.card_plan);
        big_price = itemView.findViewById(R.id.big_price);
        target_date = itemView.findViewById(R.id.target_date);
        location = itemView.findViewById(R.id.location);
        textViewDesc = itemView.findViewById(R.id.textViewDesc);

    }
}
