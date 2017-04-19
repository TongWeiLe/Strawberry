package com.nba.news.view.newsfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.nba.nbanews.R;
import com.nba.news.contract.NewsContract;
import com.nba.news.model.bean.JokeBean;
import com.nba.news.presenter.JokePresenter;
import com.nba.news.view.adapter.JokeAdapter;
import com.nba.news.view.base.BaseFragment;
import com.victor.loading.newton.NewtonCradleLoading;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.InjectView;

/**
 * Created by allen on 16/12/2.
 */

public class HuPuFragment extends BaseFragment implements NewsContract.View {

    //请求页数 pageCount
    AtomicLong count = new AtomicLong(0);
    private JokeAdapter jokeAdapter;

    private Context mContext;
    private boolean isRefreshing;

    @InjectView(R.id.recyclerview)
    IRecyclerView recyclerView;

    @InjectView(R.id.loading)
    NewtonCradleLoading loading;

    private JokePresenter jokePresenter;

    List<JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>  contentlistBeen;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.hupu_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initPresenter();

        mContext = getActivity();

        jokeAdapter = new JokeAdapter(mContext);

        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getRemoteData("1");
            }
        });

        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getRemoteData(String.valueOf(count.incrementAndGet() / 2 + 1));
              Log.e("hupu", String.valueOf(count.incrementAndGet() / 2 + 1));
            }
        });

    }

    private void initPresenter() {
        jokePresenter = new JokePresenter(this);
        getRemoteData("1");
    }

    @Override
    public void onSucceed(JokeBean jokeBean,int page) {

        //易源返回标志,0为成功，其他为失败
        if (jokeBean.getShowapi_res_code() == 0){
            //所有页
            int allPages =  jokeBean.getShowapi_res_body().getPagebean().getAllPages();
            //所有数量
            int allNum = jokeBean.getShowapi_res_body().getPagebean().getAllNum();
            //当前页
            int currentPage = jokeBean.getShowapi_res_body().getPagebean().getCurrentPage();

            contentlistBeen = jokeBean.getShowapi_res_body().getPagebean().getContentlist();

            //first load
            if (page == 1){
                jokeAdapter.setContentlistBeanList(contentlistBeen);
                recyclerView.setIAdapter(jokeAdapter);
            } else
            //loadmore data
            if (page > 1){
                jokeAdapter.addContentlistBeanList(contentlistBeen);
                jokeAdapter.notifyDataSetChanged();
            }
            //refresh data
            if (isRefreshing){
                jokeAdapter.notifyDataSetChanged();
                recyclerView.setRefreshing(false);
                isRefreshing = false;
            }
        }

        Log.e("joke",jokeBean.getShowapi_res_body().toString());

    }

    public void getRemoteData(String page){
        loading.setLoadingColor(getResources().getColor(R.color.textview_color));
        loading.start();
        HashMap<String,String> map = getCommonMap();
        map.put("page",page);
        jokePresenter.getJokeData("255-1/",map);
    }

    public HashMap<String,String> getCommonMap(){
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("showapi_appid","35490");
        map.put("showapi_sign","e8bc37d7211c44ddb9273bba759d63a1");
        map.put("type","29");
        return map;
    }

    @Override
    public void showLoading(String title) {



    }

    @Override
    public void stopLoading() {
        loading.stop();
        loading.setVisibility(View.GONE);
    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        jokePresenter.unSubscribe();
    }
}
