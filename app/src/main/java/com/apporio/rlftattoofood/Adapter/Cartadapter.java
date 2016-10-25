package com.apporio.rlftattoofood.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.rlftattoofood.CartActivity;
import com.apporio.rlftattoofood.Database.DBManager;
import com.apporio.rlftattoofood.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by apporio6 on 28-07-2016.
 */
public class Cartadapter  extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
        int value =0;
//    public static RealmResults<CartTable> ct4;
//    private ImageLoader mImageLoader;
//    int pos=0;
    static DBManager dbm;
    ArrayList<String> fooddescp = new ArrayList<String>();
    ArrayList<String> foodcalories = new ArrayList<String>();
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String> foodname = new ArrayList<String>();
    ArrayList<String> no_of_units = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    public Cartadapter(Context c, ArrayList<String> product_id, ArrayList<String> food_name,
                       ArrayList<String> food_descp, ArrayList<String> food_image,
                       ArrayList<String> food_calories, ArrayList<String> no_of_units) {

        ctc = c;
        dbm = new DBManager(c);
        inflater = LayoutInflater.from(this.ctc);
        this.product_id=product_id;
        this.foodname=food_name;
        this.images11=food_image;
        this.fooddescp=food_descp;
        this.foodcalories=food_calories;
        this.no_of_units=no_of_units;


    }

    @Override
    public int getCount() {
//        return product_id.size();
        return product_id.size();
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
        public android.widget.ImageView imgbg,plus,minus;
        public TextView product_name, resultquantity, calories, descp;
        TextView tv1, tv2;
        public ImageView ImageView,delete,imageView;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforcart, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }
        holder.product_name = (TextView)convertView.findViewById(R.id.namesss);
        holder.descp = (TextView)convertView.findViewById(R.id.descp);
        holder.delete = (ImageView)convertView.findViewById(R.id.deleteicon);
        holder.resultquantity = (TextView)convertView.findViewById(R.id.result);
        holder.calories = (TextView)convertView.findViewById(R.id.calories);
        holder.plus = (ImageView)convertView.findViewById(R.id.plus22);
        holder.minus = (ImageView)convertView.findViewById(R.id.minus22);
        holder.imageView = (ImageView)convertView.findViewById(R.id.img);
//
        holder.descp.setText(fooddescp.get(i));
        holder.product_name.setText(""+foodname.get(i));

        holder.resultquantity.setText(""+dbm.getNoofunitAccordingToProductId(product_id.get(i)));
        String images = ""+images11.get(i);
        images = "http://apporio.in/food_app/"+ images.replace("//","/");

        Log.e("bahjd",""+images);
        Picasso.with(ctc)
                .load(images)
                .placeholder(R.drawable.logo)
                .error(R.drawable.stub)
                .into(holder.imageView);

        holder.calories.setText(""+foodcalories.get(i));


        final Holder finalViewHolder = holder;

///////////////////////  PLS

     holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value = Integer.parseInt(finalViewHolder.resultquantity.getText().toString());
                finalViewHolder.resultquantity.setText("" + (value + 1));
                no_of_units.set(i, "" + (value + 1));
//                // Toast.makeText(ctc, "" + toppingnamearray.get(i), Toast.LENGTH_SHORT).show();
                dbm.addtocart(product_id.get(i), foodname.get(i),
                        fooddescp.get(i), foodcalories.get(i),images11.get(i),
                        no_of_units.get(i),"cart");

            }
        });
//        Toast.makeText(con,sharedpreferences.getString("Itemunits",("" + (value + 1))),Toast.LENGTH_SHORT).show();


/////////////////////  MINS
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.resultquantity.getText().toString());
                if (value > 0) {
                    if (value == 1) {
                        finalViewHolder.resultquantity.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
//                        // remove it from DB
//
                        dbm.removeItemfromDB(product_id.get(i),"cart");

                    }
                    if (value > 1) {
                        finalViewHolder.resultquantity.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
//                        ///edit it into DB
                        dbm.addtocart(product_id.get(i), foodname.get(i),
                                fooddescp.get(i), foodcalories.get(i),images11.get(i),
                                no_of_units.get(i),"cart");
//
                    }
                } else {
                    //do nothing
                }
            }
        });


        holder.delete.setTag(i);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                dbm.removeItemfromDB(product_id.get(pos),"cart");
            }
        });
        holder.imageView.setTag(i);

        holder.resultquantity.setTag(i);


        return convertView;
    }
}
