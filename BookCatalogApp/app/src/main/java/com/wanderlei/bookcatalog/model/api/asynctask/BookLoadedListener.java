package com.wanderlei.bookcatalog.model.api.asynctask;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

/**
 * Created by wanderlei on 16/02/16.
 */
public interface BookLoadedListener {
    public void onUpcomingMoviesLoaded(List<Book> listBooks);
}
