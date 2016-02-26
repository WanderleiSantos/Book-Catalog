package com.wanderlei.bookcatalog.model.api.asynctask.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.ItemTypeAdapterFactory;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.bookcatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.api.asynctask.exception.RequestErrorException;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wanderlei on 20/02/16.
 */
public class AsyncTaskSearchBooksByName  extends BaseAsyncTask<String, Void, List<Book>> {

    private BookAPI bookAPI;
    private static final int LIMIT = 20;

    public AsyncTaskSearchBooksByName(Context context, BookAPI bookAPI) {
        super(context);
        this.bookAPI = bookAPI;
    }

    @Override
    protected AsyncTaskResult<List<Book>> doInBackground(String... params){
        try {
            Response<List<Book>> listResponse = bookAPI.getBooksByName(params[0].trim(), LIMIT).execute();
            switch (listResponse.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(listResponse.body());
                default:
                    return new AsyncTaskResult<>(new RequestErrorException());
            }
        } catch (IOException e){
            return new AsyncTaskResult<>(new RequestErrorException());
        }
    }

    /*  public static final String API = "https://www.googleapis.com/books/v1/";
    private static final int LIMIT = 20;

    private  List<Book> books;
    private BookLoadedListener myComponent;
    private String nome;
    private  ProgressBar progressBar;



    public AsyncTaskSearchBooksByName(BookLoadedListener myComponent, String nome, ProgressBar progressBar) {
        this.nome = nome;
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

        final Call<List<Book>> listCallBooks = bookAPI.getBooksByName(nome, LIMIT);
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

        progressBar.setVisibility(View.INVISIBLE);
    } */

}
