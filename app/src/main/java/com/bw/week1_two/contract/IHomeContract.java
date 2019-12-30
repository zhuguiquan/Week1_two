package com.bw.week1_two.contract;

import com.bw.week1_two.model.bean.Bean;

/**
 * DateTime:2019/12/30 0030
 * author:朱贵全(Administrator)
 * function:
 */
public interface IHomeContract  {
    interface IView{
        void onHomeSuccess(Bean bean);
        void onHomeFailure(Throwable throwable);

    }
    interface IPresenter{
        void getHomeData();

    }
    interface IModel{
        void getHomeData(IModelCallback iModelCallback);
        interface IModelCallback{
            void onHomeSuccess(Bean bean);
            void onHomeFailure(Throwable throwable);

        }
    }
}
