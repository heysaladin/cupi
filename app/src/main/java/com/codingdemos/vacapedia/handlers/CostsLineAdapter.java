package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.CostsModel;
import com.codingdemos.vacapedia.data.DestinationsModel;

import java.util.List;

public class CostsLineAdapter extends RecyclerView.Adapter < CostsLineViewHolder > {

    private Context mContext;
    private List <CostsModel> mFlowerList;

    public CostsLineAdapter(Context mContext, List <CostsModel> mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public CostsLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.field, parent, false);
        return new CostsLineViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final CostsLineViewHolder holder, int position) {
//        Glide.with(mContext)
//                .load(mFlowerList.get(position).getImage().replace(" ", "%20"))
//                .into(holder.mImage);
        holder.text_edit_text.setText(mFlowerList.get(position).getName());
        holder.number_edit_text.setText(mFlowerList.get(position).getCost());
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent mIntent = new Intent(mContext, EditPlanActivity.class);
//                mIntent.putExtra("_id", mFlowerList.get(holder.getAdapterPosition()).get_id());
//                mIntent.putExtra("body_copy", mFlowerList.get(holder.getAdapterPosition()).getBody_copy());
//                mIntent.putExtra("content", mFlowerList.get(holder.getAdapterPosition()).getContent());
//                mIntent.putExtra("target_date", mFlowerList.get(holder.getAdapterPosition()).getTarget_date());
//                mIntent.putExtra("title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
//                mIntent.putExtra("target_time", mFlowerList.get(holder.getAdapterPosition()).getTarget_time());
//                mIntent.putExtra("destinations", String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()));
//                //mIntent.putExtra("destinations", String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()).substring(1, String.valueOf(mFlowerList.get(holder.getAdapterPosition()).getDestinations()).length()-1));
//                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class CostsLineViewHolder extends RecyclerView.ViewHolder {

//    ImageView mImage;
//    TextView mTitle;
//    CardView mCardView;

    EditText text_edit_text, number_edit_text;
    Button delete_button;

    CostsLineViewHolder(View itemView) {
        super(itemView);

//        mImage = itemView.findViewById(R.id.ivImage);
//        mTitle = itemView.findViewById(R.id.tvTitle);
//        mCardView = itemView.findViewById(R.id.cardview);

        text_edit_text = itemView.findViewById(R.id.text_edit_text);
        number_edit_text = itemView.findViewById(R.id.number_edit_text);
        delete_button = itemView.findViewById(R.id.delete_button);
    }

}

