package com.example.hao.mvptest;

import java.util.ArrayList;

import Base.IBasePresenter;

public class Listpresenter implements ListContract.IPresenter {


    ListContract.IView myview;
    ArrayList<String> textlist;


    @Override
    public void onrefreshlist() {

        textlist = new ArrayList<>();
        myview.settextlist(textlist);
    }

    @Override
    public void onAddtext(String text) {
        textlist.add(text);
        myview.notifytextAdded();
    }

    @Override
    public void onRemovetext(int position) {
            textlist.remove(position);
            myview.notifytextRemoved(position);
    }

    @Override
    public void onModifytext(int position, String text) {
            textlist.set(position,text);
            myview.notifytextModified(position);
    }

    @Override
    public void onAttachView(ListContract.IView view) {
        myview = view;
    }

    @Override
    public void onDetachView() {
        myview = null;
    }
}
