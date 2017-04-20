package com.nba.news.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nba.nbanews.R;
import com.nba.news.model.bean.JokeBean;
import com.nba.news.view.widget.RoundImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by allen on 17/4/14.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private List<JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlistBeanList;
    public JokeAdapter(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
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

        JokeBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = contentlistBeanList.get(position);
        Picasso.with(mContext).load(contentlistBean.getProfile_image()).into(holder.roundImageView);
        holder.id.setText(contentlistBean.getName());
        holder.date.setText(contentlistBean.getCreate_time());
        holder.love.setText(contentlistBean.getLove());
        holder.hate.setText(contentlistBean.getHate());
        holder.content.setText( contentlistBean.getText().replace("\n",""));
        holder.content.post(new Runnable() {
            @Override
            public void run() {
                Log.e("hupu",holder.content.getLineCount() +"");
            }
        });



    }

    @Override
    public int getItemCount() {
        return contentlistBeanList == null ? 0 : contentlistBeanList.size();
    }

    public static class JokeHolder extends RecyclerView.ViewHolder{

        RoundImageView roundImageView;
        TextView id;
        TextView date;
        TextView content;
        TextView praise;
        TextView criticism;
        TextView forwarding;
        TextView command;
        TextView love;
        TextView hate;

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

        }
    }
}
