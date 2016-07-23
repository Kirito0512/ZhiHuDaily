package com.xuqi.zhihu_daily.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xuqi.zhihu_daily.R;
import com.xuqi.zhihu_daily.adapter.NewsListAdapter;
import com.xuqi.zhihu_daily.network.NetWork;
import com.xuqi.zhihu_daily.task.LoadNewsList;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private ListView list;
    private SwipeRefreshLayout swipe;
    private NewsListAdapter adapter;
    private boolean connection;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查询网络状态
        connection = NetWork.isOpenNetwork(this);
        list = (ListView) findViewById(R.id.listview);
        swipe = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        Context context = MainActivity.this;

        //在活动的左上角添加标题
        setTitle(getTime());
        //官方刷新
        swipe.setOnRefreshListener(this);
        //刷新动画的颜色
        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipe.setDistanceToTriggerSync(300);
        // 设定下拉圆圈的背景
        swipe.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        //定义适配器
        adapter = new NewsListAdapter(this,R.layout.news_item);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        //刚打开app时，自动刷新
        if(NetWork.isOpenNetwork(context)){
            Log.d(TAG, "显示内容");
            new LoadNewsList(adapter).execute();
            Log.d(TAG, "显示完毕");
        }
    }


//为当前活动创建菜单
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    //为菜单定义响应事件
    public boolean onOptionsItemSelected(MenuItem item){
        int order = item.getItemId();
        switch(order){
            //收藏
            case R.id.menu_news_like:
                Toast.makeText(this,"收藏~~",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "点击了收藏");
                break;
            //刷新
            case R.id.menu_refresh:
                Toast.makeText(this,"刷新~~",Toast.LENGTH_SHORT).show();
                onRefresh();
                Log.d(TAG, "点击了刷新");
                break;

            //设置
            case R.id.menu_setting:
                Toast.makeText(this,"设置~~",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "点击了设置");
                break;
            default:
        }
        return true;
    }

    //overflow中的Action按钮应不应该显示图标，是由MenuBuilder这个类的setOptionalIconsVisible变量来决定的，
    //如果我们在overflow被展开的时候将这个变量赋值为true，那么里面的每一个Action按钮对应的图标就都会显示出来了。
    // 赋值的方法当然仍然是用反射了
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public static String getTime(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd EEEE");
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    @Override
    public void onRefresh() {
        if(NetWork.isOpenNetwork(this)){
            swipe.setRefreshing(true);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    // 通过setRefreshing(false)使动画停止
                    new LoadNewsList(adapter).execute();
                    swipe.setRefreshing(false);
                }
            }, 6000);
        }else{
            swipe.setRefreshing(false);
            Toast.makeText(this,"net is disconneted",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,NewsContentActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("key", adapter.getItem(position)); // 传递一个user对象
        intent.putExtras(mBundle);
        this.startActivity(intent);
    }

//    private void initHeadView()
//    {
//        View view = LayoutInflater.from(this).inflate(R.layout.image_viewpager, null);
//        ViewPager viewpager = (ViewPager)view.findViewById(R.id.headviewpager);
//        List<ImageView> list_temp = new ArrayList<ImageView>();
//        for(int i = 0;i<4;i++)
//        {
//            ImageView img = new ImageView(this);
//            img.setLayoutParams(new ViewPager.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,100));
//            img.setScaleType(ImageView.ScaleType.FIT_XY);
//            img.setBackgroundResource(R.drawable.no_image);
//            list_temp.add(img);
//        }
//        ImageViewPagerAdapter viewadapter = new ImageViewPagerAdapter(list_temp);
//        list.addHeaderView(view);
//        list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data));
//        viewpager.setAdapter(viewadapter);
//    }
}
