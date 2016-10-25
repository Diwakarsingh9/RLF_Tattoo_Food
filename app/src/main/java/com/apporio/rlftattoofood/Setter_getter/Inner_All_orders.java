package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class Inner_All_orders {


    @SerializedName("order_id")
    public String order_id;

    @SerializedName("order_date")
    public String order_date;

    @SerializedName("status")
    public String order_status;

    @SerializedName("order_details")
    public List<OrderDetails> orderDetails = new ArrayList<>();

}
