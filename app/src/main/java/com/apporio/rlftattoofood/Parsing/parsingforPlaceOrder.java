package com.apporio.rlftattoofood.Parsing;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
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
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.CartActivity;
import com.apporio.rlftattoofood.Setter_getter.Order_result;
import com.apporio.rlftattoofood.singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class parsingforPlaceOrder {

    public static StringRequest sr,sr2;
    public static RequestQueue queue,queue2;

    public static void parsing(final Context c, String order, String groupid) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);

        queue = VolleySingleton.getInstance(c).getRequestQueue();

        String urlforRest_food = Apis_url.placeorder.concat(order).concat("&group_id=").concat(groupid);
                urlforRest_food = urlforRest_food.replace(" ", "%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
//                pd.dismiss();
               CartActivity. pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

//                    catname.clear();
//                    catid.clear();
                    Order_result os = new Order_result();
                    os= gson.fromJson(response,Order_result.class);
                    if(os.result.equals("0")){
                        Toast.makeText(c, "" + os.msg, Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(c, "" + os.msg, Toast.LENGTH_SHORT).show();
                        CartActivity.showSuccessDialog();

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CartActivity.pb.setVisibility(View.GONE);
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
        CartActivity.pb.setVisibility(View.VISIBLE);
    }
}
