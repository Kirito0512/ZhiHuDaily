package com.xuqi.zhihu_daily.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view = null;
        ViewHolder viewHolder;
//        if (getItemViewType(position) == 0)//
//        {
            if (convertView == null) {
                view = inflater.inflate(resourceId, null);
                viewHolder = new ViewHolder();
                viewHolder.newsTitle = (TextView) view.findViewById(R.id.news_title);
                viewHolder.newsImage = (ImageView) view.findViewById(R.id.news_image);
                view.setTag(viewHolder);
            } else {
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

//            return view;
//        }

//        else if(getItemViewType(position) == 1)//如果是顶部viewpager
//        {
//            ViewPagerHolder holder = null;
//            if(convertView==null)
//            {
//                view = inflater.inflate(R.layout.head_viewpager, null);
//                holder = new ViewPagerHolder();
//                holder.viewPager = (ViewPager)view.findViewById(R.id.headviewpager);
//
//
//                List<ImageView> listtemp = new ArrayList<ImageView>();
//                for(int i = 0;i<4;i++)
//                {
//                    ImageView img = new ImageView(context);
//                    img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,100));
//                    img.setScaleType(ImageView.ScaleType.FIT_XY);
//                    img.setBackgroundResource(R.drawable.favorite);
//                    listtemp.add(img);
//                }
//
//                ImageViewPagerAdapter viewadapter = new ImageViewPagerAdapter(listtemp);
//                holder.viewPager.setAdapter(viewadapter);
//
//                view.setTag(holder);
//            }
//            else
//            {
//                view  = convertView;
//                holder = (ViewPagerHolder)view.getTag();
//            }
//        }

        return view;
        }


    public void refreshNewsList(List<News> newsList){
        if(newsList != null){
            clear();
            addAll(newsList);
            notifyDataSetChanged();
        }
        else {
            Toast.makeText(context,"newsList 是空的 ",Toast.LENGTH_SHORT).show();
        }
    }

    //判断是不是第一项
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return position > 0 ? 0 : 1;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    class ViewPagerHolder {
        ViewPager viewPager;
    }
    class ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
    }
}
