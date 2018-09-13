package com.mapprr.test.utils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mapprr.test.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by narip on 2/4/2017.
 */
public class Utils
{
    public static String Tag = "Utils";
    public static Context context = null;
    public static Global global;
    public static PopupWindow pwindo;
    static LayoutInflater inflater;
    static ProgressBar progress;
    static ProgressDialog progressDialog;
    static Boolean showPopoup = true;
    // Milliseconds
    static LocationManager locationManager;

    // IMage Loader end
    private static AlertDialog alert;
    private static SharedPreferences.Editor editor;
    private static Pattern pattern;
    public static SharedPreferences prefs;
    private static Matcher matcher;

    public Utils(Context context)
    {
        if (context == null)
            context = Global.getAppContext();

        if (context != null)
        {
            if (global == null)
                global = new Global();

            Utils.context = context;

            if (context instanceof Activity)
                global.setCurrentActivity((Activity) context);
        }
    }



    public static void setImagePiccaso(final Context context, String url, final ImageView imagevv)
    {
        //Utils.e(Tag+"118", "PHOTO===="+url);

       /* Picasso.with(context).load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder).into(imagevv);
*/
        /*Picasso.with(context).load(url).resize(600, 600)
                .centerInside()
                .onlyScaleDown()
                .into(new Target()
                .into(new Target()
                {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap,Picasso.LoadedFrom from)
                    {
                        imagevv.setImageBitmap(bitmap);
                        Log.e("TAG"+"130", "SUCCESS");
                    }

                    @Override
                    public void onBitmapFailed(final Drawable errorDrawable)
                    {
                        Log.e("TAG4"+"137", "FAILED");
                        imagevv.setImageResource(R.drawable.placeholder);
                    }

                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable)
                    {
                        Log.e("TAG4"+"148", "Prepare Load");
                    }
                });*/
    }


    public static final void toastTxt(String str, Context context)
    {
        Toast toast = Toast.makeText(context,str,Toast.LENGTH_SHORT);
        toast.show();
    }

    public static boolean isNetworkAvailable(Context context)
    {
        if (context == null && Utils.context != null)
        {
            context = Utils.context;
        }
        NetworkInfo activeNetworkInfo = null;
        if (context != null)
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null)
            {
                Settings.NETWORK_STATUS = "";
                Settings.NETWORK_TYPE = "";
            }
            else
            {
                if (activeNetworkInfo.getType() == 1)
                {
                    Settings.NETWORK_TYPE = "WiFi";
                }
                if (activeNetworkInfo.getType() == 0)
                    Settings.NETWORK_TYPE = "3G";
                if (activeNetworkInfo.getType() == 0
                        && activeNetworkInfo.getType() == 1)
                    Settings.NETWORK_TYPE = "WiFi";
                if (activeNetworkInfo.getType() == 6) {
                    Settings.NETWORK_TYPE = "WiMax";
                }
            }
        }
        return activeNetworkInfo != null;
    }


    public static final void e(String tag, String msg)
    {
        Log.e(tag, msg);
    }



    public static void initiatePopupWindow(Context mcontext, String text)
    {
        try
        {
            if (progressDialog==null)
            {
                progressDialog = new ProgressDialog(mcontext);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Please wait request is in progress...");
                progressDialog.show();
            }
        }
        catch (Exception e)
        {
            //Utils.e("Utils 3548","Exception=============Exception==================Exception");
            e.printStackTrace();
        }
        finally
        {
            //Utils.e("2initiatePopupWindow","2513test------" + Global.mCurrentActivity);
        }
    }



    public static void startActivity(Context context, Class<?> activity)
    {
        if (context == null)
            context = Utils.context;

        Intent intent = new Intent(context,activity);
        try
        {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ((Activity) context).startActivityForResult(intent,0);
        }
        catch (Exception ex)
        {
            //Utils.e(Tag + "1896", "Exception===========Exception===========Exception");
            ex.printStackTrace();
        }
        intent = null;
    }

    public static HashMap<String, Object> GetJsonDataIntoMap(Context context, JSONArray array, String mode)
    {

        // SharedPreferences prefs =
//        Utils.e("Utils 709", "GetEventOfflineList ok");
        HashMap<String, Object>   item = new HashMap<String, Object>();
       // List<HashMap<String, Object>> collection = new ArrayList<HashMap<String, Object>>();
//        if (context instanceof MainActivityAdmin) {
        try
        {
//                Utils.e("Utils 712", "GetEventOfflineList ok");

            HashMap<String, Object> itemSub = null;
//                Utils.e("Utils 715", "GetEventOfflineList ok"+array);
            JSONObject ary = null;
            JSONObject arySub = null;
//                Utils.e("Utils760", ""+array);
            for (int i = 0; i < array.length(); i++)
            {
//                    Utils.e("Utils 717", "GetEventOfflineList ok " + i);
//                String obj = (String) array.get(i);
                ary = array.optJSONObject(i);

//                    Utils.e("Utils 719", "JSONObject ok " + ary);
                if (ary != null) {
//                        Utils.e("Utils 720", "JSONObject ok " + ary);
                    Iterator<String> it = ary.keys();
//                        Utils.e("Utils 723", "Iterator ok " + it);
                    while (it.hasNext())
                    {
                        String key = it.next();

                        //for sub array

                         if (key.equalsIgnoreCase(ConstVariable.SUBARRAY))
                        {
//                            Utils.e("Utils 733", "JSONObject ok "+ConstVariable.EVENTIMAGE);
                            JSONArray arraySub = ary.optJSONArray(ConstVariable.SUBARRAY);
//                            Utils.e("Utils 733", "arraySub ok "+arraySub);
                            if (arraySub != null)
                            {
                                List<HashMap<String, Object>> tempArrayMapImage = new ArrayList<HashMap<String, Object>>();
                                for (int j = 0; j < arraySub.length(); j++)
                                {
                                    arySub = arraySub.optJSONObject(j);
                                    if(arySub != null){
//                                Utils.e("Utils 736", "JSONObject ok "+arySub);
                                        Iterator<String> itSub = arySub.keys();
//                                Utils.e("Utils 738", "Iterator ok "+itSub);
                                        itemSub = new HashMap<String, Object>();
//                                        Utils.e("Utils 786", "arySubPer ok " + arySub);
                                        while (itSub.hasNext()) {
                                            String keySub = itSub.next();
//                                            Utils.e("Utils 786", keySub + "GetEventOfflineList ok " + arySub.get(keySub));
                                            if (arySub.get(keySub) != null) {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                                itemSub.put(keySub, arySub.get(keySub));
                                            } else {
//                                    Utils.e("Utils780","key : "+key);
                                                itemSub.put(keySub, "");
                                            }
                                        }
                                        tempArrayMapImage.add(itemSub);
                                    }
                                    else
                                    {
                                        tempArrayMapImage = null;
                                    }
                                }
                                item.put(ConstVariable.SUBARRAY, tempArrayMapImage);
//                                    Utils.e("Utils 795", "arySubPer ok " + item);
                            } else {
                                item.put(ConstVariable.SUBARRAY, null);
                            }
                        }


                        else if (key.equalsIgnoreCase(ConstVariable.SUBARRAY1))
                        {
//                            Utils.e("Utils 733", "JSONObject ok "+ConstVariable.EVENTIMAGE);
                            JSONArray arraySub = ary.optJSONArray(ConstVariable.SUBARRAY1);
//                            Utils.e("Utils 733", "arraySub ok "+arraySub);
                            if (arraySub != null) {
                                List<HashMap<String, Object>> tempArrayMapImage = new ArrayList<HashMap<String, Object>>();
                                for (int j = 0; j < arraySub.length(); j++) {
                                    arySub = arraySub.optJSONObject(j);
                                    if(arySub != null){
//                                Utils.e("Utils 736", "JSONObject ok "+arySub);
                                        Iterator<String> itSub = arySub.keys();
//                                Utils.e("Utils 738", "Iterator ok "+itSub);
                                        itemSub = new HashMap<String, Object>();
//                                        Utils.e("Utils 786", "arySubPer ok " + arySub);
                                        while (itSub.hasNext()) {
                                            String keySub = itSub.next();
//                                            Utils.e("Utils 786", keySub + "GetEventOfflineList ok " + arySub.get(keySub));
                                            if (arySub.get(keySub) != null) {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                                itemSub.put(keySub, arySub.get(keySub));
                                            } else {
//                                    Utils.e("Utils780","key : "+key);
                                                itemSub.put(keySub, "");
                                            }
                                        }
                                        tempArrayMapImage.add(itemSub);
                                    }else{
                                        tempArrayMapImage = null;
                                    }
                                }
                                item.put(ConstVariable.SUBARRAY1, tempArrayMapImage);
//                                    Utils.e("Utils 795", "arySubPer ok " + item);
                            } else {
                                item.put(ConstVariable.SUBARRAY1, null);
                            }
                        } else {
                            if (ary.get(key) != null) {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                item.put(key, ary.get(key));
                            } else {
//                                    Utils.e("Utils780","key : "+key);
                                item.put(key, "");
                            }
                        }

                    }
//                    collection.add(item);

                }
            }
        } catch (JSONException e)
        {
            //Utils.e("Error 4094", "while parsing===================JSONException==============JSONException");
            e.printStackTrace();
        }
//        }
        //Utils.e("Utils 751", "collection ok " + item);
        return item;
    }

    public static List GetJsonDataIntoList(Context context, JSONArray array, String mode)
    {
        List<HashMap<String, Object>> collection = new ArrayList<HashMap<String, Object>>();
        try {
           HashMap<String, Object> item = null;
            HashMap<String, Object> itemSub = null;
            JSONObject ary = null;
            JSONObject arySub = null;
            for (int i = 0; i < array.length(); i++)
            {
                   ary = array.optJSONObject(i);
//                    Utils.e("Utils 719", "JSONObject ok " + ary);
                if (ary != null) {
//                        Utils.e("Utils 720", "JSONObject ok " + ary);
                    Iterator<String> it = ary.keys();
                   // Utils.e("Utils 3695", "Iterator ok " + it);
                    item = new HashMap<String, Object>();
                    while (it.hasNext()) {
                        String key = it.next();
                        if (key.equalsIgnoreCase(ConstVariable.SUBARRAY))
                        {
//                            Utils.e("Utils 733", "JSONObject ok "+ConstVariable.EVENTIMAGE);
                            JSONArray arraySub = ary.optJSONArray(ConstVariable.SUBARRAY);
//                            Utils.e("Utils 733", "arraySub ok "+arraySub);
                            if (arraySub != null) {
                                List<HashMap<String, Object>> tempArrayMapImage = new ArrayList<HashMap<String, Object>>();
                                for (int j = 0; j < arraySub.length(); j++) {
                                    arySub = arraySub.optJSONObject(j);
                                    if(arySub != null){
//                                Utils.e("Utils 736", "JSONObject ok "+arySub);
                                        Iterator<String> itSub = arySub.keys();
//                                Utils.e("Utils 738", "Iterator ok "+itSub);
                                        itemSub = new HashMap<String, Object>();
//                                        Utils.e("Utils 786", "arySubPer ok " + arySub);
                                        while (itSub.hasNext()) {
                                            String keySub = itSub.next();
//                                            Utils.e("Utils 786", keySub + "GetEventOfflineList ok " + arySub.get(keySub));
                                            if (arySub.get(keySub) != null) {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                                itemSub.put(keySub, arySub.get(keySub));
                                            } else {
//                                    Utils.e("Utils780","key : "+key);
                                                itemSub.put(keySub, "");
                                            }
                                        }
                                        tempArrayMapImage.add(itemSub);
                                    }else{
                                        tempArrayMapImage = null;
                                    }
                                }
                                item.put(ConstVariable.SUBARRAY, tempArrayMapImage);
//                                    Utils.e("Utils 795", "arySubPer ok " + item);
                            } else {
                                item.put(ConstVariable.SUBARRAY, null);
                            }
                        } else if (key.equalsIgnoreCase(ConstVariable.SUBARRAY1))
                        {
//                            Utils.e("Utils 733", "JSONObject ok "+ConstVariable.EVENTIMAGE);
                            JSONArray arraySub = ary.optJSONArray(ConstVariable.SUBARRAY1);
//                            Utils.e("Utils 733", "arraySub ok "+arraySub);
                            if (arraySub != null) {
                                List<HashMap<String, Object>> tempArrayMapImage = new ArrayList<HashMap<String, Object>>();
                                for (int j = 0; j < arraySub.length(); j++) {
                                    arySub = arraySub.optJSONObject(j);
                                    if(arySub != null){

//                                Utils.e("Utils 736", "JSONObject ok "+arySub);
                                        Iterator<String> itSub = arySub.keys();
//                                Utils.e("Utils 738", "Iterator ok "+itSub);
                                        itemSub = new HashMap<String, Object>();
//                                        Utils.e("Utils 786", "arySubPer ok " + arySub);
                                        while (itSub.hasNext()) {
                                            String keySub = itSub.next();
//                                            Utils.e("Utils 786", keySub + "GetEventOfflineList ok " + arySub.get(keySub));
                                            if (arySub.get(keySub) != null) {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                                itemSub.put(keySub, arySub.get(keySub));
                                            } else {
//                                    Utils.e("Utils780","key : "+key);
                                                itemSub.put(keySub, "");
                                            }
                                        }
                                        tempArrayMapImage.add(itemSub);
                                    }else{
                                        tempArrayMapImage = null;
                                    }
                                }
                                item.put(ConstVariable.SUBARRAY1, tempArrayMapImage);
//                                    Utils.e("Utils 795", "arySubPer ok " + item);
                            }
                            else
                            {
                                item.put(ConstVariable.SUBARRAY1, null);
                            }
                        }
                        else
                        {
                            if (ary.get(key) != null)
                            {
//                                    Utils.e("Utils780",key+" : "+ary.get(key));
                                item.put(key, ary.get(key));
                            }
                            else
                            {
//                                    Utils.e("Utils780","key : "+key);
                                item.put(key, "");
                            }
                        }
                    }
                    collection.add(item);
//                        Utils.e("Utils 751", "collection ok " + collection);
                }
            }
        } catch (JSONException e)
        {
           // Utils.e("Error 3860", "while parsing=====JSONException================JSONException");
            e.printStackTrace();
        }
//        }
        return collection;
    }
}
