package com.nba.news.common;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * des:基类：basePresenter
 * Created by allen on 16/12/23.
 */

public abstract class BasePresenter<V extends BaseView,M extends BaseModel> {

    public M mModel;
    public V mView;

    private CompositeDisposable compositeDisposable;

    public void addSubscribe(Disposable disposable){
        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }

        compositeDisposable.add(disposable);

    }

   public void unSubscribe(){
       if (mView != null){
           mView = null;
       }

       if (compositeDisposable!= null && compositeDisposable.isDisposed()){
           compositeDisposable.clear();
       }
   }

}
