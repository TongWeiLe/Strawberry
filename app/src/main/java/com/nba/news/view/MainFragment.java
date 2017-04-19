package com.nba.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nba.nbanews.R;
import com.nba.news.view.base.BaseFragment;
import com.nba.news.view.newsfragment.HuPuFragment;
import com.nba.news.view.newsfragment.WangYiFragment;
import com.nba.news.view.newsfragment.ZhiHuFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.InjectView;

/**
 * Created by allen on 16/11/30.
 */

public class MainFragment extends BaseFragment {

    @InjectView(R.id.viewpagertab)
    SmartTabLayout smartTabLayout;

    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    private View rootView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        if (null != rootView){
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();

            if (null != viewGroup){
                viewGroup.removeView(rootView);
            }
        }else {
            rootView = inflater.inflate(R.layout.main_fragment,container,false);
            Log.e("allen","onCreateView");
        }

        return rootView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentPagerItemAdapter fragmentPagerItemAdapter = new FragmentPagerItemAdapter(this.getChildFragmentManager(), FragmentPagerItems.with(getContext()).add(R.string.hu_pu,HuPuFragment.class).add(R.string.wang_yi,WangYiFragment.class).add(R.string.zhi_hu,ZhiHuFragment.class).create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        smartTabLayout.setViewPager(viewPager);

    }
































}
