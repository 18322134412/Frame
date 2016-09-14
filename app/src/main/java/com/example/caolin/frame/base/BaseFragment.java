package com.example.caolin.frame.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.caolin.frame.CustomApplication;


/**
 * Fragment基类
 *
 * @author nEdAy
 */
public abstract class BaseFragment extends Fragment {
    protected CustomApplication mApplication;
    /**
     * 共通操作
     **/
    public BaseOperation $ = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mApplication = CustomApplication.getInstance();
        //实例化共通操作
        $ = new BaseOperation(getActivity());
    }
}
