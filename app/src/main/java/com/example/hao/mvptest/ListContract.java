package com.example.hao.mvptest;

import java.util.ArrayList;

import Base.IBasePresenter;
import Base.IBaseView;


public interface ListContract {
    interface IView extends IBaseView<IPresenter> {
        void settextlist(ArrayList<String> referencePoints);

        void notifytextAdded();

        void notifytextModified(int position);

        void notifytextRemoved(int position);
    }

    interface IPresenter extends IBasePresenter<IView> {

        void onrefreshlist();
        void onAddtext(String text);

        void onRemovetext(int position);

        void onModifytext(int position, String text);
    }
}
