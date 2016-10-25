package com.apporio.rlftattoofood;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.rlftattoofood.Parsing.parsingforGroupids;
import com.apporio.rlftattoofood.Parsing.parsingforLogin;

import views.ProgressWheel;

public class LoginActivity extends Activity {
    public static View v11,v2,v3,v41;
    public static EditText pass;
    public static TextView next,forgot;
    public  static ProgressWheel pb;
    String data[]={"20142","24243","63453","34343"};
    public static Spinner email;
    public static  LoginActivity log;
    private static final int MY_PERMISSIONS_REQUEST_Call_Contacts = 0;
    public  static LoginActivity loginactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Statusbar.setStatusBarColor(LoginActivity.this, R.color.logored);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        next = (TextView)findViewById(R.id.login34);
        log = LoginActivity.this;
        email = (Spinner)findViewById(R.id.sp);
        pb=(ProgressWheel)findViewById(R.id.pbl);
        pass = (EditText)findViewById(R.id.zpc);
        v11 = (View)findViewById(R.id.view11);
        v2 = (View)findViewById(R.id.view12);
        v3 = (View)findViewById(R.id.view21);
        v41 = (View)findViewById(R.id.view22);
        loginactivity=LoginActivity.this;
        parsingforGroupids.parsing(LoginActivity.this);

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
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    v3.setVisibility(View.GONE);
                    v41.setVisibility(View.VISIBLE);
                } else {
                    v3.setVisibility(View.VISIBLE);
                    v41.setVisibility(View.GONE);
                }
            }
        });
//        forgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // Toast.makeText(getApplicationContext(), "Signing Up...", Toast.LENGTH_SHORT).show();
//
//
//                Intent in = new Intent(LoginActivity.this, ForgotActivity.class);
//                startActivity(in);
//
//
//
//
//
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LoginActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

              //  parsingforlogin.parsing(Loginactivity.this,email.getText().toString().trim(),zip.getText().toString().trim());
                Handler handler1 = new Handler();

                handler1.postDelayed(new Runnable() {

                    @Override
                    public void run()

                    {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            String permission = "android.permission.READ_PHONE_STATE";

                            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                                try {


                                    int permissionCheck = ContextCompat.checkSelfPermission(LoginActivity.this,
                                            android.Manifest.permission.READ_PHONE_STATE);

                                    if (ContextCompat.checkSelfPermission(LoginActivity.this,
                                            android.Manifest.permission.READ_PHONE_STATE)
                                            != PackageManager.PERMISSION_GRANTED) {

                                        // Should we show an explanation?
                                        if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                                                android.Manifest.permission.READ_PHONE_STATE)) {
                                            Toast.makeText(getApplicationContext(), "Please allow phone permission to login !!!", Toast.LENGTH_LONG).show();
                                            // Show an expanation to the user *asynchronously* -- don't block
                                            // this thread waiting for the user's response! After the user
                                            // sees the explanation, try again to request the permission.


                                        } else {

                                            // No explanation needed, we can request the permission.

                                            ActivityCompat.requestPermissions(LoginActivity.this,
                                                    new String[]{android.Manifest.permission.READ_PHONE_STATE},
                                                    MY_PERMISSIONS_REQUEST_Call_Contacts);

                                            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                                            String device_id = tm.getDeviceId();
                                            parsingforLogin.parsing(LoginActivity.this,parsingforGroupids.login_id.get(email.getSelectedItemPosition()).trim(),
                                                    pass.getText().toString().trim(),device_id );
                                        }
                                    } else {
                                        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                                        String device_id = tm.getDeviceId();
                                        parsingforLogin.parsing(LoginActivity.this,parsingforGroupids.login_id.get(email.getSelectedItemPosition()).trim(),
                                                pass.getText().toString().trim(),device_id );

                                    }
                                } catch (Exception e) {
                                    Log.e("ghghghhg", "" + e);
                                }
                            } else {
                                TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                                String device_id = tm.getDeviceId();
                                parsingforLogin.parsing(LoginActivity.this,parsingforGroupids.login_id.get(email.getSelectedItemPosition()).trim(),
                                        pass.getText().toString().trim(),device_id );

                            }

                            // parsingfornotification.parsing(MainActivity.this,regId,dd);

                        } else {
                            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                            String device_id = tm.getDeviceId();
                            parsingforLogin.parsing(LoginActivity.this,parsingforGroupids.login_id.get(email.getSelectedItemPosition()).trim(),
                                    pass.getText().toString().trim(),device_id );

                        }




                    }
                }, 1000);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        email.postDelayed(
                new Runnable() {
                    public void run() {
                        email.requestFocus();
                        InputMethodManager inputMethodManager = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(email, InputMethodManager.SHOW_IMPLICIT);
                    }
                }, 100);
        super.onResume();
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Call_Contacts: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    String device_id = tm.getDeviceId();
                    parsingforLogin.parsing(LoginActivity.this,parsingforGroupids.login_id.get(email.getSelectedItemPosition()).trim(),
                            pass.getText().toString().trim(),device_id );

                    if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


        return;
    }
}

