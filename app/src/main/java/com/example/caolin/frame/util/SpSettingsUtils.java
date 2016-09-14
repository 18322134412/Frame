package com.example.caolin.frame.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SpSettingsUtils {
    private static final String SETTING_FIRST = "setting_first";
    private static final String SETTING_NOTIFY = "setting_notify";
    private static final String SETTING_VOICE = "setting_voice";
    private static final String SETTING_VIBRATE = "setting_vibrate";
    private static SharedPreferences.Editor editor;
    private SharedPreferences mSharedPreferences;

    public SpSettingsUtils(Context context, String name) {
        mSharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
    }

    public void cleanSharedPreference() {
        editor.clear();
        editor.commit();
    }

    public boolean isAllowFirst() {
        return mSharedPreferences.getBoolean(SETTING_FIRST, true);
    }

    public void setAllowFirstEnable(boolean isFirst) {
        editor.putBoolean(SETTING_FIRST, isFirst);
        editor.commit();
    }


    public boolean isAllowPushNotify() {
        return mSharedPreferences.getBoolean(SETTING_NOTIFY, true);
    }

    public void setPushNotifyEnable(boolean isChecked) {
        editor.putBoolean(SETTING_NOTIFY, isChecked);
        editor.commit();
    }


    public boolean isAllowVoice() {
        return mSharedPreferences.getBoolean(SETTING_VOICE, true);
    }

    public void setAllowVoiceEnable(boolean isChecked) {
        editor.putBoolean(SETTING_VOICE, isChecked);
        editor.commit();
    }

    public boolean isAllowVibrate() {
        return mSharedPreferences.getBoolean(SETTING_VIBRATE, true);
    }

    public void setAllowVibrateEnable(boolean isChecked) {
        editor.putBoolean(SETTING_VIBRATE, isChecked);
        editor.commit();
    }

}
