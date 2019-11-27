package com.bawei.wukeyao20191127.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.wukeyao20191127.R;
import com.bawei.wukeyao20191127.bean.Bean;
import com.bawei.wukeyao20191127.glide.GldieHolder;

import java.util.List;

/**
 * 功能：MyAdapter类
 * 作者：武柯耀
 * 当前日期：2019/11/27
 * 当前时间：9:37
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private List<Bean.ResultBean> mLis;

    public MyAdapter(Context mContext, List<Bean.ResultBean> mLis) {
        this.mContext = mContext;
        this.mLis = mLis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         MyAdapter.ViewHolder viewHolder = null;
         View view = null;
         view = LayoutInflater.from(mContext).inflate(R.layout.item,null);
         viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
          viewHolder.name.setText(mLis.get(i).getCommodityName());
          viewHolder.price.setText(mLis.get(i).getMasterPic());
        GldieHolder.imageHolder(mLis.get(i).getMasterPic(),viewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLis.isEmpty()?0:mLis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

    public interface setOnItemClickListener{
        void onClick(int position);
    }

    public setOnItemClickListener listener;

    public void setsOnItemClickListener(setOnItemClickListener listener){
        this.listener = listener;
    }
}
