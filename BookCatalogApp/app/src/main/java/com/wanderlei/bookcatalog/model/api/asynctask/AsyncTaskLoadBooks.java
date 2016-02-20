package com.wanderlei.bookcatalog.model.api.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.ItemTypeAdapterFactory;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.activity.MainActivity;

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
    private ProgressBar progressBar;

    public AsyncTaskLoadBooks(BookLoadedListener myComponent, ProgressBar progressBar) {
        this.myComponent = myComponent;
        this.progressBar = progressBar;
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


        final Call<List<Book>> listCallBooks = bookAPI.getBooksNewest();
        try {

            Response<List<Book>> response = listCallBooks.execute();
            books = response.body();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(List<Book> listMovies) {
        if (myComponent != null) {
            myComponent.onUpcomingMoviesLoaded(listMovies);
        }
        this.progressBar.setVisibility(View.INVISIBLE);
    }

}
