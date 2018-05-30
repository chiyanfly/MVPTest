package com.example.hao.mvptest;

public class Mypresenter implements MyContract.IlogicPresenter {
    // il faut avoir le view
    private MyContract.IlogicView myview;

    @Override
    public void onSignIn(String username, String password) {

        if (username.equals("haoxu") && password.equals("haoxu")) {

            myview.showtoast("login success");
        } else {
            myview.showtoast("login  fail");
        }
    }

    @Override
    public void onPasswordforgot() {

    }

    @Override
    public void onAttachView(MyContract.IlogicView view) {
        myview = view;
    }

    @Override
    public void onDetachView() {
        myview = null;
    }
}
