package com.bawei.wukeyao20191127.contract;

/**
 * 功能：Contract类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:03
 */
public interface Contract {
    //M层
    interface IModel{
        void getInfo(String url,myCallBack myCallBack);
        void postInfo(String url,myCallBack myCallBack);
    }

    //V层
    interface IView{
        void onSuccess(String json);
        void onError(String error);
    }
    //P层
    interface Presenter{
        void onStartRequest(String url);
    }
    //封装接口
    interface myCallBack{
        void onSuccess(String json);
        void onError(String error);
    }
}
