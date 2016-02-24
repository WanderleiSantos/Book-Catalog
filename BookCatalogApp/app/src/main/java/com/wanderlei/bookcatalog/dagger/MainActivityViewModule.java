package com.wanderlei.bookcatalog.dagger;

import com.wanderlei.bookcatalog.presenter.MainActivityPresenter;
import com.wanderlei.bookcatalog.view.MainActivityView;
import com.wanderlei.bookcatalog.view.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 23/02/16.
 */
@Module(injects = MainActivity.class, includes = {ApiModule.class}, library = true)
public class MainActivityViewModule {

    private MainActivityView view;

    public MainActivityViewModule(MainActivityView view) {
        this.view = view;
    }

    @Provides
    public MainActivityPresenter providerMainActivityPresenter(){
        return new MainActivityPresenter(view);
    }
}
