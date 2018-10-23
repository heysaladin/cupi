package com.codingdemos.vacapedia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingdemos.flowers.R;

import java.util.ArrayList;

public class SlidesAdapter extends RecyclerView.Adapter < SlidesAdapter.AdapterHolder > {

    String group_karma_beach = "Karma Beach";
    String group_karma_spa = "Karma Spa";
    String group_karma_restaurant = "Karma Restaurants";
    String group_karma_boutique = "Boutique";
    String intent_param_sub_group = "sub_group";
    String intent_param_post_id = "postID";
    String intent_param_post_title = "postTitle";
    String intent_param_menu_id = "menuID";

    private static final String TAG = "GuestDestinationsAdapter";
    ArrayList < NotificationsModel > arrayList;
    Context context;

    public SlidesAdapter(Context context, ArrayList < NotificationsModel > arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public SlidesAdapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_destination, parent, false);
        AdapterHolder viewHolder = new AdapterHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SlidesAdapter.AdapterHolder holder, int position) {
        final NotificationsModel model = arrayList.get(position);
        String img = "https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg";
        Log.d("LOG", "model.getImage() +++++++++++++++ " + model.getImage());
        Glide.with(context)
                .load(model.getImage())
                .into(holder.item_home_destination_image_iv);
        holder.item_home_destination_name_tv.setText(model.getTitle());
        holder.item_home_destination_parent_rl.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                if (model.getMenuName().equals(group_karma_beach)) {
                    Intent intent = new Intent(context, DestinationListActivity.class);
                    intent.putExtra(intent_param_sub_group, model.getMenuName());
                    context.startActivity(intent);
                } else if (model.getMenuName().equals(group_karma_spa)) {
                    Log.d(TAG, "clicked : " + group_karma_spa);
                    Intent intent = new Intent(context, DestinationListActivity.class);
                    intent.putExtra(intent_param_sub_group, model.getMenuName());
                    context.startActivity(intent);
                } else if (model.getMenuName().equals(group_karma_restaurant)) {
                    Log.d(TAG, "clicked : " + group_karma_restaurant);
                    Intent intent = new Intent(context, DestinationListActivity.class);
                    intent.putExtra(intent_param_sub_group, model.getMenuName());
                    context.startActivity(intent);
                } else if (model.getMenuName().equals(group_karma_boutique)) {
                    Intent intent = new Intent(context, DetailActivity.class);

                    intent.putExtra("Title", model.getTitle());
                    intent.putExtra("Description", model.getTitle());
                    intent.putExtra("Image", model.getTitle());

                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_post_title, model.getTitle());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());

                    intent.putExtra("Title", model.getTitle());
                    intent.putExtra("Description", model.getTitle());
                    intent.putExtra("Image", model.getImage());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static class AdapterHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_home_destination_parent_rl;
        private ImageView item_home_destination_image_iv;
        private TextView item_home_destination_name_tv;
        public AdapterHolder(View itemView) {
            super(itemView);
            item_home_destination_parent_rl = (RelativeLayout) itemView.findViewById(R.id.item_home_destination_parent_rl);
            item_home_destination_image_iv = (ImageView) itemView.findViewById(R.id.item_home_destination_image_iv);
            item_home_destination_name_tv = (TextView) itemView.findViewById(R.id.item_home_destination_name_tv);
        }
    }

}