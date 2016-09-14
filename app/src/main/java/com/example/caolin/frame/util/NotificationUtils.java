package com.example.caolin.frame.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.caolin.frame.R;

import java.io.File;

/**
 * Created by caolin on 2016/4/29.
 */
public class NotificationUtils {
    /** Notification构造器 */
    NotificationCompat.Builder mBuilder;
    /** Notification的ID */
    int notifyId = 100;
    /** 通知管理 */
    NotificationManager mNotificationManager;
    /**上线文*/
    Context mContext;
    public NotificationUtils(Context context){
        mNotificationManager = (NotificationManager) (context.getSystemService(Context.NOTIFICATION_SERVICE));
        this.mContext=context;
    }
    /** 初始化通知栏 */
    private void initNotify(){
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContentTitle("默认标题")
                .setContentText("默认内容")
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
//				.setNumber(number)//显示数量
                .setTicker("您有新的通知")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.ic_launcher);
    }

    /** 显示通知栏 */
    public void showNotify(String title,String content){
        mBuilder.setContentTitle(title)
                .setContentText(content);
//				.setNumber(number)//显示数量
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
//
    /** 显示通知栏点击跳转到指定Activity */
    public void showIntentActivityNotify(String title,String content,Intent resultIntent){
        // Notification.FLAG_ONGOING_EVENT --设置常驻 Flag;Notification.FLAG_AUTO_CANCEL 通知栏上点击此通知后自动清除此通知
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        mBuilder.setAutoCancel(true)//点击后让通知将消失
                .setContentTitle(title)
                .setContentText(content)
                .setTicker("有新的发现");
        //点击的意图ACTION是跳转到Intent
        //Intent resultIntent = new Intent(mContext, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }

    /** 显示通知栏点击打开Apk */
    public void showIntentApkNotify(String title,String content,String apk_path){
        // Notification.FLAG_ONGOING_EVENT --设置常驻 Flag;Notification.FLAG_AUTO_CANCEL 通知栏上点击此通知后自动清除此通知
        // notification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        mBuilder.setAutoCancel(true)//点击后让通知将消失
                .setContentTitle(title)
                .setContentText(content)
                .setTicker("下载完成!!!");
        //我们这里需要做的是打开一个安装包
        Intent apkIntent = new Intent();
        apkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        apkIntent.setAction(android.content.Intent.ACTION_VIEW);
        //注意：这里的这个APK是放在assets文件夹下，获取路径不能直接读取的，要通过COYP出去在读或者直接读取自己本地的PATH，这边只是做一个跳转APK，实际打不开的
        //Uri uri = Uri.parse(apk_path);
        Uri uri = Uri.fromFile(new File(apk_path));
        apkIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        PendingIntent contextIntent = PendingIntent.getActivity(mContext, 0,apkIntent, 0);
        mBuilder.setContentIntent(contextIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
    //自定义通知
    public void shwoCustomNotify(String title,String Context){
        //先设定RemoteViews
        RemoteViews view_custom = new RemoteViews(mContext.getPackageName(), R.layout.view_custom_notify);
        //设置对应IMAGEVIEW的ID的资源图片
        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.ic_launcher);
        //view_custom.setInt(R.id.custom_icon,"setBackgroundResource",R.drawable.icon);
        view_custom.setTextViewText(R.id.tv_custom_title, title);
        view_custom.setTextViewText(R.id.tv_custom_content, Context);
		view_custom.setTextViewText(R.id.tv_custom_time, TimeUtils.getCurrentTime(TimeUtils.FORMAT_MONTH_DAY));
        //设置显示
		view_custom.setViewVisibility(R.id.tv_custom_time, View.VISIBLE);
        //设置number
        //NumberFormat num = NumberFormat.getIntegerInstance();
        //view_custom.setTextViewText(R.id.tv_custom_num, num.format(this.number));
        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setContent(view_custom)
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("有新的消息")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                .setSmallIcon(R.drawable.ic_launcher);
        //mNotificationManager.notify(notifyId, mBuilder.build());
        Notification notify = mBuilder.build();
        notify.contentView = view_custom;
        //notify.contentIntent = getDefalutIntent(Notification.FLAG_AUTO_CANCEL);
        mNotificationManager.notify(notifyId, notify);
    }
    /**
     * 清除当前创建的通知栏
     */
    public void clearNotify(int notifyId){
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
    }

    /**
     * 清除所有通知栏
     * */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }

    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性:
     * 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(mContext, 1, new Intent(), flags);
        return pendingIntent;
    }
}
