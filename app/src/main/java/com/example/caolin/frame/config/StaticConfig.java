package com.example.caolin.frame.config;


import android.graphics.Bitmap;

import com.example.caolin.frame.R;

/**
 * 系统变量
 *
 * @author nEdAy
 */
public class StaticConfig {
    public static final boolean isDebugMode = true;

    //bitmapUtils的默认参数
    public static  final  int  DefaultLoadingImage= R.mipmap.ic_launcher;
    public static  final  int  DefaultLoadFailedImage= R.mipmap.ic_launcher;
    public static  final   Bitmap.Config  DefaultBitmapConfig= Bitmap.Config.RGB_565;

}
