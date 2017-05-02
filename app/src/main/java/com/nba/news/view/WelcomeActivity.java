package com.nba.news.view;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import com.nba.news.MainActivity;
import com.nba.nbanews.R;
import com.nba.news.view.base.BaseActivity;

import butterknife.InjectView;


/**
 * Created by allen on 16/11/30.
 * 欢迎界面
 * 加载配置
 */

public class WelcomeActivity extends BaseActivity {

    @InjectView(R.id.tv_counter_down)
    TextView counter_down;
    @InjectView(R.id.tv_go_into_immediate)
    TextView goInto_Immediate;
    private CountDownTimer countDownTimer;

    @Override
    public void loadLayoutView() {
        setContentView(R.layout.welcome);
    }


    @Override
    protected void setListener() {
        goInto_Immediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent = new Intent();
                intent.setClass(getContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected Context getContext() {
        return this;
    }

    @Override
    protected void processBussiness() {
        // 3s后跳转
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },3000);
        counter_down.setText("3");
        countDownTimer = new CountDownTimer(4000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                counter_down.setText(String.valueOf((millisUntilFinished)/1000));
            }

            @Override
            public void onFinish() {
                counter_down.setText("0");
                Intent intent = new Intent();
                intent.setClass(getContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        countDownTimer.start();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }


}
