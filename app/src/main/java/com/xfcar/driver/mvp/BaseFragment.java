package com.xfcar.driver.mvp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xfcar.driver.utils.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


/**
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {
    protected FragmentActivity mActivity;
    protected boolean isDestroyView = true;
    private boolean isInResume = false;   //判断当前Fragment的生命周期是否处于resume
    protected View mRootView;
    public BaseFragment(){
        super();
    }

    /**
     * 这里的注解没有作用就删了,放在子类
     * 2016-3-18 rw
      */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivity == null) {
            mActivity = getActivity();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        isInResume = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInResume = false;
    }

    public boolean isInResume(){
        return isInResume;
    }

    protected abstract int getContentViewLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isDestroyView = false;
        mRootView = executeCreateView(inflater,container,savedInstanceState);
        return mRootView;
    }

    public View executeCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View contentView = inflater.inflate(getContentViewLayoutId(), container, false);
        return contentView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;                           //清除掉相应的界面
        isDestroyView = true;
    }



    protected void startActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void startActivityForResult(Class clazz, int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void startActivityForResult(Class clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //记录Fragment是否被销毁
    public boolean isViewDestroy(){
        return isDestroyView;
    }

    public void showMsg(int str){
        showMsg(getString(str));
    }

    public void showMsg(String str){
        Utils.showMsg(getActivity(), str);
    }

    public String getPageTitle(){//获得页面标题，用于数据统计
        return null;
    }


    /*显示toast消息
    * */
    protected void toastMsg(int str) {
        Utils.showMsg(getActivity(), str);
    }

    protected void toastMsg(String str) {
        Utils.showMsg(getActivity(), str);
    }

    protected int getDimen(int dimenRes){
        return getActivity().getResources().getDimensionPixelOffset(dimenRes);
    }

}
