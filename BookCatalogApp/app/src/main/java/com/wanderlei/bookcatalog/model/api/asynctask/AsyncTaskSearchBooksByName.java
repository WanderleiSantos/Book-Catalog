package com.wanderlei.bookcatalog.model.api.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
 * Created by wanderlei on 20/02/16.
 */
public class AsyncTaskSearchBooksByName extends AsyncTask<Void, Void, List<Book>> {

    public static final String API = "https://www.googleapis.com/books/v1/";
    private static final int LIMIT = 20;

    private  List<Book> books;
    private BookLoadedListener myComponent;
    private ProgressDialog progressDialog;
    private String nome;
    private Context context;



    public AsyncTaskSearchBooksByName(BookLoadedListener myComponent, String nome, Context context) {
        this.nome = nome;
        this.myComponent = myComponent;
        this.context = context;
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
        } finally {
            closeProgress();
        }

        return books;
    }

    @Override
    protected void onPreExecute() {
        openProgress();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Book> listMovies) {
        if (myComponent != null) {
            myComponent.onUpcomingMoviesLoaded(listMovies);
        }
    }

    public void openProgress(){
        try{
            progressDialog = ProgressDialog.show(context, "", "Aguarde");
        }catch (Throwable e){
            Log.e("BookAPP", e.getMessage(), e);
        }
    }

    public void closeProgress(){
        try{
            if (progressDialog != null){
                progressDialog.dismiss();
            }
        }catch (Throwable e){
            Log.e("BookAPP", e.getMessage(), e);
        }
    }
}
