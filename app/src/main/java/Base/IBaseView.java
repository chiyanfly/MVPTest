package Base;

public interface IBaseView<P extends IBasePresenter> {

    P createMainPresenter();
}
