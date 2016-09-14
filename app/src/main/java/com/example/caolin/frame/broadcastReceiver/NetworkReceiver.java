package com.example.caolin.frame.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.widget.Toast;

import com.example.caolin.frame.CustomApplication;
import com.example.caolin.frame.util.CommonUtils;

/**
 * Android  利用广播BroadCast监听网络的变化
 * @author 16/49
 */
public class NetworkReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        //获取手机的连接服务管理器，这里是连接管理器类
        if (CommonUtils.isNetworkAvailable(context)) {
            CustomApplication.getInstance().hasNetwork=true;
            Toast.makeText(context, "当前有网络...", Toast.LENGTH_SHORT).show();
        }else{
            CustomApplication.getInstance().hasNetwork=false;
            Toast.makeText(context, "当前没有任何网络...", Toast.LENGTH_SHORT).show();
        }
    }
}
