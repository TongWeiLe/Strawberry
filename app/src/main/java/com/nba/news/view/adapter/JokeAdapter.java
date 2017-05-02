package com.nba.news.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nba.nbanews.R;
import com.nba.news.model.bean.GoodBean;
import com.nba.news.model.bean.JokeBean;
import com.nba.news.view.widget.RoundImageView;
import com.squareup.picasso.Picasso;
import com.wx.goodview.GoodView;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by allen on 17/4/14.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private GoodView goodView;
    private List<JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList;
    public JokeAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);

        goodView = new GoodView(mContext);

    }

    public void setContentlistBeanList(List<JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList){
        this.contentlistBeanList = contentlistBeanList;
    }

    public void addContentlistBeanList(List<JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList1){
        contentlistBeanList.addAll(contentlistBeanList1);
    }

    @Override
    public JokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeHolder(layoutInflater.inflate(R.layout.hupu_joke_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final JokeHolder holder, int position) {
        GoodBean goodBean = new GoodBean();
        final JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = contentlistBeanList.get(position);
        Picasso.with(mContext).load(contentlistBean.getProfile_image()).into(holder.roundImageView);
        holder.id.setText(contentlistBean.getName());
        holder.date.setText(contentlistBean.getCreate_time());

        holder.love.setTextColor(mContext.getResources().getColor(R.color.gray_text_color));
        holder.ivLike.setImageResource(R.drawable.shenhe_ding_pic);
        holder.hate.setTextColor(mContext.getResources().getColor(R.color.gray_text_color));
        holder.ivDislike.setImageResource(R.drawable.shenhe_cai_pic);


        holder.love.setText(contentlistBean.getLove());
        holder.hate.setText(contentlistBean.getHate());
        holder.content.setText( contentlistBean.getText().replace("\n",""));
        //异步方式获取 textview 行数
        setReadAllContentIsVisiable(holder);
        //显示全文或者收起
        setShowAllContentOrOther(holder);

        goodBean.setRealId(Long.valueOf(contentlistBean.getId()));
        goodView.setTextInfo("+1",R.color.bm_main_red_bg,15);
        //点赞 or 不支持
        pressLike(goodBean,contentlistBean,holder);
        pressDisLike(goodBean,contentlistBean,holder);


        //Litepal 数据库查找
        List<GoodBean> goodList =  DataSupport.where("realid = ?",contentlistBean.getId()).find(GoodBean.class);

        if (goodList != null && goodList.size() > 0){
            GoodBean bean = goodList.get(0);
            if (bean.isLike()){
                setLikeState(holder,contentlistBean.getId());
                setLikeDisLikeDisable(holder);
            }
            if (bean.isDislike()){
                setDisLikeState(holder,contentlistBean.getId());
                setLikeDisLikeDisable(holder);
            }
        }

    }

    private void setLoveRedState(JokeHolder holder){
        holder.love.setTextColor(mContext.getResources().getColor(R.color.bm_main_red_bg));
        holder.ivLike.setImageResource(R.drawable.shenhe_ding_pic_an);
    }

    private void setHateRedState(JokeHolder holder){
        holder.hate.setTextColor(mContext.getResources().getColor(R.color.bm_main_red_bg));
        holder.ivDislike.setImageResource(R.drawable.shenhe_cai_pic_an);
    }

    private void setLikeState(JokeHolder holder,String id){
        if (holder.ivLike.getTag() != null){
            if (id.equals(holder.ivLike.getTag().toString()) ){
                setLoveRedState(holder);
            }
        }
        setLoveRedState(holder);
    }

    private void setDisLikeState(JokeHolder holder,String id){
        if (holder.ivDislike.getTag() != null){
            if (id.equals(holder.ivDislike.getTag().toString())){
                setHateRedState(holder);
            }
        }
        setHateRedState(holder);
    }

    private void pressLike(final GoodBean goodBean,final JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean, final JokeHolder holder) {
        holder.llLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivLike.setTag(contentlistBean.getId());
                goodBean.setLike(true);
                holder.love.setText(String.valueOf(Integer.valueOf(contentlistBean.getLove()) + 1));
                //数据库插入
                goodBean.setLove(Integer.valueOf(contentlistBean.getLove()) + 1);
                setLikeState(holder,contentlistBean.getId());
                goodView.show(v);
                setLikeDisLikeDisable(holder);
                if(goodBean.save()){
                    Log.e("","");
                }

            }
        });
    }



    private void pressDisLike(final GoodBean goodBean ,final JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean, final JokeHolder holder) {

        holder.llDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodBean.setDislike(true);
                holder.hate.setText(String.valueOf(Integer.valueOf(contentlistBean.getHate())+ 1));
                //数据库插入
                goodBean.setHate(Integer.valueOf(contentlistBean.getHate())+ 1);
                setDisLikeState(holder,contentlistBean.getId());
                goodView.show(v);
                setLikeDisLikeDisable(holder);
                goodBean.save();
            }
        });
    }

    private void setShowAllContentOrOther(final JokeHolder holder) {
        holder.readAllContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.readAllContent.getText().equals("全文")){
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.readAllContent.setText("收起");
                }else if (holder.readAllContent.getText().equals("收起")){
                    holder.content.setMaxLines(7);
                    holder.readAllContent.setText("全文");
                }
            }
        });
    }

    private void setReadAllContentIsVisiable(final JokeHolder holder) {
        holder.content.post(new Runnable() {
            @Override
            public void run() {
                if (holder.content.getLineCount() > 7){
                    holder.readAllContent.setVisibility(View.VISIBLE);
                }else {
                    holder.readAllContent.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentlistBeanList == null ? 0 : contentlistBeanList.size();
    }

    private void setLikeDisLikeDisable(JokeHolder jokeHolder){
        jokeHolder.llLike.setClickable(false);
        jokeHolder.llDislike.setClickable(false);
    }


    public static class JokeHolder extends RecyclerView.ViewHolder{

        RoundImageView roundImageView;
        TextView id;
        TextView date;
        TextView content;
        TextView praise;
        TextView criticism;
        TextView forwarding;
        TextView readAllContent;
        TextView command;
        TextView love;
        TextView hate;
        LinearLayout llLike;
        LinearLayout llDislike;
        LinearLayout llForward;
        LinearLayout llCommand;
        ImageView ivLike;
        ImageView ivDislike;

        public JokeHolder(View itemView) {
            super(itemView);
            roundImageView = (RoundImageView) itemView.findViewById(R.id.rv_profile_image);
            id = (TextView) itemView.findViewById(R.id.tv_id);
            date = (TextView) itemView.findViewById(R.id.tv_date);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            praise = (TextView) itemView.findViewById(R.id.tv_praise);
            criticism = (TextView) itemView.findViewById(R.id.tv_criticism);
            forwarding = (TextView) itemView.findViewById(R.id.tv_forwarding);
            command = (TextView) itemView.findViewById(R.id.tv_command);
            love = (TextView) itemView.findViewById(R.id.tv_praise);
            hate = (TextView) itemView.findViewById(R.id.tv_criticism);
            readAllContent = (TextView) itemView.findViewById(R.id.tv_read_all);
            llLike = (LinearLayout) itemView.findViewById(R.id.ll_like);
            llDislike = (LinearLayout) itemView.findViewById(R.id.ll_dislike);
            llForward = (LinearLayout) itemView.findViewById(R.id.ll_forward);
            llCommand = (LinearLayout) itemView.findViewById(R.id.ll_command);
            ivLike = (ImageView) itemView.findViewById(R.id.iv_like);
            ivDislike = (ImageView) itemView.findViewById(R.id.iv_dislike);

        }
    }
}
