package com.wanderlei.bookcatalog.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 23/02/16.
 */
@Module(library = true)
public class AppModule {

    private  static Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    public AppModule() {
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}
