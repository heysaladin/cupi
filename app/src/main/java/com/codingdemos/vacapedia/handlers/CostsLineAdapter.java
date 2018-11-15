package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.data.CostsModel;

import java.util.List;

public class CostsLineAdapter extends RecyclerView.Adapter < CostsLineViewHolder > {

    private Context mContext;
    private List < CostsModel > mFlowerList;

    public CostsLineAdapter(Context mContext, List < CostsModel > mFlowerList) {
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
        holder.text_edit_text.setText(mFlowerList.get(position).getName());
        holder.number_edit_text.setText(mFlowerList.get(position).getCost());
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }
}

class CostsLineViewHolder extends RecyclerView.ViewHolder {

    EditText text_edit_text, number_edit_text;
    Button delete_button;

    CostsLineViewHolder(View itemView) {
        super(itemView);
        text_edit_text = itemView.findViewById(R.id.text_edit_text);
        number_edit_text = itemView.findViewById(R.id.number_edit_text);
        delete_button = itemView.findViewById(R.id.delete_button);
    }

}
