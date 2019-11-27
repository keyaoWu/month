package com.bawei.wukeyao20191127;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.wukeyao20191127.adapter.MyAdapter;
import com.bawei.wukeyao20191127.base.BaseActivity;
import com.bawei.wukeyao20191127.base.BasePresenter;
import com.bawei.wukeyao20191127.bean.Bean;
import com.bawei.wukeyao20191127.presenter.Presenter;
import com.bawei.wukeyao20191127.view.MyView;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private EditText editText;
    private ImageView imageView;
    private MyView myView;
    private List<String> List = new ArrayList<>();
    private RecyclerView recyclerView;
    private List<Bean.ResultBean> mList = new ArrayList<>();
    private String sp;
    private MyAdapter myAdapter;

    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return  new Presenter();
    }

    @Override
    protected void initData() {
     imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             sp = editText.getText().toString().trim();
             if (sp.isEmpty()){
                 Toast.makeText(MainActivity.this, "请输入参数", Toast.LENGTH_SHORT).show();
             }else if(!mList.contains(sp)){
                 myView.addTag(sp);
                 List.add(sp);
             }else {
                 Toast.makeText(MainActivity.this, "参数重复", Toast.LENGTH_SHORT).show();
             }
         }
     });
     myView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword="+ URLEncoder.encode(sp) +"&page=1&count=5";

             mPresenter.onStartRequest(url);

             myAdapter = new MyAdapter(MainActivity.this, mList);
             recyclerView.setAdapter(myAdapter);
             myAdapter.setsOnItemClickListener(new MyAdapter.setOnItemClickListener() {
                 @Override
                 public void onClick(int position) {
                     Intent intent = new Intent(MainActivity.this, JsActivity.class);
                     startActivity(intent);
                 }
             });
         }
     });

    }

    @Override
    protected void initViwe() {
        editText = findViewById(R.id.edit);
        imageView = findViewById(R.id.image);
        myView = findViewById(R.id.myView);
        recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int providerLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(String json) {
        java.util.List<Bean.ResultBean> result = new Gson().fromJson(json, Bean.class).getResult();
        mList.clear();
        mList.addAll(result);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String error) {

    }
}
