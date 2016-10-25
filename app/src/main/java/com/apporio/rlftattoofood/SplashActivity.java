package com.apporio.rlftattoofood;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashActivity extends Activity {
    ImageView logo;
    public static boolean previouslyStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo= (ImageView)findViewById(R.id.imageView);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        previouslyStarted = prefs.getBoolean("pref_previously_started", false);

        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(findViewById(R.id.imageView));

        Handler handler1 = new Handler();

        handler1.postDelayed(new Runnable() {

            @Override
            public void run()

            {

                if (!previouslyStarted) {
                    Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                   startActivity(in);
                    finish();


                } else {
                    Intent in = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(in);
                    finish();

                }

            }
        }, 3000);
    }
}
