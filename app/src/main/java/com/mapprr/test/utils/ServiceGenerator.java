package com.mapprr.test.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by narip on 2/4/2017.
 */
public class ServiceGenerator
{
    public static final String API_BASE_URL = Settings.URLMAINDATA;

    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass)
    {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


}
