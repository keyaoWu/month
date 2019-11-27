package com.bawei.wukeyao20191127.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 功能：MyView类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:25
 */
public class MyView extends ViewGroup {
    private Context mContext;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        //获取总数
        int childCount = getChildCount();

        int space = 10;
        int left = 0;
        int right = 0;
        int top = 0;
        int buttom = 0;
        for (int j = 0; j < childCount; j++) {
            View childAt = getChildAt(j);

            //测量宽高
            childAt.measure(0, 0);

            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();

            left = space + right;
            right = left + measuredWidth;

            int width = getWidth();
            if (right > width) {
                left = space;
                top = buttom + space;
            }
            right = left + measuredWidth;
            buttom = buttom + measuredHeight;

            childAt.layout(left, top, right, buttom);
        }
    }

    public void  addTag(String content){
        TextView textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(12,12,12,12);
        addView(textView);
    }
}
