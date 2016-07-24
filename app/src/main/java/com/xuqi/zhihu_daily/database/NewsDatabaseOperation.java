package com.xuqi.zhihu_daily.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xuqi.zhihu_daily.adapter.NewsListAdapter;
import com.xuqi.zhihu_daily.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class NewsDatabaseOperation {
    private NewsDatabaseHelper dbHelper = null;
    private SQLiteDatabase db ;
    private NewsListAdapter adapter;
    private Context context;
    private static final String TAG = "NewsDatabaseOperation";

    public NewsDatabaseOperation(Context context){
        this.context = context;
        dbHelper = new NewsDatabaseHelper(context,"Colletion_News.db",null,1);
        db = dbHelper.getWritableDatabase();
    }

    public NewsDatabaseOperation(Context context,NewsListAdapter adapter){
        this.context = context;
        this.adapter = adapter;
            dbHelper = new NewsDatabaseHelper(context,"Colletion_News.db",null,1);
            db = dbHelper.getWritableDatabase();
    }

    public void collect_News(News news){
        Log.d(TAG, "collect_News: 开始收藏");
        db.execSQL("insert into table_fav_news (news_id ,image ,type ,ga_prefix ,title) values(?,?,?,?,?)",
                new String[]{String.valueOf(news.getNews_id()),news.getNews_image(),String.valueOf(news.getType()),news.getGa_prefix(),news.getNews_title()});
        Log.d(TAG, "collect_News: 收藏完毕");
    }

    public void delete_News(News news){
        Log.d(TAG, "delete_News: 取消收藏");
        db.execSQL("delete from table_fav_news where news_id = "+news.getNews_id());
        Log.d(TAG, "delete_News: 取消完毕");
    }
    
    public boolean isFavorite(News news){
        Cursor cursor = db.rawQuery("select * from table_fav_news where news_id = "+news.getNews_id()+" AND image = \'"+news.getNews_image()+"\'",null);
        return cursor.moveToNext();
    }

    public NewsListAdapter open_News_Favorites(){
        List<News> newsList = new ArrayList<News>();
        Cursor cursor = db.query("table_fav_news",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{

                int id = cursor.getInt(cursor.getColumnIndex("news_id"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int ga_prefix = cursor.getInt(cursor.getColumnIndex("ga_prefix"));
                String title = cursor.getString(cursor.getColumnIndex("title"));

                Log.d(TAG, "open_News_Favorites: ");
                Log.d(TAG, "id = "+id+" title = "+title+" image = "+image);
                News news = new News(id,title,image);
                newsList.add(news);
            }while(cursor.moveToNext());
            adapter.refreshNewsList(newsList);
        }
        return adapter;
    }
}
