package com.wanderlei.bookcatalog.dagger;

import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.presenter.SearchBookPresenter;
import com.wanderlei.bookcatalog.view.SearchBookView;
import com.wanderlei.bookcatalog.view.activity.SearchBookActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanderlei on 25/02/16.
 */
@Module(injects = SearchBookActivity.class, includes = {AppModule.class, ApiModule.class}, library = true)
public class SearchBookViewModule {

    private SearchBookView searchBookView;

    public SearchBookViewModule(SearchBookView searchBookView) {
        this.searchBookView = searchBookView;
    }

    @Provides
    public SearchBookPresenter provideSearchBookPresenter(SearchBookApi searchBookApi){
        return new SearchBookPresenter(searchBookView, searchBookApi);
    }

}
