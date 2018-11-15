package com.codingdemos.vacapedia.handlers;

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
import com.bumptech.glide.request.RequestOptions;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.DestinationListActivity;
import com.codingdemos.vacapedia.data.DestinationsModel;
import com.codingdemos.vacapedia.DetailActivity;

import java.util.ArrayList;

public class GuestDestinationsAdapter extends RecyclerView.Adapter < GuestDestinationsAdapter.AdapterHolder > {

    private String group_karma_beach = "Karma Beach";
    private String group_karma_spa = "Karma Spa";
    private String group_karma_restaurant = "Karma Restaurants";
    private String group_karma_boutique = "Boutique";
    private String intent_param_sub_group = "sub_group";
    private String intent_param_post_id = "postID";
    private String intent_param_post_title = "postTitle";
    private String intent_param_menu_id = "menuID";

    private static final String TAG = "GuestDestinationsAdapter";
    ArrayList < DestinationsModel > arrayList;
    Context context;

    public GuestDestinationsAdapter(Context context, ArrayList < DestinationsModel > arrayList) {
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
        // Log.d("LOG", "model.getImage() +++++++++++++++ " + model.getImage());
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.default_image);
        Glide.with(context)
                .load(model.getImage())
                .apply(options)
                .into(holder.item_home_destination_image_iv);
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

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_post_title, model.getName());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());

                    intent.putExtra("_id", model.get_id());
                    intent.putExtra("name", model.getName());
                    intent.putExtra("location", model.getLocation());
                    intent.putExtra("image", model.getImage());
                    intent.putExtra("description", model.getDescription());

                    intent.putExtra("category", model.getCategory());
                    intent.putExtra("latitude", model.getLatitude());
                    intent.putExtra("longitude", model.getLongitude());
                    intent.putExtra("address", model.getAddress());
                    intent.putExtra("distance", model.getDistance());
                    intent.putExtra("note", model.getNote());
                    intent.putExtra("costs", model.getCosts());
                    intent.putExtra("total_cost", model.getTotal_cost());

                    // Log.d("LOG", "model.getLatitude() >>>>>>>>> " + model.getLatitude());

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