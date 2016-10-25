package com.apporio.rlftattoofood;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.apporio.rlftattoofood.Adapter.Cartadapter;
import com.apporio.rlftattoofood.Adapter.SpecificorderAdapter;
import com.apporio.rlftattoofood.Parsing.parsingforViewOrder;

import java.util.ArrayList;

import views.ProgressWheel;

public class SpecificOrderActvity extends Activity {
    ImageView back;
    public static ListView lv;

    public static ProgressWheel pb,pb22,pb23;

    public static ArrayList<String> productid = new ArrayList<String>();
    public static ArrayList<String> food_name = new ArrayList<String>();
    public static ArrayList<String> food_descp = new ArrayList<String>();
    public static ArrayList<String> food_calories = new ArrayList<String>();
    public static ArrayList<String> food_image = new ArrayList<String>();
    public static ArrayList<String> food_quantity = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(SpecificOrderActvity.this, R.color.logored);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_order_actvity);
        back= (ImageView)findViewById(R.id.imageView2);
        pb= (ProgressWheel)findViewById(R.id.pb123);
        lv = (ListView)findViewById(R.id.listviewspecific);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        productid= getIntent().getStringArrayListExtra("product_id");
        food_name= getIntent().getStringArrayListExtra("food_name");
        food_descp= getIntent().getStringArrayListExtra("food_descp");
        food_calories= getIntent().getStringArrayListExtra("food_calories");
        food_image= getIntent().getStringArrayListExtra("food_image");
        food_quantity= getIntent().getStringArrayListExtra("food_quantity");

//        lv.setAdapter(new SpecificorderAdapter(SpecificOrderActvity.this));
        lv.setAdapter(new SpecificorderAdapter(SpecificOrderActvity.this, productid, food_name,
                food_descp, food_image, food_calories,
                food_quantity));
    }
}
