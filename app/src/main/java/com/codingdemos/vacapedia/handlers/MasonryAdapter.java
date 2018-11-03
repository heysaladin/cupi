package com.codingdemos.vacapedia.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.NoteActivity;
import com.codingdemos.vacapedia.data.NoteData;

import java.util.ArrayList;

public class MasonryAdapter extends RecyclerView.Adapter < MasonryAdapter.MasonryView > {

    private Context context;
    private ArrayList <NoteData> mFlowerList;

    int[] imgList = {
            R.drawable.hagia_sophia,
            R.drawable.colosseum,
            R.drawable.ic_launcher_background,
            R.drawable.masjidil_haram,
            R.drawable.ic_launcher,
            R.drawable.hagia_sophia,
            R.drawable.masjid_nabawi,
            R.drawable.masjidil_haram,
            R.drawable.hagia_sophia,
            R.drawable.colosseum
    };

    String[] nameList = {
            "Lorem ipsum dlor sit amet.",
            "Two Lorem ipsum dlor sit amet.",
            "Three",
            "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Five",
            "Six",
            "Seven",
            "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Nine",
            "Ten"
    };

    String[] noteList = {
            "Lorem ipsum dlor sit amet.",
            "Two",
            "Three",
            "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Five Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet. Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Six",
            "Seven",
            "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Nine Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.",
            "Ten"
    };

    public MasonryAdapter(Context context, ArrayList < NoteData > mFlowerList) {
        this.context = context;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(final MasonryView holder, int position) {
        holder.textView.setText(mFlowerList.get(holder.getAdapterPosition()).getTitle());
        holder.textViewNote.setText(mFlowerList.get(holder.getAdapterPosition()).getContent());
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, NoteActivity.class);
                mIntent.putExtra("_id", mFlowerList.get(holder.getAdapterPosition()).get_id());
                mIntent.putExtra("title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                mIntent.putExtra("content", mFlowerList.get(holder.getAdapterPosition()).getContent());
                context.startActivity(mIntent);
                Activity activity = (Activity) context;
                activity.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewNote;
        LinearLayout wrapper;
        public MasonryView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.img_name);
            textViewNote = (TextView) itemView.findViewById(R.id.img_note);
            wrapper = (LinearLayout) itemView.findViewById(R.id.wrapper);
        }
    }

}
