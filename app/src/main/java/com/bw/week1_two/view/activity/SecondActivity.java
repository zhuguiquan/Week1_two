package com.bw.week1_two.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.week1_two.R;
import com.bw.week1_two.base.BaseActivity;
import com.bw.week1_two.base.BasePresenter;
import com.bw.week1_two.model.bean.BusBean;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity {


    @BindView(R.id.edt)
    EditText edt;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.bus_str)
    Button busStr;
    @BindView(R.id.bus_bean)
    Button busBean;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        imageview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(imageview, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(SecondActivity.this, "成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });

    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }

    @OnClick({R.id.bt1, R.id.bus_str, R.id.bus_bean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                String s = edt.getText().toString();
              //  Bitmap image = CodeUtils.createImage(s, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                Bitmap image = CodeUtils.createImage(s, 400, 400, null);
                imageview.setImageBitmap(image);
                break;
            case R.id.bus_str:
                EventBus.getDefault().post("这是字符穿的传递");
                break;
            case R.id.bus_bean:
                EventBus.getDefault().post(new BusBean("独孤无敌",88));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getString(String str){
    Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBean(BusBean busBean){
        Toast.makeText(this, ""+busBean.getName(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
