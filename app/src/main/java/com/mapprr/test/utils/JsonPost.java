package com.mapprr.test.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by narip on 2/4/2017.
 */
public class JsonPost implements ConstVariable
{
    public static  String TAG = "JsonPost";
    static String events;

    public synchronized static void getNetworkResponse(final Context context,final ProgressBar progressBar,final HashMap<String, Object> nameValuePairs, final int mode)
    {
        Utils.e("JsonPost54", "send events are >>>"+nameValuePairs.get(URL).toString()+nameValuePairs);

        try
        {
            boolean isPop = true;

            //if (mode==Login)
              //  isPop=false;

            try
            {
                if(isPop)
                Utils.initiatePopupWindow(context,"Please wait request is in progress...");
            }
            catch (Exception e)
            {
               // Utils.e(TAG+"129","Exception===============Exception=================Exception");
                e.printStackTrace();
            }

           //RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(nameValuePairs)).toString());

            ServiceApi  service = ServiceGenerator.createService(ServiceApi.class);

            Call<ResponseBody> response = service.request(nameValuePairs.get(URL).toString(),nameValuePairs);

            nameValuePairs.remove(URL);



           /* response.enqueue(new Callback<ResponseBody>()
            {
                @Override
                public void onResponse(Call<ResponseBody> call,retrofit2.Response<ResponseBody> rawResponse)
                {
                    try
                    {
                        //get your response....
                        if(Utils.progressDialog != null)
                        {
                            Utils.progressDialog.dismiss();
                            Utils.progressDialog = null;
                        }
                        String result = rawResponse.body().string();
                        Log.e(TAG + "99", "RetroFit2.0 :RetroGetResult: " + result);

                        switch (mode)
                        {
                            case List:
                                // Log.e(TAG+"106", "response" + result.toString());
                                result =  JsonHelper.getResults(result.toString(),context,mode);
                                // Log.e(TAG+"193", "response bool===="+result.toString());
                                if(result.equalsIgnoreCase(SUCCESS))
                                {
                                     //Utils.e("" + "73", "login  mapdata===="+  Utils.global.dropAddresslist);

                                    // BookReservation.Instance.updateDropAddressList(Utils.global.dropAddresslist);
                                      //  com.mapprr.test.activity.DropAddressList.loadRequestsList(context,Utils.global.dropAddresslist,"mode");

                                }
                                else
                                {
                                    //Utils.toastTxt( Utils.global.mapMain.get(ConstVariable.MESSAGE).toString(),context);
                                }
                                break;

                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call,Throwable throwable)
                {
                    if(Utils.progressDialog != null)
                    {
                        Utils.progressDialog.dismiss();
                        Utils.progressDialog = null;
                    }
                    *//*if(progressBar != null)
                    {
                        progressBar.setVisibility(View.GONE);
                    }*//*
                    // other stuff...
                    Utils.e(TAG+"98","Exception==================Exception===================Exception"+throwable.getMessage());
                }
            });*/



            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            if(Utils.progressDialog != null)
                            {
                                Utils.progressDialog.dismiss();
                                Utils.progressDialog = null;
                            }
                            Log.e(TAG + "99", "RetroFit2.0 :RetroGetResult: " + response);

                            switch (mode)
                            {
                                case List:
                                    // Log.e(TAG+"106", "response" + result.toString());
                                    response =  JsonHelper.getResults(response.toString(),context,mode);
                                    // Log.e(TAG+"193", "response bool===="+result.toString());
                                    if(response.equalsIgnoreCase(SUCCESS))
                                    {
                                        Utils.e("" + "739999999999999======", "87867687687687a===="+  Utils.global.reposList);

                                        // BookReservation.Instance.updateDropAddressList(Utils.global.dropAddresslist);

                                         List<HashMap<String,Object>> mlist=new ArrayList<>();

                                         for (int i=0;i<10;i++)
                                         {
                                             mlist.add(Utils.global.reposList.get(i));
                                         }

                                        HashMap<String,Object> a;
                                        int x,y;

                                        for (int i = 0; i < mlist.size(); ++i)
                                        {
                                            for (int j = i + 1; j < mlist.size(); ++j)
                                            {
                                                 x=Integer.parseInt(mlist.get(i).get("watchers_count").toString());
                                                 y=Integer.parseInt(mlist.get(j).get("watchers_count").toString());

                                                if (x < y)
                                                {
                                                    a = mlist.get(i);
                                                    mlist.set(i, mlist.get(j));
                                                    mlist.set(j, a);
                                                }
                                            }
                                        }
                                          com.mapprr.test.activity.HomeActivity.loadRequestsList(context,mlist,"mode");
                                    }
                                    else
                                    {
                                        //Utils.toastTxt( Utils.global.mapMain.get(ConstVariable.MESSAGE).toString(),context);
                                    }
                                    break;
                            }

                        }
                    }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    if(Utils.progressDialog != null)
                    {
                        Utils.progressDialog.dismiss();
                        Utils.progressDialog = null;
                    }
                    Utils.e(TAG+"98","Exception==================Exception===================Exception"+error.getMessage());

                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);







        }catch (Exception e)
        {
            e.printStackTrace();
            //Utils.e(TAG+"104","Exception==================Exception===================Exception");
        }
        finally
        {

        }
    }

    }
