package com.wanderlei.bookcatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.utils.BookDeserializer;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookFragment extends Fragment {

    private ListView listView;
    private List<Book> bookList;
    private List<Book> bookListJson;
    public static final String API = "https://www.googleapis.com/books/v1/";

    private static final String MYBOOKLIST_KEY = "booklist_key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);

        if (savedInstanceState != null){
            Book[] bookArray = (Book[]) savedInstanceState.getParcelableArray(MYBOOKLIST_KEY);
            if (bookArray != null){
                bookList = Arrays.asList(bookArray);
            } else {
                bookList = getBookList();
            }
        } else {
            bookList = getBookList();
        }

        listView = (ListView) view.findViewById(R.id.listview_books);
        listView.setAdapter(new ListBookAdapter(bookList, getActivity()));

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (bookList != null){
            outState.putParcelableArray(MYBOOKLIST_KEY, bookList.toArray(new Book[bookList.size()]));
        }
        super.onSaveInstanceState(outState);
    }

    private List<Book> getBookList(){

        Gson gson = new GsonBuilder().registerTypeAdapter(Book.class, new BookDeserializer()).create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        BookAPI bookAPI = retrofit.create(BookAPI.class);


        final Call<List<Book>> listCallBooks = bookAPI.getBooks("volumes?q=quilting");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bookListJson = listCallBooks.execute().body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//                try{
//                    List<Book>  books =  listCallBooks.execute().body();
//                    if (books != null) {
//                       for (Book book : books){
//                           bookListJson.add(book);
//                       }
//                    }
//
//                }catch (IOException e ){
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        return bookListJson;
    }
}
