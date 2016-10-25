package com.apporio.rlftattoofood;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends Activity {
    public static View v11,v2,v3,v41;
    public static EditText email,zip;
    public static TextView next;
    public  static ProgressBar pb;
    public  static ForgotActivity fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarColor();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        next = (TextView)findViewById(R.id.rcvpass);
        email = (EditText)findViewById(R.id.username);
        pb=(ProgressBar)findViewById(R.id.pb);
        v11 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        fg= ForgotActivity.this;

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    v11.setVisibility(View.GONE);
                    v2.setVisibility(View.VISIBLE);
                }
                else{
                    v11.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.GONE);
                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForgotActivity.this, "Sent !!!", Toast.LENGTH_SHORT).show();
               // parsingforforgotpassword.parsing(ForgotActivity.this, email.getText().toString().trim());
                View view = ForgotActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)ForgotActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }


        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColor(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = ForgotActivity.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ForgotActivity.this.getResources().getColor(R.color.red));
        } else {
            Window window = ForgotActivity.this.getWindow();
            //  window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        email.postDelayed(
                new Runnable() {
                    public void run() {
                        email.requestFocus();
                        InputMethodManager inputMethodManager =  (InputMethodManager)ForgotActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(email,InputMethodManager.SHOW_IMPLICIT);
                    }
                },100);
        super.onResume();
    }
}