package com.wanderlei.bookcatalog.presenter;

import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.LancBookView;

import java.util.List;

/**
 * Created by wanderlei on 26/02/16.
 */
public class LancBookPresenter {

    private LancBookView view;
    private SearchBookApi api;

    public LancBookPresenter(LancBookView view, SearchBookApi api) {
        this.view = view;
        this.api = api;
    }

    public void loadLancBooks(){
        view.showLoading();
        api.setServiceResultListener(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                view.showBook((List<Book>) object);
                view.dismissLoading();
            }

            @Override
            public void onException(Exception exception) {
                view.dismissLoading();
                view.errorToListBooks();
            }
        });

        api.searchLanc();
    }

    public void loadLancEbooks(){
        view.showLoading();
        api.setServiceResultListener(new ApiResultListner() {
            @Override
            public void onResult(Object object) {
                view.showBook((List<Book>) object);
                view.dismissLoading();
            }

            @Override
            public void onException(Exception exception) {
                view.dismissLoading();
                view.errorToListBooks();
            }
        });

        api.searchEbooks();
    }
}
