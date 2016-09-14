package com.example.caolin.frame.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.caolin.frame.CustomApplication;
import com.example.caolin.frame.R;
import com.example.caolin.frame.util.CommonUtils;

/**
 * 基本的操作共通抽取
 *
 * @author nEdAy
 */
public class BaseOperation {
    /**
     * 提示
     */
    Toast mToast;
    /***
     * 上下文
     **/
    private Activity mContext = null;
    private View view_0;

    public BaseOperation(Activity mContext) {
        this.mContext = mContext;
    }

    /**
     * 启动Activity
     */
    public void startActivity(Class<?> cla) {
        mContext.startActivity(new Intent(mContext, cla));
    }

    public void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    /**
     * 打Log showLog
     */
    public void showLog(String msg) {
        //if (StaticConfig.isDebugMode)
    }

    /**
     * 检查网络连接是否正常
     */
    public boolean isNetConnected() {
        return CommonUtils.isNetworkAvailable(mContext);
    }

    public void showToast(final String text) {
        if (!TextUtils.isEmpty(text)) {
            mContext.runOnUiThread(() -> {
                // TODO Auto-generated method stub
                if (mToast == null) {
                    mToast = Toast.makeText(CustomApplication.getInstance(), text,
                            Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(text);
                }
                mToast.show();
            });

        }
    }

    public void showToast(final int resId) {
        mContext.runOnUiThread(() -> {
            // TODO Auto-generated method stub
            if (mToast == null) {
                mToast = Toast.makeText(CustomApplication.getInstance(), resId,
                        Toast.LENGTH_SHORT);
            } else {
                mToast.setText(resId);
            }
            mToast.show();
        });
    }

    private static final ColorDrawable TRANSPARENT_DRAWABLE = new ColorDrawable(R.color.global_transparent);

    private void fadeInDisplay(ImageView imageView, Bitmap bitmap) {
        final TransitionDrawable transitionDrawable =
                new TransitionDrawable(new Drawable[]{
                        TRANSPARENT_DRAWABLE,
                        new BitmapDrawable(imageView.getResources(), bitmap)
                });
        imageView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(500);
    }

}
