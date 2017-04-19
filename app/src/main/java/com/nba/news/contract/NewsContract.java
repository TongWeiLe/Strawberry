package com.nba.news.contract;

import com.nba.news.common.BaseModel;
import com.nba.news.common.BasePresenter;
import com.nba.news.common.BaseView;
import com.nba.news.model.bean.JokeBean;

import java.util.HashMap;

import io.reactivex.Observable;


/**
 * Created by allen on 16/12/15.
 *
 * 新闻类的契约类
 * 存放所有的 view presenter 便于管理
 *
 */


public interface NewsContract {

    /**
     * 获取获取新闻List
     */
    interface Model extends BaseModel{
        Observable<JokeBean> getJokeData(String url, HashMap<String,String> hashMap);
    }

    interface View extends BaseView {
        void onSucceed(JokeBean jokeBean,int page);
    }

    abstract class Presenter extends BasePresenter<View,Model>{

        public abstract void getJokeData(String s,HashMap<String,String> hashMap);

    }

    /**
     * 发起获取新闻的请求
     */





}
