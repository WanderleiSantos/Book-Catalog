package com.wanderlei.bookcatalog.model.api.asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

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
 * Created by wanderlei on 21/02/16.
 */
public class AsyncTaskSearchByNameAuthor extends AsyncTask<Void, Void, List<Book>> {

    public static final String API = "https://www.googleapis.com/books/v1/";
    private  List<Book> books;
    private BookLoadedListener myComponent;
    private String nome;
    private ProgressBar progressBar;

    public AsyncTaskSearchByNameAuthor(BookLoadedListener myComponent, String nome, ProgressBar progressBar) {
        this.myComponent = myComponent;
        this.nome = nome;
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

        final Call<List<Book>> listCallBooks = bookAPI.getBooksByAuthor(nome);
        try{

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
    protected void onPostExecute(List<Book> books) {
        if (myComponent != null){
            myComponent.onUpcomingMoviesLoaded(books);
        }

        progressBar.setVisibility(View.INVISIBLE);
    }
}
