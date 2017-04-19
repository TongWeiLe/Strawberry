package com.nba.news.view.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by allen on 16/11/30.
 */

public abstract class BaseActivity extends AppCompatActivity {
    
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        context = getContext();
        ButterKnife.inject(this);
        initData();
    }

    private void initData(){
        setListener();
        processBussiness();
    }



    protected abstract void setListener();

    protected abstract Context getContext();

    protected abstract void processBussiness();


    private void initView() {
        loadLayoutView();
    }

    protected abstract void loadLayoutView();
}
