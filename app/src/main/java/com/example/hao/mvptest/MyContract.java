package com.example.hao.mvptest;

import Base.IBasePresenter;
import Base.IBaseView;

public interface MyContract {

    interface IlogicView extends IBaseView<IlogicPresenter> {

        void showtoast(String mes);

    }


    interface IlogicPresenter extends IBasePresenter<IlogicView> {
    void onSignIn(String username,String password);
    void onPasswordforgot();
    }


}
