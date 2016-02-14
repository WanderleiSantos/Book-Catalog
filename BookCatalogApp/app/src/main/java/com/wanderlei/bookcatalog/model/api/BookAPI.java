package com.wanderlei.bookcatalog.model.api;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by wanderlei on 14/02/16.
 */
public interface BookAPI {

    //volumes?q=quilting
    @GET("volumes?q=quilting")
    Call<List<Book>> getBooks();

}
