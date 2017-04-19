package com.nba.news.model;

import com.nba.news.api.ApiManage;
import com.nba.news.contract.NewsContract;
import com.nba.news.model.bean.JokeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by allen on 17/4/13.
 */

public class JokeModel implements NewsContract.Model {
    @Override
    public Observable<JokeBean> getJokeData(String url,HashMap<String, String> hashMap) {
        return ApiManage.getInstance().getTopNewsService().getJokeData(url,hashMap).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
