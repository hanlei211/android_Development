package com.rjyx.quickdevelopment.base;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjyx.quickdevelopment.BaseApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by rjyx on 2018/4/25.
 */

public  abstract  class BaseFragment  extends Fragment {

    private View mContextView;
    /** 是否设置沉浸状态栏*/
    private boolean isSetStatusBar = true;
    /** 是否允许全屏*/
    private boolean  mAllowFullScreen = true;
    /** 是否禁止旋转屏幕*/
    private boolean  isAllowScreenRotate = false;
    /** 是否输出日志信息*/
    private boolean isDebug;
    private Unbinder unbinder;
    protected final String TAG = this.getClass().getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDebug = BaseApplication.getInstance().isDebug();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContextView = inflater.inflate(getLayoutId(), container, false);
        unbinder= ButterKnife.bind(this,mContextView);
        initView(mContextView);
        initData();
        setListener();
        return mContextView;
    }

    /**
     * [初始化Bundle参数]
     *
     * @param parms
     */
    public abstract void initParms(Bundle parms);

    /**
     * 设置ContentView
     * @return R.layout.xxx
     */
    protected abstract int getLayoutId();

    /**
     * 初始化请求数据
     */
    protected abstract void initData();

    /**
     * 初始化 view
     */
    protected abstract  void  initView(View mContextView);


    /**
     * 设置 listener 监听
     */
    protected abstract  void setListener();


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    /**
     * [日志输出]
     *
     * @param msg
     */
    protected void $Log(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

}
