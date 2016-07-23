package com.xuqi.zhihu_daily.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ImageViewPagerAdapter extends PagerAdapter {
    List<ImageView> list = null;//我们用一个list存放所有的imageview
    public ImageViewPagerAdapter (List<ImageView> _list)
    {
        list = _list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();//返回数据的个数
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return (arg0 == arg1);//这句话，比较重要，加上之后才能正确显示
    }
}
