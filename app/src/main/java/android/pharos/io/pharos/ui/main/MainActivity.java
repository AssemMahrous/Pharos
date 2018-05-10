package android.pharos.io.pharos.ui.main;

import android.os.Bundle;
import android.pharos.io.pharos.MvpApp;
import android.pharos.io.pharos.R;
import android.pharos.io.pharos.data.AppDataManager;
import android.pharos.io.pharos.ui.base.BaseActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends BaseActivity implements MainMvpView {
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppDataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        presenter = new MainPresenter(dataManager);
        presenter.onAttach(this);

    }

}
