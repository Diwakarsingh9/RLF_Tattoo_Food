package com.apporio.rlftattoofood.Parsing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.apporio.rlftattoofood.Adapter.VieworderAdapter;
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.Setter_getter.Inner_All_orders;
import com.apporio.rlftattoofood.Setter_getter.OrderDetails;
import com.apporio.rlftattoofood.Setter_getter.Outer_all_orders;
import com.apporio.rlftattoofood.fragment.View_OrderFragment;
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
public class parsingforViewOrder {

    public  static StringRequest sr;
    public  static RequestQueue queue;
    public static String Rest_id;
    public static SharedPreferences prefs2;
    public static List<Inner_All_orders> orderList = new ArrayList<>();
    public static ArrayList<String> orderid = new ArrayList<String>();
    public static ArrayList<String> orderdate = new ArrayList<String>();
    public static ArrayList<String> orderstatus = new ArrayList<String>();
    public static ArrayList<List<OrderDetails>> orderdetails = new ArrayList<>();

    public static void parsing(final Context c) {
//        final ProgressDialog pd = new ProgressDialog(c);
//        pd.setMessage("Loading ...");
//        pd.setCancelable(false);
        prefs2 = PreferenceManager.getDefaultSharedPreferences(c);
        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.orderrecent+prefs2.getString("groupid","");
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                 View_OrderFragment.pb.setVisibility(View.GONE);
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();

                    orderid.clear();
                    orderdate.clear();
                    orderstatus.clear();



                    Outer_all_orders received2 = new Outer_all_orders();
                    received2 = gson.fromJson(response, Outer_all_orders.class);

                    if (received2.response.result.equals("1")) {
                        orderList=received2.response.inner_all_orderses;

                        for (int i = 0; i < orderList.size(); i++)
                        {
                            orderid.add(orderList.get(i).order_id);
                            orderdate.add(orderList.get(i).order_date);
                            orderstatus.add(orderList.get(i).order_status);
                            orderdetails.add(orderList.get(i).orderDetails);

                        }
                        View_OrderFragment.lv.setAdapter(new VieworderAdapter(c, orderid,orderdate,orderstatus ));


                    } else {



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("eerrr", "" + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // pd.dismiss();
                View_OrderFragment.pb.setVisibility(View.GONE);
                Log.e("errror", "" + error.toString());
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof TimeoutError)
                {
                    Toast.makeText(c, "Internet Connectivity Problem...", Toast.LENGTH_SHORT).show();
                }
                // Toast.makeText(getActivity(), "Please enter the email and password", Toast.LENGTH_SHORT).show();

                Log.e("Sucess", "" + error.toString());
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
        View_OrderFragment.pb.setVisibility(View.VISIBLE);

        // pd.show();
    }
}
