package com.codingdemos.flowers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DestinationListAdapter extends RecyclerView.Adapter < DestinationListAdapter.AdapterHolder > {


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

    private static final String TAG = "DestinationListAdapter";
    ArrayList< SubGroupModel > arrayList;
    Context context;
    String groupName;
//    SessionManager sm;

    public DestinationListAdapter(Context context, ArrayList < SubGroupModel > arrayList, String groupName) {
        this.context = context;
        this.arrayList = arrayList;
        this.groupName = groupName;
//        sm = new SessionManager(context);
    }

    @Override
    public DestinationListAdapter.AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination, parent, false);
        AdapterHolder viewHolder = new AdapterHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DestinationListAdapter.AdapterHolder holder, final int position) {
        final SubGroupModel model = arrayList.get(position);
        Log.d(TAG, "model : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + model);
        Glide.with(context)
                .load(model.getBgimage().replace(" ", "%20"))
                .into(holder.item_destination_image_iv);
        Glide.with(context)
                .load(model.getImage().replace(" ", "%20"))
                .into(holder.item_destination_logo_iv);
        holder.item_destination_parent_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "groupName >>>>>>>>>>>>>>>>>>>>>> " + groupName);
                if (groupName.equals(group_karma_beach)) {
                    Log.d(TAG, "AppStrings.Unique.group_karma_beach >>>>>>>>>>>>>>>>>>>>>> " + group_karma_beach);
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                } else if (groupName.equals(group_karma_spa)) {
                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(AppStrings.Unique.intent_param_post_id, model.getPostID());
//                    intent.putExtra(AppStrings.Unique.intent_param_menu_id, model.getMenuID());
//                    intent.putExtra(AppStrings.Unique.intent_param_spa_name, model.getName());
                    context.startActivity(intent);
                } else if (groupName.equals(group_karma_restaurant)) {
                    Log.d(TAG, "clicked : " + group_karma_restaurant);
                    Intent intent = new Intent(context, DestinationListActivity.class);
                    intent.putExtra(intent_param_sub_group, "SubRestaurant");
                    intent.putExtra("id", model.getId());
                    intent.putExtra("name", model.getName());
                    context.startActivity(intent);
                } else if (groupName.equals("SubRestaurant")) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                } else if (groupName.equals(group_karma_boutique)) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra(AppStrings.Unique.intent_param_post_id, model.getPostID());
//                    intent.putExtra(AppStrings.Unique.intent_param_post_title, model.getName());
//                    intent.putExtra(AppStrings.Unique.intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                }
            }
        });
//        if (AppUtils.isKarmaGroupMember(context)) {
//            holder.item_destination_favourite_iv.setVisibility(View.VISIBLE);
//            if (model.getFavouriteStatus() == 0) {
//                holder.item_destination_favourite_iv.setImageResource(R.drawable.icn_star);
//                holder.item_destination_favourite_iv.setColorFilter(Color.parseColor(context.getResources().getString(R.string.favourite_icon_white_colour)));
//            } else {
//                holder.item_destination_favourite_iv.setColorFilter(Color.parseColor(context.getResources().getString(R.string.favourite_icon_colour)));
//            }
//        } else {
//            holder.item_destination_favourite_iv.setVisibility(View.GONE);
//        }
//        holder.item_destination_favourite_iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (AppUtils.isKarmaGroupMember(context)) {
//                    if (model.getFavouriteStatus() == 0)
//                        ((DestinationListActivity) context).updateFavourite(position, model.getPostID(), model.getMenuID(), 1);
//                    else
//                        ((DestinationListActivity) context).updateFavourite(position, model.getPostID(), model.getMenuID(), 0);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static class AdapterHolder extends RecyclerView.ViewHolder {
        private RelativeLayout item_destination_parent_rl;
        private ImageView item_destination_image_iv;
        private ImageView item_destination_logo_iv;
        private ImageView item_destination_favourite_iv;
        public AdapterHolder(View itemView) {
            super(itemView);
            item_destination_parent_rl = (RelativeLayout) itemView.findViewById(R.id.item_destination_parent_rl);
            item_destination_image_iv = (ImageView) itemView.findViewById(R.id.item_destination_image_iv);
            item_destination_logo_iv = (ImageView) itemView.findViewById(R.id.item_destination_logo_iv);
            item_destination_favourite_iv = (ImageView) itemView.findViewById(R.id.item_destination_favourite_iv);
        }
    }

}
