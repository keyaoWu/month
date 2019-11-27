package com.bawei.wukeyao20191127.base;

import com.bawei.wukeyao20191127.contract.Contract;

import java.lang.ref.WeakReference;

/**
 * 功能：BasePresenter类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:06
 */
public abstract class BasePresenter<V extends Contract.IView> implements Contract.Presenter {
    //虚引用
    private WeakReference<V> mWeak;

    public BasePresenter() {
        initModel();
    }

    abstract protected void initModel();

    public void onAttachView(V view){
        mWeak = new WeakReference<>(view);
    }

    public void onDeattchView(){
        if (mWeak!=null){
            mWeak.clear();
            mWeak=null;
        }
    }

    //获取view

    public V getView(){
        return mWeak.get();
    }
}
