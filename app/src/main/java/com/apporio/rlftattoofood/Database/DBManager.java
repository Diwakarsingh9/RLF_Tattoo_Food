package com.apporio.rlftattoofood.Database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.apporio.rlftattoofood.CartActivity;
import com.apporio.rlftattoofood.MainActivity;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by samir on 10/07/15.
 */
public class DBManager {

    public static CartTable ct ;

    public  Realm myRealm;

    public static Context con ;

   // public static EventBus bus = EventBus.getDefault() ;

    public DBManager(Context con){
        this.con = con ;

        myRealm = Realm.getInstance(con);
    }

    public   void addtocart(String ProductId,
                            String food_name, String fooddescp, String foodcalories, String foodimage,
                            String food_no_of_units, String acitivtycome){

        if(checkproductExsistance(ProductId)){
            ////change already saved details in database
            changeExsistingRowintable(ProductId,food_no_of_units,acitivtycome);
        }else {
            /////  create new row in database
            createnreRowintable(ProductId, food_name, fooddescp, foodcalories,foodimage,
                    food_no_of_units,acitivtycome);
        }

    }


    private  void createnreRowintable(String productId, String food_name,
                                      String fooddescp, String foodcalories, String foodimage,
                                      String food_no_of_units, String acitivtycome) {
        Log.e("hfdhg","nounits   ---"+""+food_no_of_units);
//        new CartTable(ProductId,
//                rest_id,
//                food_name,
//                foodtype,
//                foodprice,foodimage,foodrating,food_no_of_units,toppingprice).save();
//
//        updateTotalOnActionBar();
//        showdataoncart();

        myRealm.beginTransaction();
        CartTable pd = myRealm.createObject(CartTable.class);

        pd.setProductId(productId);
        pd.setFoodname(food_name);
        pd.setFoodNoOfUnits(food_no_of_units);
        pd.setFoodCalories(foodcalories);
        pd.setFoodDescp(fooddescp);
        pd.setFoodimage(foodimage);
        myRealm.commitTransaction();
        showdataoncart(acitivtycome);
       // countNoofRowsInDatabse();

    }





    public  void changeExsistingRowintable(String productId, String food_no_of_units, String acitivtycome) {
        Log.e("hfdhg", "nounits   ---" + food_no_of_units);
        CartTable tobechangedelement =
                myRealm.where(CartTable.class)
                        .equalTo("ProductId", "" + productId)
                        .findFirst();

        Log.e("change",""+productId+" "+food_no_of_units);
        myRealm.beginTransaction();
        tobechangedelement.setProductId(productId);
        tobechangedelement.setFoodNoOfUnits(food_no_of_units);
        myRealm.commitTransaction();
//        updateTotalOnActionBar();
        showdataoncart(acitivtycome);
    }




    public  void removeItemfromDB(String productId,String activitycome){

        CartTable tobechangedelement =
                myRealm.where(CartTable.class).equalTo("ProductId", "" + productId)
                .findFirst();


        myRealm.beginTransaction();
        tobechangedelement.removeFromRealm();
        Toast.makeText(con, "Item Removed", Toast.LENGTH_SHORT).show();
        myRealm.commitTransaction();

//        updateTotalOnActionBar();
        showdataoncart(activitycome);
    }


    public   int countNoofRowsInDatabse(){
        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();

        Log.e("No Rows in CART TABLE ", "" + results.size());
        return results.size();

    }



    public RealmResults<CartTable> getFullTable(){
        RealmResults<CartTable> results = myRealm.where(CartTable.class).findAll();
        return  results ;
    }







    public  void clearCartTable(){
        myRealm.beginTransaction();
        myRealm.allObjects(CartTable.class).clear();
//      myRealm.where(CartTable.class).findAll().clear();
        myRealm.commitTransaction();
//        showdataoncart(activitycome);
    }

    public static void showdataoncart (String s){

        if(s.equals("mainactivity")){
            MainActivity.totlitem.setText(""+new DBManager(con).getFullTable().size());
        }
        else {

            CartActivity.totalitem.setText(""+new DBManager(con).getFullTable().size());
            CartActivity.loadlistview();

        }


    }


    public   String getNoofunitAccordingToProductId(String productId){


        if(checkproductExsistance(productId)){
           // Log.e("fgdfdfdfdf",""+id);

            CartTable tobechangedelement =
                    myRealm.where(CartTable.class).equalTo("ProductId", "" + productId)
                           .findFirst();


            return  tobechangedelement.getFoodNoOfUnits();
        }else {
            return "0";
        }


    }

    public  boolean checkproductExsistance(String productId){

        boolean value  = false;



               if( myRealm.where(CartTable.class)
                        .equalTo("ProductId", ""+productId)
                        .count()==0)
     {
            value = false ;
        }else {
            value = true ;
        }
        Log.e("valuesscheck",""+value);
        return  value ;

    }


}
