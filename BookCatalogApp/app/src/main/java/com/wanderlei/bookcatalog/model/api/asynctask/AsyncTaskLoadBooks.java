package com.wanderlei.bookcatalog.model.api.asynctask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.ItemTypeAdapterFactory;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wanderlei on 16/02/16.
 */
public class AsyncTaskLoadBooks extends AsyncTask<Void, Void, List<Book>> {

    public static final String API = "https://www.googleapis.com/books/v1/";
    private  List<Book> books;
    private BookLoadedListener myComponent;


    public AsyncTaskLoadBooks(BookLoadedListener myComponent) {
        this.myComponent = myComponent;
    }

    @Override
    protected List<Book> doInBackground(Void... params) {


        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BookAPI bookAPI = retrofit.create(BookAPI.class);


        final Call<List<Book>> listCallBooks = bookAPI.getBooks();
        try {

            Response<List<Book>> response = listCallBooks.execute();
            books = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    protected void onPostExecute(List<Book> listMovies) {
        if (myComponent != null) {
            myComponent.onUpcomingMoviesLoaded(listMovies);
        }
    }
}
