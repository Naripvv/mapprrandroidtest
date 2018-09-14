package com.mapprr.test.utils;

import android.content.Context;
import android.text.format.DateUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by narip on 2/4/2017.
 */
public class JsonHelper implements ConstVariable
{
    public static final String Response_Code = "status";
    public static final String Response_Text = "ResponseText";
    public static final String DATA = "items";
    public static final String ERROR_MSG = "errmsg";
    static String Tag = "jsonHelper";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static int flags = DateUtils.FORMAT_ABBREV_RELATIVE;

    public static String getResults(String result, Context context, final int mode)
    {
        String response = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        if (result == null)
            return null;
        if (context == null)
            context = Global.getAppContext();
        try
        {
            try
            {
                jsonObject = new JSONObject(result);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

            switch (1)
            {
                case 1:
                    response = ConstVariable.SUCCESS;
                   jsonArray = jsonObject.optJSONArray(DATA);
                    Utils.global.mapMain =  Utils.GetJsonDataIntoMap(context, jsonArray,"");

                    if (mode == ConstVariable.List)
                    {
                        Utils.global.reposList.clear();
                        Utils.global.reposList.addAll((ArrayList<HashMap<String,Object>>) (Utils.GetJsonDataIntoList(context, jsonArray, "")));

                        Utils.e(Tag + "73", "list  mapdata===="+  Utils.global.reposList);
                    }
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
            //Utils.e(Tag + "79", "Exception=================Exception====================Exception");
            e.printStackTrace();
        }
        finally
        {
            jsonArray = null;
            jsonObject = null;
        }
        return response;
    }
}
