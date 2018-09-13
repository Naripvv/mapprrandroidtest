package com.mapprr.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.mapprr.test.R;
import com.mapprr.test.utils.Utils;

/**
 * Created by narayana on 10/25/2017.
 */

public class Splash extends AppCompatActivity
{
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                    Utils.startActivity(Splash.this, HomeActivity.class);
                    finish();

            }
        }, 3000);
    }
}

