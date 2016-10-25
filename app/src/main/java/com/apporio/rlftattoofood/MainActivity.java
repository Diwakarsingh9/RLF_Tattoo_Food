package com.apporio.rlftattoofood;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.apporio.rlftattoofood.Api_s.Apis_url;
import com.apporio.rlftattoofood.Database.DBManager;
import com.apporio.rlftattoofood.Parsing.parsingforContactUS;
import com.apporio.rlftattoofood.Parsing.parsingforpasswordchange;
import com.apporio.rlftattoofood.fragment.ContactFragment;
import com.apporio.rlftattoofood.fragment.FaqFragment;
import com.apporio.rlftattoofood.fragment.HomeFragment;
import com.apporio.rlftattoofood.fragment.View_OrderFragment;

import views.ProgressWheel;

public class MainActivity extends AppCompatActivity {
    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    Fragment fragment = null;
    public static String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java
    public String state[]={"Delhi/Ncr","Maharashtra","Mumbai","Bihar"};
    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    ImageView back2;
    TextView passalert,done;

    EditText oldp,conp,newp,named,mobile;
    public static Context ctc2;
    public static ProgressWheel pbdd;
    public static Context ctc;
    private static String mCurrentPhotoPath;
    public static String NAME = "";
    public static String EMAIL = "";
    public static String PROFILE = "";
    private android.support.v7.widget.Toolbar toolbar;                              // Declaring the Toolbar Object

    public static RecyclerView mRecyclerView;                           // Declaring RecyclerView
    public static RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    public static RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    public static DrawerLayout Drawer;
    public static MainActivity mainact;

    public static String TITLES[] = {"Home","View Order","Change Password","Contact Us","FAQ","Log Out"};
    public static int ICONS[] = {
            R.drawable.home,
            R.drawable.vieworder,
            R.drawable.password,R.drawable.contact,
            R.drawable.faq,R.drawable.logout};
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    static DBManager dbm;
    public static Dialog dialog;
    View layout, layout2;
    public static TextView text, textforschedule, cancel, confirm, text22, cabtype, couponsavailable;
    Typeface font;
    Bitmap bitmap1;
    FrameLayout cartll;
    public static TextView head,totlitem;
    ProgressDialog prgDialog;
//    RequestParams params = new RequestParams();
//    GoogleCloudMessaging gcmObj;
    Context applicationContext;
    String regId = "";
    SharedPreferences prefs2;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    AsyncTask<Void, Void, String> createRegIdTask;
    public static Boolean previouslyStarted22=false;
    public static final String REG_ID = "regId";
    public static final String EMAIL_ID = "eMailId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        cartll = (FrameLayout) findViewById(R.id.cartll);
        head = (TextView) findViewById(R.id.header);
//        search = (ImageView) findViewById(R.id.sear);
        totlitem = (TextView) findViewById(R.id.total_item);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        prefs2 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        mRecyclerView.setHasFixedSize(true);
        dbm = new DBManager(MainActivity.this);

        fragment = new HomeFragment();

        if (fragment != null) {
            head.setText("RLF Tattoo Food");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        cartll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, CartActivity.class);
                startActivity(in);
            }
        });

        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.drawer);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        };

        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Drawer.openDrawer(Gravity.LEFT);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        totlitem.setText("" + dbm.getFullTable().size());
        if(Apis_url.Contactdone.equals("0")) {
            parsingforContactUS.parsing(MainActivity.this);
        }
        mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mRecyclerView.setAdapter(mAdapter);
    }



    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        // Creating a ViewHolder which extends the RecyclerView View Holder
        // ViewHolder are used to to store the inflated views in order to recycle them

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            int Holderid;

            TextView textView;
            ImageView imageView;
            public ImageView profile11;
            public TextView Name;
            TextView email;
            LinearLayout llforprof;
            LinearLayout itemll;

            public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
                super(itemView);


                // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

                if (ViewType == TYPE_ITEM) {
                    textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                    imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                    itemll = (LinearLayout) itemView.findViewById(R.id.llfornavi);
                    itemll.setOnClickListener(this);
                    // Creating ImageView object with the id of ImageView from item_row.xml
                    Holderid = 1;

                    // setting holder id as 1 as the object being populated are of type item row
                } else {

                    // Creating Text View object from header.xml for email

                    profile11 = (ImageView) itemView.findViewById(R.id.imageView);
                    llforprof = (LinearLayout) itemView.findViewById(R.id.llforprofile);
                    profile11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.Drawer.closeDrawer(Gravity.LEFT);
                            //  showcamerdialog();
                        }
                    });
                    llforprof.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            Intent in = new Intent(ctc2, Profileactivity.class);
//                            ctc2.startActivity(in);
                        }
                    });

                    // Creating Image view object from header.xml for profile pic
                    Holderid = 0;
                }
            }


            @Override
            public void onClick(View v) {
                try {

                    if (mNavTitles[getPosition() - 1].equals("Home")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new HomeFragment();

                                if (fragment != null) {
                                    head.setText("RLF Tattoo Food");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);

                    }
                    else if (mNavTitles[getPosition() - 1].equals("View Order")) {
                        Drawer.closeDrawer(Gravity.LEFT);
                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new View_OrderFragment();

                                if (fragment != null) {
                                    head.setText("View Order");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);


                    }



                    else if (mNavTitles[getPosition() - 1].equals("Change Password")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                            //    fragment = new ChangePasswordFragment();

                                showdialogforpassword();
                            }
                            //startThread();
                        }
                                , 200);

                    }

                    else if (mNavTitles[getPosition() - 1].equals("FAQ")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new FaqFragment();

                                if (fragment != null) {
                                    head.setText("FAQ");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);
                    }


                    else if (mNavTitles[getPosition() - 1].equals("Contact Us")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {
                                fragment = new ContactFragment();

                                if (fragment != null) {
                                    head.setText("Contact Us");
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.container3, fragment).commit();

                                } else {
                                    Log.e("MainActivity", "Error in creating fragment");
                                }
                            }
                            //startThread();
                        }
                                , 200);

                    }

                    else if (mNavTitles[getPosition() - 1].equals("Log Out")) {
                        Drawer.closeDrawer(Gravity.LEFT);

                        Handler handler = new Handler();

                        handler.postDelayed(new Runnable() {

                            @Override
                            public void run()

                            {


                                new AlertDialogWrapper.Builder(MainActivity.this)
                                        .setTitle("Log Out")
                                        .setMessage("Are you sure want to logout?")
                                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                                                SharedPreferences.Editor edit = prefs.edit();
                                                edit.putBoolean("pref_previously_started", false);
                                                edit.commit();
                                                Intent in = new Intent(MainActivity.this, SplashActivity.class);
                                                startActivity(in);
                                                finish();
                                                totlitem.setText("" );

                                                mAdapter = new MyAdapter(MainActivity.this, TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

                                                mRecyclerView.setAdapter(mAdapter);
                                            }
                                        }).show();

                            }
                            //startThread();
                        }
                                , 200);
                    }
                    else{
                        //
                    }
                }catch (Exception e){
                    Log.e("ddddd", "" + e);
                }
            }

        }



        public MyAdapter(MainActivity mainActivity, String Titles[], int Icons[], String Name, String Email, String Profile){ // MyAdapter Constructor with titles and icons parameter
            // titles, icons, name, email, profile pic are passed from the main activity as we
            mNavTitles = Titles;                //have seen earlier
            mIcons = Icons;
            name = Name;
            email = Email;
            //profile = Profile;
            ctc2= mainActivity;
            //here we assign those passed values to the values we declared here
            //in adapter


        }



        //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
        //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
        // if the viewType is TYPE_HEADER
        // and pass it to the view holder

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false); //Inflating the layout

                ViewHolder vhItem = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhItem; // Returning the created object

                //inflate your layout and pass it to view holder

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false); //Inflating the layout

                ViewHolder vhHeader = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view

                return vhHeader; //returning the object created


            }
            return null;

        }

        //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
        // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
        // which view type is being created 1 for item row
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
                // position by 1 and pass it to the holder while setting the text and image
                holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
                holder.imageView.setImageResource(mIcons[position -1]);
                // Settimg the image with array of our icons
            }
            else{

//                Picasso.with(MainActivity.this)
//                        .load("http://www.wscubetechapps.in/mobileteam/OneTapTakeway_app/" + profile)
//                        .placeholder(R.drawable.download) // optional
//                        .error(R.drawable.download)         // optional
//                        .into(holder.profile11);
                //  holder.profile11.setImageBitmap(bitmap1);           // Similarly we set the resources for header view
//                holder.Name.setText(name);

            }

        }

        // This method returns the number of items present in the list
        @Override
        public int getItemCount() {
            return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
        }


        // Witht the following method we check what type of view is being passed
        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }

    public  void showdialogforpassword() {

       dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.setContentView(R.layout.dialogforchangepassword);
        done = (TextView)dialog.findViewById(R.id.done);
        passalert = (TextView)dialog.findViewById(R.id.passalert);
        back2 = (ImageView)dialog.findViewById(R.id.back);
        oldp = (EditText)dialog.findViewById(R.id.oldp);
        conp = (EditText)dialog.findViewById(R.id.conp);
        newp = (EditText)dialog.findViewById(R.id.newp);
        pbdd = (ProgressWheel)dialog.findViewById(R.id.pbl);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(oldp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                imm.hideSoftInputFromWindow(conp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                imm.hideSoftInputFromWindow(newp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (!newp.getText().toString().equals(conp.getText().toString())) {
                    passalert.setVisibility(View.VISIBLE);
                } else {
                    passalert.setVisibility(View.GONE);
                    if(oldp.getText().toString().trim().equals(prefs2.getString("password",""))){
                        if(!newp.getText().toString().trim().equals(prefs2.getString("password",""))){
                            parsingforpasswordchange.parsing(MainActivity.this,oldp.getText().toString().trim(),
                                    newp.getText().toString().trim());
                        }
                        else {
                            Toast.makeText(MainActivity.this, "New password and Old Password are same !!!", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Old Password is incorrect !!!", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        oldp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    passalert.setVisibility(View.GONE);
                }
            }
        });
        newp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    passalert.setVisibility(View.GONE);
                }
            }
        });
        conp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    passalert.setVisibility(View.GONE);
                }
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(oldp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                imm.hideSoftInputFromWindow(conp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                imm.hideSoftInputFromWindow(newp.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
