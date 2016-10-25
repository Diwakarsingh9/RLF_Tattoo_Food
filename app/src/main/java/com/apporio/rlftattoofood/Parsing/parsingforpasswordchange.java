package com.apporio.rlftattoofood.Parsing;

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
import com.apporio.rlftattoofood.Setter_getter.Change_Pass_outter;
import com.apporio.rlftattoofood.Setter_getter.Inner_login;
import com.apporio.rlftattoofood.Setter_getter.Login_Outter;
import com.apporio.rlftattoofood.singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class parsingforpasswordchange {

    public static RequestQueue queue;
    public static StringRequest sr2;
    public static Inner_login data_list1;

    public static SharedPreferences prefs2;
    public static Context ctc22;

    public static void parsing(final Context activity,String oldpass, String newpass) {


        ctc22 = activity;

         prefs2 = PreferenceManager.getDefaultSharedPreferences(activity);

        queue = VolleySingleton.getInstance(activity).getRequestQueue();


            String locationurl2 = Apis_url.passwordchange.concat(prefs2.getString("groupid","null")).
                    concat(Apis_url.passwordchange1).
                    concat(oldpass).concat(Apis_url.passwordchange2).concat(newpass);
            locationurl2 = locationurl2.replace(" ", "%20");

            Log.e("url", "" + locationurl2);

            sr2 = new StringRequest(Request.Method.GET, locationurl2, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
//                    MainActivity.pbdd.setVisibility(View.GONE);

                    MainActivity.pbdd.setVisibility(View.GONE);




                    try {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        final Gson gson = gsonBuilder.create();
                        Change_Pass_outter received2 = new Change_Pass_outter();
                        received2 = gson.fromJson(response, Change_Pass_outter.class);

                        String result = received2.result;
                        if (result.equals("1")) {
                            Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();


                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
                            SharedPreferences.Editor edit2 = prefs.edit();


                            edit2.putString("password", "" + received2.password);


                            edit2.commit();
                            MainActivity.dialog.dismiss();






                        } else {

                            MainActivity.pbdd.setVisibility(View.GONE);

                            Toast.makeText(ctc22, "" + received2.msg, Toast.LENGTH_SHORT).show();

                        }


                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.e("exception", "" + e);
                        MainActivity.pbdd.setVisibility(View.GONE);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    MainActivity.pbdd.setVisibility(View.GONE);

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
            MainActivity.pbdd.setVisibility(View.VISIBLE);


        }

}
