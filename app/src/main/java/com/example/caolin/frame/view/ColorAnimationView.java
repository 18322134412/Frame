package com.example.caolin.frame.view;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ColorAnimationView
        extends View
        implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int GREEN = 0xff80ff80;
    private static final int ORANGE = 0xfffe9200;
    private static final int TRANSPARENT = 0x00000000;
    private static final int DURATION = 3000;
    ViewPager.OnPageChangeListener onPageChangeListener;
    private ValueAnimator colorAnim = null;
    private PageChangeListener mPageChangeListener;

    public ColorAnimationView(Context context) {
        this(context, null, 0);

    }


    public ColorAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPageChangeListener = new PageChangeListener();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    /**
     * This is the only method you need care about.
     *
     * @param mViewPager ,you need set the adpater before you call this.
     * @param count      ,this param set the count of the viewpaper's child
     * @param colors     ,this param set the change color use (int... colors),
     *                   so,you could set any length if you want.And by default.
     *                   if you set nothing , don't worry i have already creat
     *                   a default good change color!
     */
    public void setViewPager(ViewPager mViewPager, int count, int... colors) {
//		this.mViewPager = mViewPager;
        if (mViewPager.getAdapter() == null) {
            throw new IllegalStateException(
                    "ViewPager does not have adapter instance.");
        }
        mPageChangeListener.setViewPagerChildCount(count);

        mViewPager.setOnPageChangeListener(mPageChangeListener);
        if (colors.length == 0) {
            createDefaultAnimation();
        } else {
            createAnimation(colors);
        }

    }

    private void seek(long seekTime) {
        if (colorAnim == null) {
            createDefaultAnimation();
        }
        colorAnim.setCurrentPlayTime(seekTime);
    }

    private void createAnimation(int... colors) {
        if (colorAnim == null) {
            colorAnim = ObjectAnimator.ofInt(this,
                    "backgroundColor", colors);
            colorAnim.setEvaluator(new ArgbEvaluator());
            colorAnim.setDuration(DURATION);
            colorAnim.addUpdateListener(this);
        }
    }

    private void createDefaultAnimation() {
        colorAnim = ObjectAnimator.ofInt(this,
                "backgroundColor", ORANGE, RED, GREEN, BLUE, TRANSPARENT);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setDuration(DURATION);
        colorAnim.addUpdateListener(this);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
//		long playtime = colorAnim.getCurrentPlayTime();
    }

    private class PageChangeListener
            implements ViewPager.OnPageChangeListener {

        private int viewPagerChildCount;

        public int getViewPagerChildCount() {
            return viewPagerChildCount;
        }

        public void setViewPagerChildCount(int viewPagerChildCount) {
            this.viewPagerChildCount = viewPagerChildCount;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            int count = getViewPagerChildCount() - 1;
            if (count != 0) {
                float length = (position + positionOffset) / count;
                int progress = (int) (length * DURATION);
                ColorAnimationView.this.seek(progress);
            }
            // call the method by default
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

        }

        @Override
        public void onPageSelected(int position) {
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(state);
            }
        }
    }
}


