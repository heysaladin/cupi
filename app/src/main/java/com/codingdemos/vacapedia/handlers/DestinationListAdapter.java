package com.codingdemos.vacapedia.handlers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.DestinationListActivity;
import com.codingdemos.vacapedia.DetailActivity;
import com.codingdemos.vacapedia.data.SubGroupModel;

import java.util.ArrayList;

public class DestinationListAdapter extends RecyclerView.Adapter < DestinationListAdapter.AdapterHolder > {

    String group_karma_beach = "Karma Beach";
    String group_karma_spa = "Karma Spa";
    String group_karma_restaurant = "Karma Restaurants";
    String group_karma_boutique = "Boutique";
    String intent_param_sub_group = "sub_group";
    String intent_param_post_id = "postID";
    String intent_param_menu_id = "menuID";

    private static final String TAG = "DestinationListAdapter";
    ArrayList <SubGroupModel> arrayList;
    Context context;
    String groupName;

    public DestinationListAdapter(Context context, ArrayList < SubGroupModel > arrayList, String groupName) {
        this.context = context;
        this.arrayList = arrayList;
        this.groupName = groupName;
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

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

                    context.startActivity(intent);
                } else if (groupName.equals(group_karma_spa)) {
                    Intent intent = new Intent(context, DetailActivity.class);

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

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

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                } else if (groupName.equals(group_karma_boutique)) {
                    Intent intent = new Intent(context, DetailActivity.class);

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

                    intent.putExtra(intent_param_post_id, model.getPostID());
                    intent.putExtra(intent_param_menu_id, model.getMenuID());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DetailActivity.class);

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

                    intent.putExtra("Title", model.getName());
                    intent.putExtra("Description", model.getName());
                    intent.putExtra("Image", model.getName());

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