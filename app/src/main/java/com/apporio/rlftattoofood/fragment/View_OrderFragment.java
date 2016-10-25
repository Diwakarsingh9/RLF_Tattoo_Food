package com.apporio.rlftattoofood.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apporio.rlftattoofood.Adapter.VieworderAdapter;
import com.apporio.rlftattoofood.Parsing.parsingforViewOrder;
import com.apporio.rlftattoofood.R;
import com.apporio.rlftattoofood.SpecificOrderActvity;

import java.util.ArrayList;

import views.ProgressWheel;

/**
 * Created by apporio6 on 28-07-2016.
 */
public class View_OrderFragment extends Fragment {
    Context ctc;
    public static ListView lv;
    VieworderAdapter hadp ;
    public static ProgressWheel pb;
    public static ArrayList<String> productid = new ArrayList<String>();
    public static ArrayList<String> food_name = new ArrayList<String>();
    public static ArrayList<String> food_descp = new ArrayList<String>();
    public static ArrayList<String> food_calories = new ArrayList<String>();
    public static ArrayList<String> food_image = new ArrayList<String>();
    public static ArrayList<String> food_quantity = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.vieworder_fragment, container, false);
        ctc = getActivity();
        pb= (ProgressWheel)v.findViewById(R.id.pb112);
        lv= (ListView)v.findViewById(R.id.listView);

//        hadp= new VieworderAdapter(ctc, orderid, orderdate, orderstatus);
//        lv.setAdapter(hadp);
         parsingforViewOrder.parsing(ctc);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productid.clear();
                food_image.clear();
                food_name.clear();
                food_descp.clear();
                food_calories.clear();
                food_quantity.clear();
//
                for(int j=0;j<parsingforViewOrder.orderdetails.get(position).size();j++){
                    productid.add(parsingforViewOrder.orderdetails.get(position).get(j).product_id);
                    food_image.add(parsingforViewOrder.orderdetails.get(position).get(j).product_image);
                    food_name.add(parsingforViewOrder.orderdetails.get(position).get(j).product_name);
                    food_descp.add(parsingforViewOrder.orderdetails.get(position).get(j).description);
                    food_calories.add(parsingforViewOrder.orderdetails.get(position).get(j).calories);
                    food_quantity.add(parsingforViewOrder.orderdetails.get(position).get(j).qty);
                }
                Intent in = new Intent(ctc, SpecificOrderActvity.class);
                in.putStringArrayListExtra("product_id",productid);
                in.putStringArrayListExtra("food_image",food_image);
                in.putStringArrayListExtra("food_name",food_name);
                in.putStringArrayListExtra("food_descp",food_descp);
                in.putStringArrayListExtra("food_calories",food_calories);
                in.putStringArrayListExtra("food_quantity",food_quantity);

                startActivity(in);

            }
        });


        return v;
    }



    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

}
