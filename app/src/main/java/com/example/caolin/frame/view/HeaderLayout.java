package com.example.caolin.frame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caolin.frame.R;

/**
 * 自定义头部布局
 *
 * @author nEdAy
 */
public class HeaderLayout extends RelativeLayout {
    private RelativeLayout mHeader;
    private RelativeLayout mLayoutLeftContainer;
    private RelativeLayout mLayoutRightContainer;
    private TextView mHtvSubTitle, mLeftTextButton, mRightTextButton;
    private ImageView mLeftImageButton;
    private onLeftButtonClickListener mLeftButtonClickListener;
    private ImageView mRightImageButton;
    private onRightButtonClickListener mRightButtonClickListener;

    public HeaderLayout(Context context) {
        super(context);
        init(context);
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("InflateParams")
    public void init(Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHeader = (RelativeLayout) mInflater.inflate(R.layout.include_top_title_bar, null);
        addView(mHeader);
        initViews();
    }

    public void initViews() {
        mLayoutLeftContainer = (RelativeLayout) findViewByHeaderId(R.id.title_left);
        mLayoutRightContainer = (RelativeLayout) findViewByHeaderId(R.id.title_right);
        mHtvSubTitle = (TextView) findViewByHeaderId(R.id.title_text);
        mLeftTextButton = (TextView) findViewByHeaderId(R.id.left_text_btn);
        mRightTextButton = (TextView) findViewByHeaderId(R.id.right_text_btn);
        mLeftImageButton = (ImageView) findViewById(R.id.left_image_btn);
        mRightImageButton = (ImageView) findViewById(R.id.right_image_btn);
    }

    public View findViewByHeaderId(int id) {
        return mHeader.findViewById(id);
    }

    public void init(HeaderStyle hStyle) {
        switch (hStyle) {
            case DEFAULT_TITLE:
                defaultTitle();
                break;

            case TITLE_LIFT_IMAGE_BUTTON:
                defaultTitle();
                titleLeftImageButton();
                break;

            case TITLE_DOUBLE_IMAGE_BUTTON:
                defaultTitle();
                titleLeftImageButton();
                titleRightImageButton();
                break;
        }
    }

    // 默认文字标题
    private void defaultTitle() {
        mHtvSubTitle.setVisibility(VISIBLE);
    }

    // 左侧自定义按钮
    private void titleLeftImageButton() {
        mLayoutLeftContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mLeftButtonClickListener != null) {
                    mLeftButtonClickListener.onClick();
                }
            }
        });
    }

    // 右侧自定义按钮
    private void titleRightImageButton() {
        mLayoutRightContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mRightButtonClickListener != null) {
                    mRightButtonClickListener.onClick();
                }
            }
        });
    }

    // title
    public void setDefaultTitle(CharSequence title) {
        if (title != null) {
            mHtvSubTitle.setText(title);
        }
    }

    // back+title
    public void setTitleAndLeftImageButton(CharSequence title, int id, String leftText,
                                           onLeftButtonClickListener onLeftButtonClickListener) {
        setDefaultTitle(title);
        if (mLeftImageButton != null && id > 0) {
            mLeftImageButton.setImageResource(id);
            setOnLeftButtonClickListener(onLeftButtonClickListener);
        }
        if (mLeftTextButton != null && !leftText.isEmpty()) {
            mLeftTextButton.setText(leftText);
        }
        mLayoutLeftContainer.setVisibility(View.VISIBLE);
    }

    // back+title+右文字
    public void setTitleAndRightTextButton(CharSequence title, String text,
                                           onRightButtonClickListener onRightButtonClickListener) {
        setDefaultTitle(title);
        if (mRightTextButton != null && !text.isEmpty()) {
            mRightTextButton.setText(text);
            setOnRightButtonClickListener(onRightButtonClickListener);
        }
        mLayoutRightContainer.setVisibility(View.VISIBLE);
    }

    // back+title+右图标
    public void setTitleAndRightImageButton(CharSequence title, int id,
                                            onRightButtonClickListener onRightButtonClickListener) {
        setDefaultTitle(title);
        mLayoutRightContainer.setVisibility(View.VISIBLE);
        if (mRightImageButton != null && id > 0) {
            mRightImageButton.setBackgroundResource(id);
            setOnRightButtonClickListener(onRightButtonClickListener);
        }
    }

    public void setOnLeftButtonClickListener(
            onLeftButtonClickListener listener) {
        mLeftButtonClickListener = listener;
    }

    public void setOnRightButtonClickListener(
            onRightButtonClickListener listener) {
        mRightButtonClickListener = listener;
    }

    public enum HeaderStyle {// 头部整体样式
        DEFAULT_TITLE, TITLE_LIFT_IMAGE_BUTTON, TITLE_DOUBLE_IMAGE_BUTTON
    }

    public interface onLeftButtonClickListener {
        void onClick();
    }

    public interface onRightButtonClickListener {
        void onClick();
    }


}
