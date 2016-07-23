package com.xuqi.zhihu_daily.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuqi.zhihu_daily.R;
import com.xuqi.zhihu_daily.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class NewsListAdapter extends ArrayAdapter<News> {
    private static final String TAG = "NewsListAdapter";
    private int resourceId;
    private Context context;
    private LayoutInflater inflater;

    public NewsListAdapter(Context context, int textViewResourceId){
        super(context,textViewResourceId);
        resourceId = textViewResourceId;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    public NewsListAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        News news = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = inflater.inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_title);
            viewHolder.newsImage = (ImageView) view.findViewById(R.id.news_image);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        //convertView.setBackgroundResource(R.drawable.listSelector);
        //加载新闻标题
        viewHolder.newsTitle.setText(news.getNews_title());
        //加载新闻图片
        Glide
                .with(context)
                .load(news.getNews_image())
                .into(viewHolder.newsImage);

        return view;
    }

    public void refreshNewsList(List<News> newsList){
        clear();
        addAll(newsList);
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
    }
}
