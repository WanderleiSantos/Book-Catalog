package com.wanderlei.bookcatalog.model.api;

import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by wanderlei on 14/02/16.
 */
public interface BookAPI {

    @GET("volumes?q=quilting&maxResults=40")
    Call<List<Book>> getBooksNewest();

    @GET("volumes")
    Call<List<Book>> getBooksByName(@Query("q") String nome, @Query("maxResults") int limit);

    @GET("")
    Call<List<Book>> getBooksByAuthor();

}
