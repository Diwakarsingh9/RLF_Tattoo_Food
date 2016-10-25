package com.apporio.rlftattoofood.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.apporio.rlftattoofood.Adapter.FaqAdapter;
import com.apporio.rlftattoofood.R;

import java.util.ArrayList;

import views.ProgressWheel;

/**
 * Created by apporio6 on 28-07-2016.
 */
public class ContactFragment extends Fragment {
    ImageView back, callus, mailus;
    FrameLayout cartll;
    public static ProgressWheel pb22;
    String phone,email;
    private static final int MY_PERMISSIONS_REQUEST_Call_Contacts = 0;

    Context ctc;
    public static ProgressWheel pb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.contact_fragment, container, false);
        ctc = getActivity();
        back = (ImageView) v. findViewById(R.id.imageView2);
        callus = (ImageView)  v.findViewById(R.id.call);
        mailus = (ImageView) v. findViewById(R.id.mail);
        cartll = (FrameLayout)  v.findViewById(R.id.cartll);
        pb = (ProgressWheel) v.findViewById(R.id.pb112);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctc);
        phone = prefs.getString("Phone", "00000000");
        email = prefs.getString("Email", "abc@gmail.com");

        callus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("phone", "" + phone);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String permission = "android.permission.CALL_PHONE";

                    if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        try {


                            int permissionCheck = ContextCompat.checkSelfPermission(ctc,
                                    Manifest.permission.CALL_PHONE);

                            if (ContextCompat.checkSelfPermission(ctc,
                                    Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED) {

                                // Should we show an explanation?
                                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                        Manifest.permission.CALL_PHONE)) {
                                    Toast.makeText(ctc, "Please allow phone permission to make calls !!!", Toast.LENGTH_LONG).show();
                                    // Show an expanation to the user *asynchronously* -- don't block
                                    // this thread waiting for the user's response! After the user
                                    // sees the explanation, try again to request the permission.


                                } else {

                                    // No explanation needed, we can request the permission.

                                    ActivityCompat.requestPermissions(getActivity(),
                                            new String[]{Manifest.permission.READ_PHONE_STATE},
                                            MY_PERMISSIONS_REQUEST_Call_Contacts);

                                    String posted_by = phone;
                                    String uri = "tel:" + posted_by.trim();
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse(uri));
                                    startActivity(intent);

                                }
                            } else {
                                String posted_by = phone;
                                String uri = "tel:" + posted_by.trim();
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse(uri));
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            Log.e("ghghghhg", "" + e);
                        }
                    } else {
                        String posted_by = phone;
                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }

                    // parsingfornotification.parsing(MainActivity.this,regId,dd);

                } else {
                    String posted_by = phone;
                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }




            }

        });
        mailus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto", ""+prefs.getString("adminemail","amit.s@tyretimes.com"), null));
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", ""+email, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Regarding RLF Tattoo Food  App");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                emailIntent.setType("text/plain");


            }
        });

        return v;
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

                    String posted_by = phone;
                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);

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
    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }
}
