package com.nba.news.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nba.nbanews.R;
import com.nba.news.view.base.BaseFragment;

/**
 * Created by allen on 16/11/30.
 */

public class RecommendFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.recommend_fragment,container,false);
    }
}
