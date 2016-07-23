package com.xuqi.zhihu_daily.netdata;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuqi.zhihu_daily.bean.News;
import com.xuqi.zhihu_daily.bean.NewsContent;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
//解析服务器返回的JSON数据
public class GsonData {
    private static final String TAG = "GsonData";
    public static List<News> parseJSONToList(String json) throws JSONException {
        //使用gson解析
        Gson gson = new Gson();
        //服务器返回的json数据需要修改
        json = json.substring(json.indexOf("["),json.indexOf("top_stories")-2);
        Log.d(TAG, "parseJSONToList: json data is "+json);
        json = json.replace("[","");
        json = json.replace("]","");
        json = "["+json+"]";

        Log.d(TAG, "parseJSONToList: json data is "+json);
        //返回News list数据
        List<News> list = gson.fromJson(json,new TypeToken<List<News>>(){}.getType());
        return list;
    }

    public static NewsContent parseJsonToContent(String json){
        Log.d(TAG, "parseJsonToContent: 解析新闻内容");
        Gson gson = new Gson();
        NewsContent newsContent = gson.fromJson(json,NewsContent.class);
        return newsContent;
    }
}
