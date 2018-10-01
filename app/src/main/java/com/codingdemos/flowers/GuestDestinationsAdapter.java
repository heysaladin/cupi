package com.codingdemos.flowers;

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

import java.util.ArrayList;

public class GuestDestinationsAdapter extends RecyclerView.Adapter <GuestDestinationsAdapter.AdapterHolder> {

    String activity_terms = "activity_terms";
    String group_karma_beach = "Karma Beach";
    String group_karma_spa = "Karma Spa";
    String group_karma_restaurant = "Karma Restaurants";
    String group_karma_boutique = "Boutique";


    String intent_param_activity = "activity";
    String intent_param_sub_group = "sub_group";
    String intent_param_post_id = "postID";
    String intent_param_post_title = "postTitle";
    String intent_param_menu_id = "menuID";
    String intent_param_resort_name = "resort_name";
    String intent_param_resort_email = "resort_email";
    String intent_param_restaurant_name = "restaurant_name";
    String intent_param_restaurant_email = "restaurant_email";
    String intent_param_spa_name = "spa_name";
    String intent_param_spa_email = "spa_email";


    private static final String TAG = "GuestDestinationsAdapter";
    ArrayList<DestinationsModel> arrayList;
    Context context;

    public GuestDestinationsAdapter(Context context, ArrayList <DestinationsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GuestDestinationsAdapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_destination, parent, false);
        AdapterHolder viewHolder = new AdapterHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GuestDestinationsAdapter.AdapterHolder holder, int position) {
        final DestinationsModel model = arrayList.get(position);
        String img = "https://www.dakwatuna.com/wp-content/uploads/2015/07/masjidil-haram.jpg";
        Log.d("LOG", "model.getImage() +++++++++++++++ " + model.getImage());
        Glide.with(context)
                .load(model.getImage().replace(" ", "%20"))
                .placeholder(R.drawable.hagia_sophia)
                .error(R.drawable.hagia_sophia)
                .into(holder.item_home_destination_image_iv);
//        Glide.with(context)
//                .load(img.replace(" ", "%20"))
//                .into(holder.item_home_destination_image_iv);
        // holder.item_home_destination_image_iv.setImageDrawable(R.drawable.hagia_sophia);
        holder.item_home_destination_name_tv.setText(model.getName());
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
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_post_title, model.getName());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
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
