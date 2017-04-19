package com.nba.news.common;

/**
 * Created by allen on 16/12/23.
 */

public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
