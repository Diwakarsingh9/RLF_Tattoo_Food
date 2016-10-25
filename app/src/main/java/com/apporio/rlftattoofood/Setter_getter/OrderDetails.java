package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class OrderDetails {

    @SerializedName("product_id")
    public String product_id;

    @SerializedName("product_name")
    public String product_name;

    @SerializedName("product_image")
    public String product_image;

    @SerializedName("description")
    public String description;

    @SerializedName("calories")
    public String calories;

    @SerializedName("Qty")
    public String qty;
}
