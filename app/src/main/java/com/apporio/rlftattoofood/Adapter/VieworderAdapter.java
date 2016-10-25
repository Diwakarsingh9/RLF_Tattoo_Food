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
public class VieworderAdapter extends BaseAdapter {

    Context ctc;
    //String[] color_arr={"#d7b853","#E27556","#9D3B54","#523259","#4dbd7c"};
    final LayoutInflater inflater;
    String[] ad;

    //    public static RealmResults<CartTable> ct4;
//    private ImageLoader mImageLoader;
//    int pos=0;
//    static DBManager dbm;
    ArrayList<String> optionvalue = new ArrayList<>();
    ArrayList<String> orderid = new ArrayList<String>();
    ArrayList<String> orderdate = new ArrayList<String>();
    ArrayList<String> orderstatus = new ArrayList<String>();
    ArrayList<String> pro_quantity = new ArrayList<String>();
    //    ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options = new ArrayList<>();
    ArrayList<String> pro_price = new ArrayList<String>();
    ArrayList<String> images11 = new ArrayList<String>();

    ArrayList<String> deliver_time = new ArrayList<String>();


    //    public Cartadapter(Context c, ArrayList<String> pro_id, ArrayList<String> pro_name,
//                       ArrayList<String> pro_img, ArrayList<String> pro_quantity,
//                       ArrayList<ArrayList<Innermost_pro_options_cart>> pro_options, ArrayList<String> pro_price) {
//        optionvalue.clear();
    public VieworderAdapter(Context c, ArrayList<String> orderid, ArrayList<String> orderdate,
                            ArrayList<String> orderstatus){
        ctc = c;
//        dbm = new DBManager(c);
//        mImageLoader = VolleySingleton.getInstance(ctc)
//                .getImageLoader();
        inflater = LayoutInflater.from(this.ctc);
        this.orderid=orderid;
        this.orderdate=orderdate;
        this.orderstatus=orderstatus;
        this.pro_quantity=pro_quantity;
//        this.pro_options=pro_options;
        this.pro_price=pro_price;
//        ct4 = dbm.getFullTable();
//        for (int i = 0; i < ct4.size(); i++) {
//            Log.e("optionsdatabasediwww", ct4.size() + " " + ct4.get(i).getOption().toString() + "   " + ct4.get(i).getOption().toString());
//
//            JSONObject jinnerobject2 = new JSONObject();
//            optionvalue.add(ct4.get(i).getOption());
//            try {
//                jinnerobject2.put("diw",ct4.get(i).getOption());
//                jinnerobject2.put("diw22", optionvalue.get(i));
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

//        this.prices21=prices11;
//        this.deliver_time=deliver_time;

    }

    @Override
    public int getCount() {
//        return product_id.size();
        return orderid.size();
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
        public TextView product_name, resultquantity, product_price, descp;
        TextView orderid, orderdate;
        public android.widget.ImageView ImageView,delete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.itemlayoutforvieworder, null);
            holder = new Holder();
            convertView.setTag(holder);


        }
        else {
            holder = (Holder)convertView.getTag();
        }
        holder.orderid = (TextView)convertView.findViewById(R.id.orderid);
        holder.orderdate = (TextView)convertView.findViewById(R.id.date);
        holder.orderid.setText("Order "+Integer.parseInt(Integer.parseInt(position+"")+1+""));
        holder.orderdate.setText(orderdate.get(position)+"");

        return convertView;
    }
}