package com.codingdemos.flowers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private Context context;

    int[] imgList = {R.drawable.hagia_sophia, R.drawable.colosseum, R.drawable.ic_launcher_background, R.drawable.masjidil_haram,
            R.drawable.ic_launcher, R.drawable.hagia_sophia, R.drawable.masjid_nabawi, R.drawable.masjidil_haram,
            R.drawable.hagia_sophia, R.drawable.colosseum};

    String[] nameList = {"Lorem ipsum dlor sit amet.", "Two Lorem ipsum dlor sit amet.", "Three", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Five", "Six",
            "Seven", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Nine", "Ten"};

    String[] noteList = {"Lorem ipsum dlor sit amet.", "Two", "Three", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Five Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet. Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Six",
            "Seven", "Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Nine Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.Lorem ipsum dlor sit amet.", "Ten"};

    public MasonryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(final MasonryView holder, int position) {
        // holder.imageView.setImageResource(imgList[position]);
        holder.textView.setText(nameList[position]);
        holder.textViewNote.setText(noteList[position]);
        holder.wrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(context, NoteActivity.class);
                mIntent.putExtra("Title", nameList[holder.getAdapterPosition()]);
                mIntent.putExtra("Note", noteList[holder.getAdapterPosition()]);
                //mIntent.putExtra("Image", imgList[holder.getAdapterPosition()]);
                context.startActivity(mIntent);
            }
        });
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

        public MasonryView(View itemView) {
            super(itemView);

            // imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.img_name);
            textViewNote = (TextView) itemView.findViewById(R.id.img_note);
            wrapper = (LinearLayout) itemView.findViewById(R.id.wrapper);

        }
    }
}