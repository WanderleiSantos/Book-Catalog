package com.wanderlei.bookcatalog.view;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

/**
 * Created by wanderlei on 26/02/16.
 */
public interface LancBookView {

    void errorToListBooks();

    void showBook(List<Book> books);

    void errorBookNotFound();

    void showLoading();

    void dismissLoading();
}
