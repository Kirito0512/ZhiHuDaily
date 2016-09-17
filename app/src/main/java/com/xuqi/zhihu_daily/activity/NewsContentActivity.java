package com.xuqi.zhihu_daily.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.xuqi.zhihu_daily.R;
import com.xuqi.zhihu_daily.bean.News;
import com.xuqi.zhihu_daily.database.NewsDatabaseOperation;
import com.xuqi.zhihu_daily.task.LoadNewsContent;

public class NewsContentActivity extends Activity {
    private WebView webView;
    private News news;
    private ActionBar actionBar;
    private boolean isFavorite;
    public static NewsDatabaseOperation db;
    private static final String TAG = "NewsContentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        // 给左上角图标的左边加上一个返回的图标
        actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        db = NewsDatabaseOperation.getInstance(this);

        webView = (WebView) findViewById(R.id.webview);
        setWebView(webView);

        //用于获取本类中startActivity方法中填充的数据,news为自定义的类，所以要用Serializable序列化
        news = (News) getIntent().getSerializableExtra("key");
        isFavorite = db.isFavorite(news);
        //execute方法里的参数对应AsyncTask的Params参数，在doInBackground中使用
        new LoadNewsContent(webView).execute(news.getNews_id());
    }

    //网络设置
    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    //为当前活动创建菜单
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_content,menu);
        if (isFavorite) menu.findItem(R.id.collect_news).setIcon(R.drawable.fav_active);
        return true;
    }
    //为菜单定义响应事件
    public boolean onOptionsItemSelected(MenuItem item){
//        NewsDatabaseOperation db;
        int order = item.getItemId();
        if(order == R.id.collect_news){
            isFavorite = db.isFavorite(news);
            if(isFavorite){
                Log.d(TAG, "取消收藏");
                item.setIcon(R.drawable.fav_normal);
                db.delete_News(news);
            }
            else{
                db.collect_News(news);
                item.setIcon(R.drawable.fav_active);
                Toast.makeText(this,"收藏~~",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "点击了收藏");
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
