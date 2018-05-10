package android.pharos.io.pharos.ui.main;

import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.ui.base.BasePresenter;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    public MainPresenter(AppDataManager dataManager) {
        super(dataManager);
    }
}
