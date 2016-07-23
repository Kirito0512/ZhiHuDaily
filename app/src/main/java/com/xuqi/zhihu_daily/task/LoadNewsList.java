package com.xuqi.zhihu_daily.task;

import android.os.AsyncTask;
import android.util.Log;

import com.xuqi.zhihu_daily.adapter.NewsListAdapter;
import com.xuqi.zhihu_daily.bean.News;
import com.xuqi.zhihu_daily.netdata.GsonData;
import com.xuqi.zhihu_daily.netdata.HttpUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
//用于为NewsListAdapter提供News List数据
//void表示执行AsyncTask时不需要传入参数给后台
//void表示不需要展示进度条
//List<News>表示用这个类型做为返回的数据
public class LoadNewsList extends AsyncTask<Void ,Void ,List<News>>{
    private static final String TAG = "LoadNewsList";
    private NewsListAdapter adapter;

    public LoadNewsList(NewsListAdapter adapter){
        super();
        this.adapter = adapter;
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsList = null;
        try{
            //
            Log.d(TAG, "doInBackground: gson data");
            newsList = GsonData.parseJSONToList(HttpUtil.getNewsList());
            Log.d(TAG, "doInBackground:  gson finish");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return newsList;
        }
    }

    protected void onPostExecute(List<News> newsList){
        Log.d(TAG, "刷新开始");
        if(newsList == null)
            Log.d(TAG, "newsList is null");
        adapter.refreshNewsList(newsList);
        Log.d(TAG, "刷新完毕");
    }
}
