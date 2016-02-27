package com.wanderlei.bookcatalog.dagger;

import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.presenter.LancBookPresenter;
import com.wanderlei.bookcatalog.presenter.SearchBookPresenter;
import com.wanderlei.bookcatalog.view.LancBookView;
import com.wanderlei.bookcatalog.view.fragment.ListBookFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 26/02/16.
 */
@Module(injects = ListBookFragment.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class LancBookViewModule {

    private LancBookView view;

    public LancBookViewModule(LancBookView view) {
        this.view = view;
    }

    @Provides
    public LancBookPresenter provideLancBookPresenter(SearchBookApi searchBookApi){
        return new LancBookPresenter(view, searchBookApi);
    }
}
