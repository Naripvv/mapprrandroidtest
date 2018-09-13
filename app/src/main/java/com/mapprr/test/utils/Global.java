package com.mapprr.test.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by narip on 2/4/2017.
 */
public class Global extends Application
{
    private static Context context;
    public static Activity mCurrentActivity;
    public static HashMap<String, Object> mapMain = new HashMap<String, Object>();
    public static HashMap<String, Object> mapData = new HashMap<String, Object>();
     public static HashMap<String, Object> tempData = new HashMap<String, Object>();
    public static List<HashMap<String, Object>> reposList = new ArrayList<HashMap<String, Object>>();

    public static Context getAppContext()
    {
        return Global.context;
    }

    public void setCurrentActivity(Activity CurrentActivity)
    {
        mCurrentActivity = CurrentActivity;
    }

    public HashMap<String, Object> mapMain()
    {
        if(mapMain == null)
        {
            return mapMain = new HashMap<String, Object>();
        }
        else
        {
            mapMain.clear();
        }
        return mapMain;
    }


    public HashMap<String, Object> tempData()
    {
        if(tempData == null)
        {
            return tempData = new HashMap<String, Object>();
        }
        else
        {
            tempData.clear();
        }
        return tempData;
    }




}
