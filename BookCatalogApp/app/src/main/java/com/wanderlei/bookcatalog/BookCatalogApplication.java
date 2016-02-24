package com.wanderlei.bookcatalog;

import android.app.Application;

import com.wanderlei.bookcatalog.dagger.ApiModule;
import com.wanderlei.bookcatalog.dagger.AppModule;

import dagger.ObjectGraph;

/**
 * Created by wanderlei on 23/02/16.
 */
public class BookCatalogApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(
                new Object[]{
                        new AppModule(BookCatalogApplication.this),
                        new ApiModule(),
                }
        );
    }


    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
