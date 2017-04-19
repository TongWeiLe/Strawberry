package com.nba.news;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.nba.nbanews.R;
import com.nba.news.view.base.BaseActivity;
import com.nba.news.view.DiscoverFragment;
import com.nba.news.view.MainFragment;
import com.nba.news.view.RecommendFragment;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tab_host)
    FragmentTabHost tabHost;
    @InjectView(R.id.fragment_content)
    FrameLayout frameLayout;

    private String title[] = {"首页","推荐","发现"};
    private Class fragmentArray[] = {MainFragment.class,RecommendFragment.class,DiscoverFragment.class};
    private int backTime = 0;

    @Override
    protected void loadLayoutView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void setListener() {

    }



    @Override
    protected void processBussiness() {

        tabHost.setup(getContext(),this.getSupportFragmentManager(),R.id.fragment_content);
        tabHost.getTabWidget().setDividerDrawable(null);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        tabHost.addTab(tabHost.newTabSpec( title[0]).setIndicator(inflater.inflate(R.layout.main,null)),fragmentArray[0],null);
        tabHost.addTab(tabHost.newTabSpec(title[1]).setIndicator(inflater.inflate(R.layout.recommend,null)),fragmentArray[1],null);
        tabHost.addTab(tabHost.newTabSpec(title[2]).setIndicator(inflater.inflate(R.layout.discover,null)),fragmentArray[2],null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK ){
            if (backTime == 0){
                Toast.makeText(getContext(),"再点击一次退出应用",Toast.LENGTH_SHORT).show();
            }else {
                MainActivity.this.finish();
            }
            backTime++;
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }


}
