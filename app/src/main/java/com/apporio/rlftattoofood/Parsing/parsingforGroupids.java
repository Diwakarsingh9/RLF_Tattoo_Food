package com.apporio.rlftattoofood.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.rlftattoofood.Adapter.FaqAdapter;
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.LoginActivity;
import com.apporio.rlftattoofood.R;
import com.apporio.rlftattoofood.Setter_getter.Faq_Outter;
import com.apporio.rlftattoofood.Setter_getter.Group_Outter;
import com.apporio.rlftattoofood.Setter_getter.Inner_Faq;
import com.apporio.rlftattoofood.Setter_getter.Inner_group;
import com.apporio.rlftattoofood.fragment.FaqFragment;
import com.apporio.rlftattoofood.singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class parsingforGroupids {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_group> groupnames = new ArrayList<>();
    public static ArrayList<String> groupid = new ArrayList<String>();
    public static ArrayList<String> groupname = new ArrayList<String>();
    public static ArrayList<String> login_id = new ArrayList<String>();
    public static ArrayList<String> usernames = new ArrayList<String>();



    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.View_groups;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.GET, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                LoginActivity.pb.setVisibility(View.GONE);

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    groupid.clear();
                    groupname.clear();
                    login_id.clear();
                    usernames.clear();


                    Group_Outter received2 = new Group_Outter();
                    received2 = gson.fromJson(response, Group_Outter.class);
//                Toast.makeText(c , ""+received2.response.result ,Toast.LENGTH_SHORT).show();
                    if (received2.response.result.equals("1")) {
                        groupnames=received2.response.inner_faqs;

                        for (int i = 0; i < groupnames.size(); i++)
                        {
                            groupid.add(groupnames.get(i).group_id);
                            groupname.add(groupnames.get(i).group_name);
                            login_id.add(groupnames.get(i).login_id);
                            usernames.add(groupnames.get(i).group_name+" ("+groupnames.get(i).login_id+")");
                        }
//                        Toast.makeText(c , ""+usernames ,Toast.LENGTH_SHORT).show();
//                        Log.e("user",""+usernames);
                        LoginActivity.email.setAdapter(new ArrayAdapter(c, R.layout.itemlayoutforspinner,
                                R.id.itemspinner, usernames));

                    } else {
                        LoginActivity.pb.setVisibility(View.GONE);
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LoginActivity.pb.setVisibility(View.GONE);
                Log.e("Sucess", "" + error.toString());
                Log.e("errror", "" + error.toString());
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof TimeoutError)
                {
                    Toast.makeText(c, "Internet Connectivity Problem...", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
        LoginActivity.pb.setVisibility(View.VISIBLE);
    }
}
