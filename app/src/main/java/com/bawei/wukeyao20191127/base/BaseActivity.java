package com.bawei.wukeyao20191127.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.wukeyao20191127.contract.Contract;

/**
 * 功能：BaseActivity类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:09
 */
public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity implements Contract.IView {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (providerLayoutId()!=0){
            setContentView(providerLayoutId());
            initViwe();
            initData();
            mPresenter = initPresenter();
            if (mPresenter!=null){
                mPresenter.onAttachView(this);
            }
            startCoding();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDeattchView();
        }
    }

    protected abstract void startCoding();

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initViwe();

    protected abstract int providerLayoutId();
}
