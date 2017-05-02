package com.nba.news.model.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by allen on 17/4/25.
 */

public class GoodBean extends DataSupport{
    private int id;
    private long realId;
    private boolean like;
    private boolean dislike;

    private int love;
    private int hate;

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getHate() {
        return hate;
    }

    public void setHate(int hate) {
        this.hate = hate;
    }

    public long getRealId() {
        return realId;
    }

    public void setRealId(long realId) {
        this.realId = realId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }
}
