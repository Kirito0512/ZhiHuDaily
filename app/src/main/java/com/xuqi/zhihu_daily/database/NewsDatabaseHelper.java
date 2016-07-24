package com.xuqi.zhihu_daily.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/24.
 */
public class NewsDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_NEWS = "create table table_fav_news ("
            + "id integer primary key autoincrement,"
            + "news_id integer,"
            + "image text,"
            + "type integer,"
            + "ga_prefix integer,"
            + "title text)";

    public NewsDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        switch(oldVersion){
//            case 1:
//                db
//        }
    }
}
