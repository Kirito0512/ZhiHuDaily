package com.xuqi.zhihu_daily.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.xuqi.zhihu_daily.R;
import com.xuqi.zhihu_daily.bean.News;
import com.xuqi.zhihu_daily.task.LoadNewsContent;

public class NewsContentActivity extends Activity {
    private WebView webView;
    private News news;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        // 给左上角图标的左边加上一个返回的图标
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        webView = (WebView) findViewById(R.id.webview);
        setWebView(webView);

        //用于获取本类中startActivity方法中填充的数据,news为自定义的类，所以要用Serializable序列化
        news = (News) getIntent().getSerializableExtra("key");
        //execute方法里的参数对应AsyncTask的Params参数，在doInBackground中使用
        new LoadNewsContent(webView).execute(news.getNews_id());
    }

    //网络设置
    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }
}
