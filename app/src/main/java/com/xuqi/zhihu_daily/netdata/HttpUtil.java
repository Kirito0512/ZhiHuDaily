package com.xuqi.zhihu_daily.netdata;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/21.
 */
public class HttpUtil {
    public static String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static String STORY_VIEW = "http://daily.zhihu.com/story/";
    public static String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";
    private static final String TAG = "HttpUtil";

    //获取日报的列表
    public static String getNewsList() throws IOException {
        Log.d(TAG, "getNewsList: ");
        return sendHttpRequest(NEWSLIST_LATEST);
    }

    //获取具体内容
    public static String getNewsDetail(int id) throws IOException {
        Log.d(TAG, "获取具体内容的url ："+NEWSDETAIL + " + "+id);
        return sendHttpRequest(NEWSDETAIL + id);
    }

    public static String sendHttpRequest(final String address) {
                HttpURLConnection connection = null;
                StringBuffer response = null;
                try {
                    URL url = new URL(address);
                    Log.d(TAG, "sendHttpRequest: address = " + address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(16000);
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        return response.toString();
                    } else {
                        throw new IOException("Network Error - response code: " + connection.getResponseCode());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                } finally {
                    if (connection != null)
                        connection.disconnect();
                }

            }
}
