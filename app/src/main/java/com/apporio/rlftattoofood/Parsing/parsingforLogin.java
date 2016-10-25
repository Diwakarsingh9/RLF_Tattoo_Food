package com.apporio.rlftattoofood.Parsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.LoginActivity;
import com.apporio.rlftattoofood.MainActivity;
import com.apporio.rlftattoofood.Setter_getter.Inner_login;
import com.apporio.rlftattoofood.Setter_getter.Login_Outter;
import com.apporio.rlftattoofood.singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class parsingforLogin {

    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_login data_list1;


    public static Context ctc22;

    public static void parsing(final Context activity,String s1, String s2,String imei) {


        ctc22 = activity;

        if (s1.equals("") || s2.equals("")) {
            Toast.makeText(ctc22, "Required fields missing ....", Toast.LENGTH_SHORT).show();
        } else {
            queue = VolleySingleton.getInstance(activity).getRequestQueue();


            String locationurl2 = Apis_url.login.concat(s1).concat("&password=").concat(s2).concat("&did=").concat(imei).concat("&flag=1");
            locationurl2 = locationurl2.replace(" ", "%20");

            Log.e("url", "" + locationurl2);

            sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//

                    LoginActivity.pb.setVisibility(View.GONE);

                    SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(activity);
                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Login_Outter received2 = new Login_Outter();
                        received2 = gson.fromJson(response, Login_Outter.class);

                        String result = received2.result;
                        if (result.equals("1")) {
                            data_list1 = received2.User_details;
                            Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                            SharedPreferences.Editor edit2 = prefs.edit();

                            edit2.putBoolean("pref_previously_started", Boolean.TRUE);
                            edit2.putString("groupname", "" + data_list1.group_name);
                            edit2.putString("groupid", "" + data_list1.group_id);
                            edit2.putString("grouptype", "" + data_list1.group_type);
                            edit2.putString("loginid", "" + data_list1.login_id);
                            edit2.putString("password", "" + data_list1.password);
                            edit2.putString("member", "" + data_list1.member);
                            edit2.putString("description", "" + data_list1.description);
                            edit2.putString("delivery_time", "" + data_list1.delivery_time);
                            edit2.putString("did", "" + data_list1.did);
                            edit2.putString("location", "" + data_list1.location);
                            edit2.putString("flag", "" + data_list1.flag);
                            edit2.putString("status", "" + data_list1.status);

                            edit2.commit();


                            SharedPreferences.Editor edit22 = prefs2.edit();

                            edit22.putBoolean("pref_previously_started", Boolean.TRUE);
                            edit22.commit();


                            Intent in = new Intent(activity, MainActivity.class);
                            activity.startActivity(in);
                            LoginActivity.log.finish();
                            Log.e("details", "" + data_list1.group_name + " " + data_list1.location);


                        } else {

                            LoginActivity.pb.setVisibility(View.GONE);
                            Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();

                        }


                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.e("exception", "" + e);
                        LoginActivity.pb.setVisibility(View.GONE);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    LoginActivity.pb.setVisibility(View.GONE);
                    Log.e("errror", "" + error.toString());
                    if (error instanceof NoConnectionError) {
                        Toast.makeText(activity, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof TimeoutError) {
                        Toast.makeText(activity, "Internet Connectivity Problem...", Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

                }
            });
            sr2.setRetryPolicy(new DefaultRetryPolicy(50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(sr2);
            LoginActivity.pb.setVisibility(View.VISIBLE);


        }
    }
}
