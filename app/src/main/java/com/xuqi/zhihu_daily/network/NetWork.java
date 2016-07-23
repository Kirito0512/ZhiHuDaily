package com.xuqi.zhihu_daily.network;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/20.
 */
public class NetWork {
    private static final String TAG = "NetWork";
    public static boolean flag = false;
   // public boolean flag = false;
    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        //有网络，判断网络类型
        if(networkInfo!= null && networkInfo.isConnected()) {
            //获取当前网络连接的类型信息
            int networkType = networkInfo.getType();

            if(ConnectivityManager.TYPE_WIFI == networkType){
               //当前为wifi网络
                Log.d(TAG, "wifi 网络下   ");
                return true;
            }
            else if(ConnectivityManager.TYPE_MOBILE == networkType){
                //当前为mobile网络
                Log.d(TAG, "移动数据 网络下    ");
                return true;
            }
        }
        //无网络
        else {
            Log.d(TAG, "无网络~");
            flag = setNetWork(context);
            return flag;
        }
        return true;
    }

    //无网络时，询问用户是否打开网络连接
    public static boolean setNetWork(final Context context){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage("网络设置提示");
        builder.setTitle("网络连接不可用,是否进行设置?");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
                flag = true;
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
        return flag;
    }

    //无网络，提示
    public static void promoteDisconneted(Context context){
        Toast.makeText(context,"无网络",Toast.LENGTH_SHORT).show();
    }

}

