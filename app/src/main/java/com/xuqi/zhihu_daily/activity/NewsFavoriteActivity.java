package com.xuqi.zhihu_daily.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xuqi.zhihu_daily.R;
import com.xuqi.zhihu_daily.adapter.NewsListAdapter;
import com.xuqi.zhihu_daily.database.NewsDatabaseOperation;
import com.xuqi.zhihu_daily.network.NetWork;

//显示收藏夹内容的活动
public class NewsFavoriteActivity extends Activity implements AdapterView.OnItemClickListener {
    private NewsListAdapter adapter;
    private NewsDatabaseOperation db;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_favorite);

        list = (ListView) findViewById(R.id.fav_news_listview);
        //定义适配器
        adapter = new NewsListAdapter(this,R.layout.news_item);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        db = new NewsDatabaseOperation(this,adapter);
        adapter = db.open_News_Favorites();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(NetWork.isOpenNetwork(NewsFavoriteActivity.this)){
            Intent intent = new Intent(this,NewsContentActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("key", adapter.getItem(position)); // 传递一个user对象
            intent.putExtras(mBundle);
            this.startActivity(intent);
        }
        else{
            NetWork.setNetWork(NewsFavoriteActivity.this);
        }    }
}
