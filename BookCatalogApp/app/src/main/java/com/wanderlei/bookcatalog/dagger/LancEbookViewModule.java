package com.wanderlei.bookcatalog.dagger;

import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.presenter.LancBookPresenter;
import com.wanderlei.bookcatalog.view.LancBookView;
import com.wanderlei.bookcatalog.view.fragment.ListEbookFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 28/02/16.
 */
@Module(injects = ListEbookFragment.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class LancEbookViewModule {

    private LancBookView view;

    public LancEbookViewModule(LancBookView view) {
        this.view = view;
    }

    @Provides
    public LancBookPresenter provideLancBookPresenter(SearchBookApi searchBookApi){
        return new LancBookPresenter(view, searchBookApi);
    }

}
