package com.apporio.rlftattoofood.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.apporio.rlftattoofood.Adapter.FaqAdapter;
import com.apporio.rlftattoofood.Adapter.SpecificorderAdapter;
import com.apporio.rlftattoofood.CartActivity;
import com.apporio.rlftattoofood.Parsing.parsingforFaq;
import com.apporio.rlftattoofood.R;

import java.util.ArrayList;

import views.ProgressWheel;

public class FaqFragment extends Fragment {
    public  static ListView lv;
    ImageView back;
    FrameLayout cartll;
    ArrayList<String> Questions = new ArrayList<>();
    ArrayList<String> Answers = new ArrayList<>();
    FaqAdapter fap ;
    Context ctc;
    public static ProgressWheel pb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_faq, container, false);
        ctc = getActivity();
        lv = (ListView) v.findViewById(R.id.listView);

        pb = (ProgressWheel) v.findViewById(R.id.pb112);

        parsingforFaq.parsing(ctc);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
