package com.example.caolin.frame;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.caolin.frame.util.NotificationUtils;
import com.example.caolin.frame.util.SpSettingsUtils;


public class CustomApplication extends Application {
    public static final String PREFERENCE_NAME = "_Settings";
    public static CustomApplication mInstance;
    public SpSettingsUtils mSpSettingsUtil;
    public NotificationUtils mNotificationUtils;
    private MediaPlayer mMediaPlayer;
    public static boolean hasNetwork=false;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static CustomApplication getInstance() {
        return mInstance;
    }
    public synchronized NotificationUtils getNotificationManager() {
        if (mNotificationUtils == null)
            mNotificationUtils = new NotificationUtils(this);
        return mNotificationUtils;
    }

    public synchronized MediaPlayer getMediaPlayer() {
        if (mMediaPlayer == null)
            mMediaPlayer = MediaPlayer.create(mInstance, R.raw.beep);
        return mMediaPlayer;
    }
    public synchronized SpSettingsUtils getSpSettingsUtil() {
        if (mSpSettingsUtil == null) {
            mSpSettingsUtil = new SpSettingsUtils(this, PREFERENCE_NAME);
        }
        return mSpSettingsUtil;
    }
}