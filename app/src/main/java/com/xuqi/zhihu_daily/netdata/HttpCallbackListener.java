package com.xuqi.zhihu_daily.netdata;

/**
 * Created by Administrator on 2016/7/25.
 */
//在HttpUtil的sendHttpRequest方法中使用（Java回调机制）
public interface HttpCallbackListener {
    String onFinish(String response);
    void onError(Exception e);
}
