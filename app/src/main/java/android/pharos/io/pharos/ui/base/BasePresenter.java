package android.pharos.io.pharos.ui.base;


import android.pharos.io.pharos.data.AppDataManager;

/**
 * Created by assem on 1/28/2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    AppDataManager mDataManager;


    public BasePresenter(AppDataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public AppDataManager getDataManager() {
        return mDataManager;
    }
}
