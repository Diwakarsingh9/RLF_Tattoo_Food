package com.apporio.rlftattoofood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apporio.rlftattoofood.Database.DBManager;
import com.apporio.rlftattoofood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import views.CustomRatingBar;

/**
 * Created by apporio6 on 27-07-2016.
 */
public class HomefragmentAdapter  extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;
    int pos=0;
    static DBManager dbm;
    int value,click=0;
    String category_name[] ;
    ArrayList<String> fooddescp = new ArrayList<String>();
    ArrayList<String> foodcalories = new ArrayList<String>();
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String> foodname = new ArrayList<String>();
    ArrayList<String> no_of_units = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    public  HomefragmentAdapter(Context c, ArrayList<String> product_id, ArrayList<String> food_name,
                                ArrayList<String> food_descp, ArrayList<String> food_image,
                                ArrayList<String> food_calories, ArrayList<String> no_of_units){
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



    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();
        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) ctc.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rowView = inflater.inflate(R.layout.itemlayoutformainlist, null);
            // configure view holder

            viewHolder.imageview = (ImageView) rowView.findViewById(R.id.imageView);
            viewHolder.product_name = (TextView) rowView.findViewById(R.id.itemTextView);
            viewHolder.descp = (TextView) rowView.findViewById(R.id.descp);
            viewHolder.noofunit_product = (TextView) rowView.findViewById(R.id.result);
            viewHolder.calories = (TextView) rowView.findViewById(R.id.calories);
            viewHolder.plusbtn = (ImageView) rowView.findViewById(R.id.plus22);
            viewHolder.minusbtn = (ImageView) rowView.findViewById(R.id.minus22);
            rowView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)rowView.getTag();
        }

        viewHolder.descp.setText(fooddescp.get(i));
        viewHolder.product_name.setText(""+foodname.get(i));

        viewHolder.noofunit_product.setText(""+dbm.getNoofunitAccordingToProductId(product_id.get(i)));
        String images = ""+images11.get(i);
        images = "http://apporio.in/food_app/"+ images.replace("//","/");

//          Log.e("bahjd",""+images);
        Picasso.with(ctc)
                .load(images)
                .placeholder(R.drawable.logo)
                .error(R.drawable.stub)
                .into(viewHolder.imageview);

         viewHolder.calories.setText(foodcalories.get(i));


        final ViewHolder finalViewHolder = viewHolder;

///////////////////////  PLS

        viewHolder.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value = Integer.parseInt(finalViewHolder.noofunit_product.getText().toString());
                finalViewHolder.noofunit_product.setText("" + (value + 1));
                no_of_units.set(i, "" + (value + 1));
//                // Toast.makeText(ctc, "" + toppingnamearray.get(i), Toast.LENGTH_SHORT).show();
                dbm.addtocart(product_id.get(i), foodname.get(i),
                        fooddescp.get(i), foodcalories.get(i),images11.get(i),
                        no_of_units.get(i),"mainactivity");

            }
        });
//        Toast.makeText(con,sharedpreferences.getString("Itemunits",("" + (value + 1))),Toast.LENGTH_SHORT).show();


/////////////////////  MINS
        viewHolder.minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(finalViewHolder.noofunit_product.getText().toString());
                if (value > 0) {
                    if (value == 1) {
                        finalViewHolder.noofunit_product.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
//                        // remove it from DB
//
                        dbm.removeItemfromDB(product_id.get(i),"mainactivity");

                    }
                    if (value > 1) {
                        finalViewHolder.noofunit_product.setText("" + (value - 1));
                        no_of_units.set(i, "" + (value - 1));
//                        ///edit it into DB
                        dbm.addtocart(product_id.get(i), foodname.get(i),
                                fooddescp.get(i), foodcalories.get(i),images11.get(i),
                                no_of_units.get(i),"mainactivity");
//
                    }
                } else {
                    //do nothing
                }
            }
        });



        viewHolder.imageview.setTag(i);

        viewHolder.noofunit_product.setTag(i);



        return rowView;
    }

    static class ViewHolder {

        public ImageView imageview, plusbtn, minusbtn, bannerimage;
        public TextView product_name, descp, product_price, noofunit_product, calories;


    }
}