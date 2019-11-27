package com.bawei.wukeyao20191127.model;

import com.bawei.wukeyao20191127.contract.Contract;
import com.bawei.wukeyao20191127.until.VolleyUntil;

/**
 * 功能：Model类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:06
 */
public class Model implements Contract.IModel {
    @Override
    public void getInfo(String url, final Contract.myCallBack myCallBack) {
        VolleyUntil.getInstance().getInfo(url, new VolleyUntil.volleyCallBack() {
            @Override
            public void onSuccess(String json) {
                myCallBack.onSuccess(json);
            }

            @Override
            public void onError(String error) {
                myCallBack.onError(error);
            }
        });
    }

    @Override
    public void postInfo(String url, Contract.myCallBack myCallBack) {

    }
}
