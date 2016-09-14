package com.example.caolin.frame.IModel;


import android.text.TextUtils;

public class RxFactory {
    protected static final Object monitor = new Object();

//    private static UserApi mUserApi;
//
//    public static UserApi getUserServiceInstance(String session) {
//        synchronized (monitor) {
//            if (mUserApi == null || !TextUtils.isEmpty(session)) {
//                mUserApi = new RxService<>(UserApi.class, session).getService();
//            }
//            return mUserApi;
//        }
//    }
}
