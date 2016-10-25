package com.apporio.rlftattoofood;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.rlftattoofood.Adapter.Cartadapter;
import com.apporio.rlftattoofood.Database.CartTable;
import com.apporio.rlftattoofood.Database.DBManager;
import com.apporio.rlftattoofood.Parsing.parsingforPlaceOrder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.RealmResults;
import views.ProgressWheel;

public class CartActivity extends Activity {
    ImageView back;
    public static ListView lv;
    public static List<CartTable> ct;
    public static TextView checkout,close;
    public static CartActivity cartact;
    public static ProgressWheel pb,pb22,pb23;
    public static  TextView slot,address,totalitem;
    public static DBManager dbm;
    public static RealmResults<CartTable> ct4;
    public static ArrayList<String> productid = new ArrayList<String>();
    public static ArrayList<String> food_name = new ArrayList<String>();
    public static ArrayList<String> food_descp = new ArrayList<String>();
    public static ArrayList<String> food_calories = new ArrayList<String>();
    public static ArrayList<String> food_image = new ArrayList<String>();
    public static ArrayList<String> food_quantity = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(CartActivity.this, R.color.logored);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        back= (ImageView)findViewById(R.id.imageView2);
        checkout= (TextView)findViewById(R.id.cok);
        totalitem = (TextView) findViewById(R.id.total_item);
        pb= (ProgressWheel)findViewById(R.id.pb123);
        lv= (ListView)findViewById(R.id.listviewcart);
        slot = (TextView) findViewById(R.id.slot);
        address = (TextView) findViewById(R.id.address);
        cartact = CartActivity.this;
        dbm = new DBManager(CartActivity.this);

        final SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(CartActivity.this);
        loadlistview();
        slot.setText(Html.fromHtml("<b>Delivery Slot</b>"+" - "+prefs2.getString("delivery_time","NA")));
        address.setText(Html.fromHtml("<b>Delivery Address</b>" + " - " + prefs2.getString("location","NA")));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsingforPlaceOrder.parsing(CartActivity.this, "" + getCartdetails(), prefs2.getString("groupid", ""));

            }
        });


    }

    public static void loadlistview() {
        ct = dbm.getFullTable();

        productid.clear();
        food_name.clear();
        food_descp.clear();
        food_calories.clear();
        food_image.clear();
        food_quantity.clear();

        for (int i = 0; i < ct.size(); i++) {
            productid.add(ct.get(i).getProductId());
            food_name.add(ct.get(i).getFoodname());
            food_descp.add(ct.get(i).getFoodDescp());
            food_calories.add(ct.get(i).getFoodCalories());
            food_image.add(ct.get(i).getFoodimage());
            food_quantity.add(ct.get(i).getFoodNoOfUnits());

        }


         lv.setAdapter(new Cartadapter(cartact, productid, food_name,
                 food_descp, food_image, food_calories,
                 food_quantity));


    }

    public JSONObject getCartdetails() {
        JSONObject obj = new JSONObject();
        JSONArray jsonArray2 = new JSONArray();
        try {
            obj.put("Order Details", jsonArray2);


            ct4 = dbm.getFullTable();
            try {

                for (int i = 0; i < ct4.size(); i++) {
                    JSONObject jinnerobject2 = new JSONObject();
                    jinnerobject2.put("product_id", ct4.get(i).getProductId());

                    jinnerobject2.put("qty", ct4.get(i).getFoodNoOfUnits());

                    jsonArray2.put(jinnerobject2);
                }

                //     Log.e("JSON ", jinnerobject.toString());
                Log.e("JSONARRAYrest ", jsonArray2.toString());

            } catch (JSONException e) {
                Log.e("gfgfrest", "" + e);
            }
        }catch (JSONException e){
            Log.e("erroror",""+e);
        }
        return obj;
    }
    public static void showSuccessDialog() {

        final Dialog dialog = new Dialog(cartact,android.R.style.Theme_Translucent);

        //  dialog.getWindow().setStatusBarColor(Loginscreenactivity.this.getResources().getColor(R.color.example_color));

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogforplaceorder);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        close = (TextView)dialog.findViewById(R.id.ok);


//




        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dbm.clearCartTable();
                dialog.dismiss();
                CartActivity.cartact.finish();

            }
        });

        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        totalitem.setText("" + dbm.getFullTable().size());
    }
}
