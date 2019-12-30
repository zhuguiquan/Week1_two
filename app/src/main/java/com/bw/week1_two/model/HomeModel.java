package com.bw.week1_two.model;

import com.bw.week1_two.contract.IHomeContract;
import com.bw.week1_two.model.bean.Bean;
import com.bw.week1_two.util.NetUtil;
import com.google.gson.Gson;

/**
 * DateTime:2019/12/30 0030
 * author:朱贵全(Administrator)
 * function:
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(IModelCallback iModelCallback) {
        NetUtil.getInstance().getJsonGet("http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword=手机", new NetUtil.MyCallback() {
            @Override
            public void ongetJson(String json) {
                Bean bean = new Gson().fromJson(json, Bean.class);
                iModelCallback.onHomeSuccess(bean);

            }

            @Override
            public void onError(Throwable throwable) {
                iModelCallback.onHomeFailure(throwable);

            }
        });

    }
}
