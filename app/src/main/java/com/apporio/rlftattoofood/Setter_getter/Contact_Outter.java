package com.apporio.rlftattoofood.Setter_getter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apporio6 on 30-07-2016.
 */
public class Contact_Outter {
    @SerializedName("response")
    public InnerSetter response = new InnerSetter();


    public class InnerSetter {
        @SerializedName("result")
        public String result;

        @SerializedName("Message")
        public Inner_contact inner_contact = new Inner_contact();
    }
}
