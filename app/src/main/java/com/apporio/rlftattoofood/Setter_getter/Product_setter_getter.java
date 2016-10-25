package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class Product_setter_getter {
    @SerializedName("response")
    public InnerSetter response = new InnerSetter();


    public class InnerSetter {
        @SerializedName("result")
        public String result;

        @SerializedName("Message")
        public List<Innerproductnames> innerproductnames = new ArrayList<Innerproductnames>();
    }
}
