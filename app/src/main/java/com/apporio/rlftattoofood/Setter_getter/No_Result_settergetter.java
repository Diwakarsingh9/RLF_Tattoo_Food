package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class No_Result_settergetter {

    @SerializedName("response")
    public InnerSetter response = new InnerSetter();


    public class InnerSetter {
        @SerializedName("result")
        public String result;
    }
}
