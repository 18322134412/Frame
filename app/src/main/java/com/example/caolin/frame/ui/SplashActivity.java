package com.example.caolin.frame.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.caolin.frame.CustomApplication;
import com.example.caolin.frame.R;
import com.example.caolin.frame.util.SpSettingsUtils;

public class SplashActivity extends FragmentActivity {

    private static final int SHOW_TIME_MIN = 1500;

    private static final int GO_GUIDE = 0;
    private static final int GO_MAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }


    private void initView() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int result;
                long startTime = System.currentTimeMillis();
                result = loadingCache();
                long loadingTime = System.currentTimeMillis() - startTime;
                if (loadingTime < SHOW_TIME_MIN) {
                    try {
                        Thread.sleep(SHOW_TIME_MIN - loadingTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(Integer result) {
                switch (result) {
                    case GO_GUIDE:
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                        break;
                    case GO_MAIN:
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                        //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        break;
                    default:
                        break;
                }
                finish();
            }
        }.execute();
    }


    private int loadingCache() {
        SpSettingsUtils spSettingsUtil = CustomApplication.getInstance().getSpSettingsUtil();
        Boolean user_first = spSettingsUtil.isAllowFirst();
        if (user_first) {
            return GO_GUIDE;
        } else {
            return GO_MAIN;
        }
    }


}
