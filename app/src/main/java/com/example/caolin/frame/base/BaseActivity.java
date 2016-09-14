package com.example.caolin.frame.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.example.caolin.frame.CustomApplication;
import com.example.caolin.frame.R;
import com.example.caolin.frame.view.HeaderLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity基类
 *
 * @author nEdAy
 */
public abstract class BaseActivity extends FragmentActivity{
    /**
     * activity栈
     */
    public static List<Activity> activities=new ArrayList<Activity>();



    /**
     * 上下文
     */

    protected Context mContext;
    protected CustomApplication mApplication;
    /**
     * 头部
     **/
    protected HeaderLayout mHeaderLayout;
    /**
     * 共通操作
     **/
    public BaseOperation $ = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mContext = this;
        mApplication = CustomApplication.getInstance();
       //实例化共通操作
        $ = new BaseOperation(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 页面开始
//        StatService.trackBeginPage(this, getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 页面结束
//        StatService.trackEndPage(this, getLocalClassName());
    }

    /**
     * 只有title
     */
    public void initTopBarForOnlyTitle(String titleName) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.top_title_bar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.DEFAULT_TITLE);
        mHeaderLayout.setDefaultTitle(titleName);
    }

    /**
     * back+title
     */
    public void initTopBarForLeft(String titleName, String leftText) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.top_title_bar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_LIFT_IMAGE_BUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.mipmap.ic_back, leftText,
                this::finish);
    }

    /**
     * back**+title
     */
    public void initTopBarForLeft(String titleName, String leftText, HeaderLayout.onLeftButtonClickListener onLeftButtonClickListener) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.top_title_bar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_LIFT_IMAGE_BUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.mipmap.ic_back, leftText, onLeftButtonClickListener);
    }

    /**
     * back+title+右文字
     */
    public void initTopBarForBoth(String titleName, String leftText, String rightText,
                                  HeaderLayout.onRightButtonClickListener onRightButtonClickListener) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.top_title_bar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGE_BUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.mipmap.ic_back, leftText,
                this::finish);
        mHeaderLayout.setTitleAndRightTextButton(titleName, rightText,
                onRightButtonClickListener);
    }

    /**
     * back+title+右图标
     */
    public void initTopBarForBoth(String titleName, String leftText, int rightDrawableId,
                                  HeaderLayout.onRightButtonClickListener listener) {
        mHeaderLayout = (HeaderLayout) findViewById(R.id.top_title_bar);
        mHeaderLayout.init(HeaderLayout.HeaderStyle.TITLE_DOUBLE_IMAGE_BUTTON);
        mHeaderLayout.setTitleAndLeftImageButton(titleName,
                R.mipmap.ic_back, leftText,
                this::finish);
        mHeaderLayout.setTitleAndRightImageButton(titleName, rightDrawableId,
                listener);
    }

    /**
     * CycleTimes动画重复的次数
     * @param CycleTimes
     * @return
     */
    private Animation shakeAnimation(int CycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    /**
     * activity管理 添加到栈
     * @param activity
     */
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    /**
     * activity管理 移除从栈里
     * @param activity
     */
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    /**
     * activity管理 清空栈
     * @param activity
     */
    public static void finishAll(){
        for (Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

}
