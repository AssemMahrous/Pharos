package android.pharos.io.pharos.ui.base;

/**
 * Created by assem on 1/28/2018.
 */

public interface MvpPresenter <V extends MvpView> {
    void onAttach(V mvpView);
}
