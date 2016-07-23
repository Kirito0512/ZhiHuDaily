package com.xuqi.zhihu_daily.task;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import com.xuqi.zhihu_daily.bean.NewsContent;
import com.xuqi.zhihu_daily.netdata.GsonData;
import com.xuqi.zhihu_daily.netdata.HttpUtil;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/22.
 */
//Integer为传入的参数，对应doInBackground的形参，NewsContent为返回类型，对应doInBackground的返回类型
public class LoadNewsContent extends AsyncTask<Integer, Void, NewsContent> {
    private static final String TAG = "LoadNewsContent";
    private WebView webview;

    public LoadNewsContent(WebView webview){
        this.webview = webview;
    }


    @Override
    protected NewsContent doInBackground(Integer... params) {
        NewsContent newsContent = null;
        try {
            newsContent = GsonData.parseJsonToContent(HttpUtil.getNewsDetail(params[0]));
            Log.d(TAG, "doInBackground: params = "+params);
            Log.d(TAG, "doInBackground: params[0] = "+params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return newsContent;
        }
    }

    //还要再看
    protected void onPostExecute(NewsContent newsContent){
        String headerImage;
        if (newsContent.getImage() == null || newsContent.getImage() == "") {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";

        } else {
            headerImage = newsContent.getImage();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(newsContent.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(newsContent.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + newsContent.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
        Log.d(TAG, "onPostExecute: url = "+mNewsContent);
        webview.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);

    }
}
