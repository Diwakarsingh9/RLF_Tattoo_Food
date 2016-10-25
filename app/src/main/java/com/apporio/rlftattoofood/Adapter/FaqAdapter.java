package com.apporio.rlftattoofood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apporio.rlftattoofood.R;

import java.util.ArrayList;

/**
 * Created by apporio6 on 28-07-2016.
 */
public class FaqAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;

    int pos=0;
    ArrayList<String> question = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<String> dates11 = new ArrayList<String>();
    ArrayList<String> food_category = new ArrayList<String>();
    ArrayList<String> names12 = new ArrayList<String>();
    ArrayList<String> prices21 = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    public FaqAdapter(Context c,ArrayList<String> que, ArrayList<String> ans) {

        ctc = c;


        inflater = LayoutInflater.from(this.ctc);
        this.question=que;
        this.answers=ans;
//        this.images11=image11;
//        this.dates11=datepro;
//        this.food_category=food_category;
//        this.names12=names11;
//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return question.size();
    }

    @Override
    public Object getItem(int position) {
        //Log.e("position", "Dish" + position);
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class Holder {
        public LinearLayout size, toppings, sausage, sizeno, toppingsno, sausageno;
        public ImageView imgbg,plus,minus;
        public TextView product_name, answer, product_price, noofunit_product, cuisines;
        TextView tv1, tv2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforfeedback, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }

        holder.product_name = (TextView) convertView.findViewById(R.id.question);
        holder.answer = (TextView) convertView.findViewById(R.id.answer);


        holder.product_name.setText(question.get(position));
        holder.answer.setText(answers.get(position));
        return convertView;
    }
}



