package com.example.caolin.frame.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caolin.frame.CustomApplication;
import com.example.caolin.frame.MainActivity;
import com.example.caolin.frame.R;
import com.example.caolin.frame.base.BaseActivity;
import com.example.caolin.frame.util.SpSettingsUtils;
import com.example.caolin.frame.view.ColorAnimationView;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导�?
 *
 * @author nEdAy
 */
public class GuideActivity extends BaseActivity {

    private static final int NUM_PAGES = 4;
    private static final int[] resource = new int[]{R.mipmap.guide_0, R.mipmap.guide_1,
            R.mipmap.guide_2, R.mipmap.guide_3};

    private ColorAnimationView colorAnimationView;
    private ViewPager viewPager;
    private TextView tv_guide_left, tv_guide_right;
    private ImageView iv_guide_right;
    private List<View> dots;
    private List<View> dots_list;
    private View dot0, dot1, dot2, dot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    public void initView() {
        colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tv_guide_left = (TextView) findViewById(R.id.tv_guide_left);

        tv_guide_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endTutorial();
            }
        });
        tv_guide_right = (TextView) findViewById(R.id.tv_guide_right);
        tv_guide_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTutorial();
            }
        });
        iv_guide_right = (ImageView) findViewById(R.id.iv_guide_right);
//        iv_guide_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
//            }
//        });
        iv_guide_right.setOnClickListener(v -> {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);

        });
        dot0 = findViewById(R.id.v_dot0);
        dot1 = findViewById(R.id.v_dot1);
        dot2 = findViewById(R.id.v_dot2);
        dot3 = findViewById(R.id.v_dot3);
    }

    private void addDots() {
        dots = new ArrayList<>();// �?
        dots.add(dot0);
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots_list = new ArrayList<>();
        for (int i = 0; i < NUM_PAGES; i++) {
            dots.get(i).setVisibility(View.VISIBLE);
            dots_list.add(dots.get(i));
        }
    }

    public void initData() {
        addDots();
        MyFragmentStatePager myFragmentStatePager = new MyFragmentStatePager(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentStatePager);
        /**
         *  首先，你必须�? 设置 Viewpager�? adapter 之后在调用这个方�?
         *  第二点，setViewPager(ViewPager mViewPager,Object obj, int count, int... colors)
         *         第一个参�? �? 你需要传人的 viewpager
         *         第二个参�? �? �?个实现了ColorAnimationView.OnPageChangeListener接口的Object,用来实现回调
         *         第三个参�? �? viewpager �? 孩子数量
         *         第四个参�? int... colors ，你�?要设置的颜色变化值~~ 如何你传�? 空，那么触发默认设置的颜色动�?
         * */
        /**
         *  First: You need call this method after you set the Viewpager adpter;
         * Second: setViewPager(ViewPager mViewPager,Object obj�? int count, int... colors)
         *          so,you can set any length colors to make the animation more cool!
         * Third: If you call this method like below, make the colors no data, it will create
         *          a change color by default.
         * */
        colorAnimationView.setViewPager(viewPager, resource.length);
        // Four : Also ,you can call this method like this:
//        colorAnimationView.setViewPager(viewPager,,resource.length,0xffFF8080,0xff8080FF,0xffffffff,0xff80ff80);
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int oldPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == NUM_PAGES - 1) {
                    tv_guide_left.setVisibility(View.GONE);
                    iv_guide_right.setVisibility(View.GONE);
                    tv_guide_right.setVisibility(View.VISIBLE);
                    dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                    dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                    oldPosition = position;

                    //结束
                    //endTutorial();
                } else if (position < NUM_PAGES - 1) {
                    tv_guide_left.setVisibility(View.VISIBLE);
                    iv_guide_right.setVisibility(View.VISIBLE);
                    tv_guide_right.setVisibility(View.GONE);
                    dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                    dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                    oldPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void endTutorial() {
        SpSettingsUtils spSettingsUtil = CustomApplication.getInstance().getSpSettingsUtil();
        spSettingsUtil.setAllowFirstEnable(false);

        $.startActivity(MainActivity.class);
        //overridePendingTransition(R.anim.in_from_right, R.anim.out_from_right);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 页面�?�?
//        StatService.trackBeginPage(this, getLocalClassName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 页面结束
//        StatService.trackEndPage(this, getLocalClassName());
    }

    public class MyFragmentStatePager extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(position);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment")
    public class MyFragment extends Fragment {
        private int position;

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resource[position]);
            return imageView;
        }
    }
}
