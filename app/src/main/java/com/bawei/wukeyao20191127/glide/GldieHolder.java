package com.bawei.wukeyao20191127.glide;

import android.widget.ImageView;

import com.bawei.wukeyao20191127.R;
import com.bawei.wukeyao20191127.app.MyApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

/**
 * 功能：GldieHolder类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:13
 */
public class GldieHolder {
    public static void imageHolder(String url, ImageView imageView){
        Glide.with(MyApp.mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .into(imageView);
    }
}
