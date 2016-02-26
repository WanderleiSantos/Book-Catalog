package com.wanderlei.bookcatalog.view;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

/**
 * Created by wanderlei on 25/02/16.
 */
public interface SearchBookView {

    void errorToListBooks();

    void showBook(List<Book> books);

    void errorBookNotFound();

    void showLoading();

    void dismissLoading();

}
