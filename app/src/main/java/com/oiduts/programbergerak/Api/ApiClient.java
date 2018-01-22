package com.oiduts.programbergerak.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oiDutS on 20/01/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "http://192.168.43.242/24995_rpl2_33_bergerak/v1/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
