package com.wanderlei.bookcatalog.presenter;

import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.model.api.asynctask.ApiResultListner;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.SearchBookView;

import java.util.List;

/**
 * Created by wanderlei on 25/02/16.
 */
public class SearchBookPresenter {

    private SearchBookView searchBookView;
    private SearchBookApi searchBookApi;

    public SearchBookPresenter(SearchBookView searchBookView, SearchBookApi searchBookApi) {
        this.searchBookView = searchBookView;
        this.searchBookApi = searchBookApi;
    }

    public void searchByName(String name){

        searchBookView.showLoading();
        searchBookApi.setServiceResultListener(new ApiResultListner() {
            @Override
            public void onResult(Object object) {

                List<Book> books = (List<Book>) object;
                if (books.size() > 0){
                    searchBookView.showBook(books);
                } else {
                    searchBookView.errorBookNotFound();
                }
                searchBookView.dismissLoading();
            }

            @Override
            public void onException(Exception exception) {
                searchBookView.errorToListBooks();
                searchBookView.dismissLoading();
            }
        });

        searchBookApi.searchByName(name);
    }
}
