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
import com.codingdemos.vacapedia.data.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter < UserViewHolder > {

    private Context mContext;
    private List < UserModel > mFlowerList;

    public UserAdapter(Context mContext, List < UserModel > mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new UserViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        // Log.d("TAG", "mFlowerList.get(position) >>>>>>>>> " + mFlowerList.get(position));
        Glide.with(mContext)
                .load(mFlowerList.get(position).getPhoto_profile().replace(" ", "%20"))
                .into(holder.mImage);
        holder.mTitle.setText(mFlowerList.get(position).getName());
        holder.tvDesc.setText(mFlowerList.get(position).getSayings());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    /*
    Intent mIntent = new Intent(mContext, DetailActivity.class);
    mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
    mIntent.putExtra("Description", "desc");
    mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getImage());
    mContext.startActivity(mIntent);
    */
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle, tvDesc;
    CardView mCardView;

    UserViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mCardView = itemView.findViewById(R.id.cardview);
        tvDesc = itemView.findViewById(R.id.tvDesc);
    }

}
