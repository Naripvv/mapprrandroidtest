package com.mapprr.test.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mapprr.test.R;
import com.mapprr.test.utils.ConstVariable;
import com.mapprr.test.utils.JsonPost;
import com.mapprr.test.utils.Settings;
import com.mapprr.test.utils.Utils;

public class HomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // server request for repositories list.
        getRespositoriesListRequest();

    }

    public  void getRespositoriesListRequest()
    {
        new Utils(HomeActivity.this);
        Utils.global.mapMain();
        Utils.global.mapMain.put(ConstVariable.URL, Settings.URL__LIST);

        if (Utils.isNetworkAvailable(HomeActivity.this))
        {
            JsonPost.getNetworkResponse(HomeActivity.this,null,Utils.global.mapMain,ConstVariable.List);
        }
        else
        {

        }


    }

}
