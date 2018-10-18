package com.codingdemos.flowers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private Context context;
//    private List< NoteData > mFlowerList;
    private ArrayList< NoteData > mFlowerList;

    int[] imgList = {R.drawable.hagia_sophia, R.drawable.colosseum, R.drawable.ic_launcher_background, R.drawable.masjidil_haram,
            R.drawable.ic_launcher, R.drawable.hagia_sophia, R.drawable.masjid_nabawi, R.drawable.masjidil_haram,
            R.drawable.hagia_sophia, R.drawable.colosseum};

    String[] nameList = {"Lorem ipsum dlor sit amet.", "Two Lorem ipsum dlor sit amet.", "Three", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Five", "Six",
            "Seven", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Nine", "Ten"};

    String[] noteList = {"Lorem ipsum dlor sit amet.", "Two", "Three", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Five Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet. Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Six",
            "Seven", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Nine Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Ten"};



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
//        final NoteData model = mFlowerList.get(position);
        // holder.imageView.setImageResource(imgList[position]);
        holder.textView.setText(nameList[position]);
        holder.textViewNote.setText(noteList[position]);
//        holder.textView.setText(model.getTitle());
//        holder.textViewNote.setText(model.getContent());
//        holder.textView.setText(mFlowerList.get(holder.getAdapterPosition()-1).getTitle());
//        holder.textViewNote.setText(mFlowerList.get(holder.getAdapterPosition()-1).getContent());
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, NoteActivity.class);
//                mIntent.putExtra("Title", nameList[holder.getAdapterPosition()]);
//                mIntent.putExtra("Note", noteList[holder.getAdapterPosition()]);
                mIntent.putExtra("id", mFlowerList.get(holder.getAdapterPosition()).get_id());
                mIntent.putExtra("title", mFlowerList.get(holder.getAdapterPosition()).getTitle());
                mIntent.putExtra("content", mFlowerList.get(holder.getAdapterPosition()).getContent());
                //mIntent.putExtra("Image", imgList[holder.getAdapterPosition()]);
                context.startActivity(mIntent);
                Activity activity = (Activity)context;
                activity.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }
        });
//        holder.edit_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent = new Intent(context, NoteActivity.class);
//                mIntent.putExtra("Title", nameList[holder.getAdapterPosition()]);
//                mIntent.putExtra("Note", noteList[holder.getAdapterPosition()]);
//                //mIntent.putExtra("Image", imgList[holder.getAdapterPosition()]);
//                context.startActivity(mIntent);
//            }
//        });
//        holder.delete_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /*
//                mFlowerList.remove(position);
//                mAdapter.notifyItemRemoved(position);
//                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
//                */
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }

    class MasonryView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textViewNote;
        LinearLayout wrapper;
//        ImageView edit_iv, delete_iv;

        public MasonryView(View itemView) {
            super(itemView);

            // imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
            textViewNote = (TextView) itemView.findViewById(R.id.img_note);
//            edit_iv = (ImageView) itemView.findViewById(R.id.edit_iv);
//            delete_iv = (ImageView) itemView.findViewById(R.id.delete_iv);
            wrapper = (LinearLayout) itemView.findViewById(R.id.wrapper);

        }
    }
}