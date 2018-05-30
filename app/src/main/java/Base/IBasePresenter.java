package Base;

public interface IBasePresenter<V extends IBaseView > {

    void onAttachView(V view);
    void onDetachView();
}
