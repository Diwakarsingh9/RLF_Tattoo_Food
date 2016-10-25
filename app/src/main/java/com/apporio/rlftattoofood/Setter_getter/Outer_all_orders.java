package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class Outer_all_orders {

    @SerializedName("response")
    public InnerSetter response = new InnerSetter();


    public class InnerSetter {
        @SerializedName("result")
        public String result;

        @SerializedName("Message")
        public ArrayList<Inner_All_orders> inner_all_orderses = new ArrayList<Inner_All_orders>();
    }
}
