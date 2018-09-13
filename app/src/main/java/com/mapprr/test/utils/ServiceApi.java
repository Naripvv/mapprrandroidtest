package com.mapprr.test.utils;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by narip on 2/3/2017.
 */
public interface ServiceApi
{
    @GET("{list}")
    Call<ResponseBody> request(@Path("list") String postfix, @QueryMap HashMap<String, Object> params);
}
