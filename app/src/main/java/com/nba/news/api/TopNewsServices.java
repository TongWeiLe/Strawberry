package com.nba.news.api;

import com.nba.news.model.bean.JokeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by allen on 16/12/16.
 */

public interface TopNewsServices {

    @GET
    Observable<JokeBean> getJokeData(@Url String s , @QueryMap HashMap<String,String> hashMap);

}
