package com.bw.week1_two.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.week1_two.R;
import com.bw.week1_two.base.BaseActivity;
import com.bw.week1_two.contract.IHomeContract;
import com.bw.week1_two.model.bean.Bean;
import com.bw.week1_two.presenter.HomePresenter;
import com.bw.week1_two.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {


    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        mpresenter.getHomeData();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onHomeSuccess(Bean bean) {
        List<Bean.ResultBean> result = bean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        MyAdapter myAdapter = new MyAdapter(result);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        rv.setAdapter(myAdapter);


    }

    @Override
    public void onHomeFailure(Throwable throwable) {

    }


}
