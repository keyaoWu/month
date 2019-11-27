package com.bawei.wukeyao20191127.until;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.wukeyao20191127.app.MyApp;

import java.util.Map;

/**
 * 功能：VolleyUntil类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：8:49
 */
public class VolleyUntil {

    private final RequestQueue queue;

    //单例模式
    private VolleyUntil() {
        queue = Volley.newRequestQueue(MyApp.mContext);
    }

    private static class VlooeyHolder {
        static VolleyUntil volleyUntil = new VolleyUntil();
    }

    public static VolleyUntil getInstance() {
        return VlooeyHolder.volleyUntil;
    }

    //封装get请求
    public void getInfo(String url, final volleyCallBack volleyCallBack) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volleyCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallBack.onError(error.getMessage());
            }
        });
        queue.add(request);
    }

    //封装post请求
    public void post(String url, final Map<String,String>map, final volleyCallBack volleyCallBack){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             volleyCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyCallBack.onError(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map!=null){
                    return map;
                }
                return super.getParams();
            }
        };
        queue.add(request);
    }

    //判断是否有网
    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null && activeNetworkInfo.isAvailable()){
            return true;
        }else {
            return false;
        }
    }

    //是否是wifi
    public boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else {
            return false;
        }
    }

    //是否是移动网
    public boolean isMobile(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return true;
        }else {
            return false;
        }
    }
    //封装接口
    public interface volleyCallBack {
        void onSuccess(String json);

        void onError(String error);
    }
}
