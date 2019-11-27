package com.bawei.wukeyao20191127;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bawei.wukeyao20191127.base.BaseActivity;
import com.bawei.wukeyao20191127.base.BasePresenter;
import com.bawei.wukeyao20191127.presenter.Presenter;

public class JsActivity extends BaseActivity {


    private WebView webView;
    private String url = "file:///android_asset/info.html";
    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initViwe() {
        webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSDemo(),"viewport");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    protected int providerLayoutId() {
        return R.layout.activity_js;
    }

    @Override
    public void onSuccess(String json) {

    }

    @Override
    public void onError(String error) {

    }

    private class JSDemo {
        @SuppressLint("JavascriptInterface")
        @JavascriptInterface
        private void changeNum(String title){
            Toast.makeText(JsActivity.this, ""+title, Toast.LENGTH_SHORT).show();
        }

        public void buyNow(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl(url);
                }
            });
        }
    }
}
