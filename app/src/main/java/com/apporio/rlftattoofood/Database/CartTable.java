package com.apporio.rlftattoofood.Database;



import io.realm.RealmObject;

/**
 * Created by samir on 10/07/15.
 */
public class CartTable extends RealmObject {


    private String ProductId ;

    private String Foodname ;

    private  String FoodCalories ;
    private String Foodimage ;
    private String FoodNoOfUnits;
    private String FoodDescp ;










    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }


    public String getFoodCalories() {
        return FoodCalories;
    }

    public void setFoodCalories(String foodCalories) {
        FoodCalories = foodCalories;
    }

    public String getFoodNoOfUnits() {
        return FoodNoOfUnits;
    }

    public void setFoodNoOfUnits(String foodNoOfUnits) {
        FoodNoOfUnits = foodNoOfUnits;
    }


    public String getFoodname() {
        return Foodname;
    }

    public void setFoodname(String foodname) {
        Foodname = foodname;
    }




    public String getFoodimage() {
        return Foodimage;
    }

    public void setFoodimage(String foodimage) {
        Foodimage = foodimage;
    }


    public String getFoodDescp() {
        return FoodDescp;
    }

    public void setFoodDescp(String foodDescp) {
        FoodDescp = foodDescp;
    }




}
