package com.nba.news.presenter;

import com.nba.news.contract.NewsContract;
import com.nba.news.model.JokeModel;
import com.nba.news.model.bean.JokeBean;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by allen on 17/4/13.
 */

public class JokePresenter extends NewsContract.Presenter {



    public JokePresenter(NewsContract.View view) {
        mView = view;
        mModel = new JokeModel();
    }

    @Override
    public void getJokeData(String s, final HashMap<String,String> hashMap) {

        Disposable disposable = mModel.getJokeData(s,hashMap).subscribe(new Consumer<JokeBean>() {
            @Override
            public void accept(JokeBean jokeBean) throws Exception {
                mView.onSucceed(jokeBean,Integer.valueOf(hashMap.get("page")));

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showErrorTip(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mView.stopLoading();
            }
        });

        addSubscribe(disposable);

    }
}
