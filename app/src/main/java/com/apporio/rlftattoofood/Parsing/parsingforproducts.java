package com.apporio.rlftattoofood.Parsing;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.HeaderViewListAdapter;
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
import com.apporio.rlftattoofood.Adapter.HomefragmentAdapter;
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.MainActivity;
import com.apporio.rlftattoofood.Setter_getter.Innerproductnames;
import com.apporio.rlftattoofood.Setter_getter.No_Result_settergetter;
import com.apporio.rlftattoofood.Setter_getter.Product_setter_getter;
import com.apporio.rlftattoofood.fragment.HomeFragment;
import com.apporio.rlftattoofood.singleton.VolleySingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import views.ProgressWheel;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class parsingforproducts {
    public  static StringRequest sr;
    public  static RequestQueue queue;


    public static  List<Innerproductnames> product_names = new ArrayList<>();


    public static  ArrayList<String> product_id = new ArrayList<String>();
    public static  ArrayList<String>   food_descp = new ArrayList<String>();
    public static  ArrayList<String>   food_calories = new ArrayList<String>();

    public static ArrayList<String>   food_name = new ArrayList<String>();
    public static  ArrayList<String>   food_image= new ArrayList<String>();

    public static  ArrayList<String>   no_of_units= new ArrayList<String>();



    public static void parsingforfoodnames(final Context c) {


        queue = VolleySingleton.getInstance(c).getRequestQueue();
        //   Toast.makeText(getActivity(),"id"+CategoryId,Toast.LENGTH_SHORT).show();
        String urlforRest_food  = Apis_url.all_products;
        urlforRest_food= urlforRest_food.replace(" ","%20");
        Log.e("bahjd", "" + urlforRest_food);
        sr = new StringRequest(Request.Method.POST, urlforRest_food, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Sucess", "" + response);
                //  Toast.makeText(LoginCleanline.this , ""+response ,Toast.LENGTH_SHORT).show();
                HomeFragment.pb.setVisibility(View.GONE);
                GsonBuilder gsonBuilder = new GsonBuilder();
                final Gson gson = gsonBuilder.create();

                product_id.clear();

                food_image.clear();

                food_name.clear();


                food_descp.clear();
                food_calories.clear();




                No_Result_settergetter received2 = new No_Result_settergetter();
                received2 = gson.fromJson(response, No_Result_settergetter.class);
                if (received2.response.result.equals("1")) {
                    Product_setter_getter product_setter_getter = gson.fromJson(response, Product_setter_getter.class);
                    product_names = product_setter_getter.response.innerproductnames;

                    //Log.e("item", "" + product_names.get(0).toppingsss.get(0).topping_id);


                    for (int i = 0; i < product_names.size(); i++)
                    {
                        product_id.add(product_names.get(i).product_id);

                        food_name.add(product_names.get(i).product_name);

                        food_descp.add(product_names.get(i).description);
                        food_image.add(product_names.get(i).product_image);
                        food_calories.add(product_names.get(i).calories);

                        no_of_units.add("0");

                    }

                    HomeFragment.lv.setAdapter(new HomefragmentAdapter(c, product_id, food_name,
                            food_descp, food_image, food_calories,
                            no_of_units));

                } else {
                    //Toast.makeText(getActivity(), "Please enter correct details", Toast.LENGTH_SHORT).show();
                    //pDialog.dismiss();
                    HomeFragment.pb.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //pDialog.dismiss();
                HomeFragment.pb.setVisibility(View.GONE);
                if(error instanceof NoConnectionError) {
                    Toast.makeText(c, "No internet connectivity..", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof TimeoutError)
                {
                    Toast.makeText(c, "Internet Connectivity Problem...", Toast.LENGTH_SHORT).show();
                }
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
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(sr);
        HomeFragment.pb.setVisibility(View.VISIBLE);
    }
}
