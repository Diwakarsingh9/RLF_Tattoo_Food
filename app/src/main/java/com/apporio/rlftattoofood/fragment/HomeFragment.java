package com.apporio.rlftattoofood.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apporio.rlftattoofood.Adapter.HomefragmentAdapter;
import com.apporio.rlftattoofood.Parsing.parsingforproducts;
import com.apporio.rlftattoofood.R;

import views.ProgressWheel;

/**
 * Created by apporio6 on 27-07-2016.
 */
public class HomeFragment extends Fragment {
    Context ctc;
    public static ListView lv;
    HomefragmentAdapter hadp ;
    public static ProgressWheel pb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_homefragment, container, false);
        ctc = getActivity();
        pb= (ProgressWheel)v.findViewById(R.id.pb112);
        lv= (ListView)v.findViewById(R.id.listView);

//        hadp= new HomefragmentAdapter(ctc, product_id, food_name, food_descp, food_image, food_calories, no_of_units);
//        lv.setAdapter(hadp);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                s_catid.clear();
//                s_catimg.clear();
//                s_catname.clear();
//                s_catstatus.clear();
//
//                for(int j=0;j<parsingforcategories.sub_category.get(position).size();j++){
//                    s_catid.add(parsingforcategories.sub_category.get(position).get(j).category_id);
//                    s_catimg.add(parsingforcategories.sub_category.get(position).get(j).imagess);
//                    s_catname.add(parsingforcategories.sub_category.get(position).get(j).namess);
//                    s_catstatus.add(parsingforcategories.sub_category.get(position).get(j).status);
//                }
//                Intent in = new Intent(ctc, Sub_categoryActivity.class);
//                in.putExtra("subcategory",parsingforcategories.catname.get(position));
//                in.putStringArrayListExtra("sub_category_id", s_catid);
//                in.putStringArrayListExtra("sub_category_name", s_catname);
//                in.putStringArrayListExtra("sub_category_img", s_catimg);
//                in.putStringArrayListExtra("sub_category_status", s_catstatus);
//                in.putExtra("act","category");
//                startActivity(in);

            }
        });


        return v;
    }



    @Override
    public void onResume() {

        super.onResume();
        parsingforproducts.parsingforfoodnames(ctc);
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
