package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class Login_Outter {
    @SerializedName("msg")
    public String msg;



        @SerializedName("result")
        public String result;

        @SerializedName("User_details")
        public Inner_login User_details = new Inner_login();

}
