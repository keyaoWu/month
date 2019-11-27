package com.bawei.wukeyao20191127.presenter;

import com.bawei.wukeyao20191127.base.BasePresenter;
import com.bawei.wukeyao20191127.contract.Contract;
import com.bawei.wukeyao20191127.model.Model;

/**
 * 功能：Presenter类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:06
 */
public class Presenter extends BasePresenter {
    private Contract.IModel model;

    @Override
    protected void initModel() {
        model = new Model();
    }

    @Override
    public void onStartRequest(String url) {
        model.getInfo(url, new Contract.myCallBack() {
            @Override
            public void onSuccess(String json) {
                getView().onSuccess(json);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }
}
