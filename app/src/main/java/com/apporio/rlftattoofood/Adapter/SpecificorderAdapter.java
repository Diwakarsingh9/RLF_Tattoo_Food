package com.apporio.rlftattoofood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apporio.rlftattoofood.Database.DBManager;
import com.apporio.rlftattoofood.R;
import com.apporio.rlftattoofood.SpecificOrderActvity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by apporio6 on 28-07-2016.
 */
public class SpecificorderAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;

    //    public static RealmResults<CartTable> ct4;
//    private ImageLoader mImageLoader;
//    int pos=0;
//    static DBManager dbm;
    ArrayList<String> fooddescp = new ArrayList<String>();
    ArrayList<String> foodcalories = new ArrayList<String>();
    ArrayList<String> product_id = new ArrayList<String>();
    ArrayList<String> foodname = new ArrayList<String>();
    ArrayList<String> no_of_units = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    //    public Cartadapter(Context c, ArrayList<String> pro_id, ArrayList<String> pro_name,
//                       ArrayList<String> pro_img, ArrayList<String> pro_quantity,
//                       ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options, ArrayList<String> pro_price) {
//        optionvalue.clear();
    public SpecificorderAdapter(SpecificOrderActvity specificOrderActvity, ArrayList<String> productid, ArrayList<String> food_name, ArrayList<String> food_descp,
                                ArrayList<String> food_image, ArrayList<String> food_calories,
                                ArrayList<String> food_quantity) {
//    public SpecificorderAdapter(SpecificOrderActvity specificOrderActvity){
        ctc = specificOrderActvity;

        inflater = LayoutInflater.from(this.ctc);
        this.product_id=productid;
        this.foodname=food_name;
        this.images11=food_image;
        this.fooddescp=food_descp;
        this.foodcalories=food_calories;
        this.no_of_units=food_quantity;


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

    static class ViewHolder {

        public ImageView imageview, plusbtn, minusbtn, bannerimage;
        public TextView product_name, descp, product_price, noofunit_product, calories;


    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        ViewHolder viewHolder = new ViewHolder();
        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) ctc.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rowView = inflater.inflate(R.layout.itemlayoutforspecificorder, null);
            // configure view holder

            viewHolder.imageview = (ImageView) rowView.findViewById(R.id.imageView);
            viewHolder.product_name = (TextView) rowView.findViewById(R.id.itemTextView);
            viewHolder.descp = (TextView) rowView.findViewById(R.id.descp);
            viewHolder.noofunit_product = (TextView) rowView.findViewById(R.id.result);
            viewHolder.calories = (TextView) rowView.findViewById(R.id.calories);

            rowView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)rowView.getTag();
        }
            // configure view holder




        viewHolder.descp.setText(fooddescp.get(i));
        viewHolder.product_name.setText(""+foodname.get(i));

        viewHolder.noofunit_product.setText("" + no_of_units.get(i));
        String images = ""+images11.get(i);
        images = "http://apporio.in/food_app/"+ images.replace("//","/");

//          Log.e("bahjd",""+images);
        Picasso.with(ctc)
                .load(images)
                .placeholder(R.drawable.logo)
                .error(R.drawable.stub)
                .into(viewHolder.imageview);

        viewHolder.calories.setText(foodcalories.get(i));

        return rowView;
    }
}
