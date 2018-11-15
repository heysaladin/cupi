package com.codingdemos.vacapedia.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codingdemos.vacapedia.data.FamilyModel;
import com.codingdemos.vacapedia.MainActivity;
import com.codingdemos.flowers.R;
import com.codingdemos.vacapedia.handlers.NotificationAdapter;
import com.codingdemos.vacapedia.handlers.UserAdapter;
import com.codingdemos.vacapedia.data.UserModel;
import com.codingdemos.vacapedia.rest.AsyncHttpResponse;
import com.codingdemos.vacapedia.rest.RestApis;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountFragment extends Fragment
        implements
        AsyncHttpResponse.AsyncHttpResponseListener {

    private static final String ID_FAMILY = "5bd0218fe298350013536024";
    private View view;
    private JSONObject dataUser = null;
    private JSONArray dataUserFamily = null;
    private JSONArray dataDestinations = null;
    private ArrayList < UserModel > destinationsArrayListBuffer;
    private ArrayList < UserModel > destinationsArrayList;
    private NotificationAdapter guestDestinationsAdapter;
    private String imageUrl = null;
    private RecyclerView mRecyclerView;

    private JSONArray jsonArrayUsersFamily;

    private FamilyModel currentFamily;
    private UserModel currentUser;

    private ArrayList < UserModel > userInFamily;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_two, container, false);

        getKarmaGroupsApiRequest();
        dataUserFamily = new JSONArray();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle clicks
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_two, menu);
    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setActionBarTitle("Account");
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((MainActivity) getActivity()).setActionBarTitle("Account");
            Log.d("LOG", "one");
        }
    }

    private void processData() {
        try {
            JSONArray jarry = dataDestinations;
   /*
   String user1 = "5bd01fa524031400132ea890";
   String user2 = "5bd01fe324031400132ea891";
   JSONArray jsonArrayUsers = new JSONArray("{" +
           "\"user_id\":\""+ user1 +"\"" +
           "\"user_id\":\""+ user2 +"\"" +
           "}");
   */
            for (int j = 0; j < jarry.length(); j++) {
                JSONObject job = jarry.getJSONObject(j);
                if (job.optString("_id").equals(ID_FAMILY)) {
                    currentFamily = new FamilyModel();
                    currentFamily.setName(job.optString("name"));
                    currentFamily.setDescription(job.optString("description"));
                    currentFamily.setLocation(job.optString("location"));
                    currentFamily.setFamily_photo(job.optString("family_photo"));
                    currentFamily.setCover_photo(job.optString("cover_photo"));
                    jsonArrayUsersFamily = new JSONArray(job.optString("members"));
                    currentFamily.setMembers(jsonArrayUsersFamily);
                    destinationsArrayList = new ArrayList < > ();
                    mRecyclerView = view.findViewById(R.id.recyclerview);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    destinationsArrayList.clear();
                    for (int k = 0; k < jsonArrayUsersFamily.length(); k++) {
                        JSONObject jobk = jsonArrayUsersFamily.getJSONObject(k);
                        loadUsers(jobk);
                    }
                    UserAdapter myAdapter = new UserAdapter(this.getActivity(), destinationsArrayList);
                    mRecyclerView.setAdapter(myAdapter);
                }
            }
            ImageView header_cover_image = view.findViewById(R.id.header_cover_image);
            ImageButton user_profile_photo = view.findViewById(R.id.user_profile_photo);
            TextView user_profile_name = view.findViewById(R.id.user_profile_name);
            TextView user_profile_short_bio = view.findViewById(R.id.user_profile_short_bio);
            Glide.with(this.getActivity())
                    .load(currentFamily.getCover_photo().replace(" ", "%20"))
                    .into(header_cover_image);
            Glide.with(this.getActivity())
                    .load(currentFamily.getFamily_photo().replace(" ", "%20"))
                    .into(user_profile_photo);
            user_profile_name.setText(currentFamily.getName());
            user_profile_short_bio.setText(currentFamily.getDescription());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers(JSONObject job) {
        // Log.d("TAG", "dataUserFamily >>>>>>>>> " + dataUserFamily);
        currentUser = new UserModel();
        currentUser.set_id(job.optString("_id"));
        currentUser.setName(job.optString("name"));
        currentUser.setFull_name(job.optString("full_name"));
        currentUser.setPhoto_profile(job.optString("photo_profile"));
        currentUser.setSex_type(job.optString("sex_type"));
        currentUser.setBirth_date(job.optString("birth_date"));
        currentUser.setEmail_address(job.optString("email_address"));
        currentUser.setPhone_number(job.optString("phone_number"));
        currentUser.setCountry(job.optString("country"));
        currentUser.setSayings(job.optString("sayings"));
        destinationsArrayList.add(currentUser);
    }

    private ArrayList < JSONObject > getArrayListFromJSONArray(JSONArray jsonArray) {
        ArrayList < JSONObject > aList = new ArrayList < JSONObject > ();
        try {
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
        return aList;
    }

    private void getKarmaGroupsApiRequest() {
        AsyncHttpResponse response = new AsyncHttpResponse(this, false);
        RequestParams params = new RequestParams();
        response.getAsyncHttp(RestApis.KarmaGroups.vacapediaFamilies + "/" + ID_FAMILY /* */ , params);
    }

    @Override
    public void onAsyncHttpResponseGet(String response, String url) throws JSONException {
        Log.d("TAG", "onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
        if (url.contains(RestApis.KarmaGroups.vacapediaFamilies /* + "/" + ID_FAMILY*/ )) {
            // Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataDestinations = new JSONArray();
            JSONObject dataResponse = new JSONObject(response);
            dataDestinations.put(dataResponse);
            processData();
        }
        if (url.contains(RestApis.KarmaGroups.vacapediaUsers + "/" /* + ID_FAMILY*/ )) {
            // Log.d("TAG", "x onAsyncHttpResponseGet() called with: response = [" + response + "], url = [" + url + "]");
            dataUser = new JSONObject(response);
            // Log.d("TAG", "dataUser >>>>>>>>> " + dataUser);
            dataUserFamily.put(dataUser);
            // processData();
        }
    }

}
