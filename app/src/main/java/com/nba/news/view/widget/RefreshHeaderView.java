package com.nba.news.view.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aspsine.irecyclerview.RefreshTrigger;
import com.nba.nbanews.R;


/**
 * Created by allen on 16/12/2.
 */

public class RefreshHeaderView extends FrameLayout implements RefreshTrigger{

    private int mHeight;

    ImageView curry;

    ImageView lebron;

    ImageView vs;

    AnimatorSet btnSexAnimatorSet ;

    public RefreshHeaderView(Context context) {
        this(context,null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, null,0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_irecyclerview_lebron_vs_curry_header_view,this);

        vs = (ImageView) findViewById(R.id.imageView);
        lebron = (ImageView) findViewById(R.id.ivSuperMan);
        curry = (ImageView) findViewById(R.id.ivBatMan);

        PropertyValuesHolder translationX1 = PropertyValuesHolder.ofFloat("translationX",0.0f,100.0f,0.0f);
        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotationY",0.0f,380.0f,0.0f);
        PropertyValuesHolder translationX2 = PropertyValuesHolder.ofFloat("translationX",0.0f,-100.0f,0.0f);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(curry,  translationX1);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setRepeatMode(ValueAnimator.RESTART);

        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(lebron,translationX2);
        objectAnimator2.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator2.setRepeatMode(ValueAnimator.RESTART);

        ObjectAnimator objectAnimator3 = ObjectAnimator.ofPropertyValuesHolder(vs,  rotate);
        objectAnimator3.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator3.setRepeatMode(ValueAnimator.RESTART);

        Animation animation =new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        Animation animation1 =new RotateAnimation(0f,-360f, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setFillAfter(true);
        animation.setDuration(2000);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setRepeatMode(ValueAnimator.INFINITE);
        animation1.setFillAfter(true);
        animation1.setDuration(2000);
        animation1.setRepeatCount(ValueAnimator.INFINITE);
        animation1.setRepeatMode(ValueAnimator.INFINITE);
//        curry.setAnimation(animation);
//        lebron.setAnimation(animation1);

        btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(objectAnimator1, objectAnimator2,objectAnimator3);
        btnSexAnimatorSet.setDuration(2000);
        btnSexAnimatorSet.start();
    }


    @Override
    public void onStart(boolean b, int i, int i1) {
        mHeight = i;
    }

    @Override
    public void onMove(boolean b, boolean b1, int i) {
        if (!b) {
            vs.setRotationY(i / (float) mHeight * 360);
        } else {
            vs.setRotationY(i / (float) mHeight * 360);
        }
    }

    @Override
    public void onRefresh() {
        if(!btnSexAnimatorSet.isStarted()){
            btnSexAnimatorSet.start();
        }
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        vs.setRotation(0);
    }
}
