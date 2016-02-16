package com.wanderlei.bookcatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskLoadBooks;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookFragment extends Fragment implements BookLoadedListener {

    private ListView listView;
    private List<Book> bookList;
    private List<Book> bookListJson = new ArrayList<>();
    private ListBookAdapter bookAdapter;


    public static final String API = "https://www.googleapis.com/books/v1/";

    private static final String MYBOOKLIST_KEY = "booklist_key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);

        bookAdapter = new ListBookAdapter(getActivity());
        listView = (ListView) view.findViewById(R.id.listview_books);
        listView.setAdapter(bookAdapter);

        if (savedInstanceState != null){
            Book[] bookArray = (Book[]) savedInstanceState.getParcelableArray(MYBOOKLIST_KEY);
            if (bookArray != null){
                bookList = Arrays.asList(bookArray);
            } else {
              //  bookList = getBookList();
                new AsyncTaskLoadBooks(this).execute();
            }
        } else {
            //bookList = getBookList();
            new AsyncTaskLoadBooks(this).execute();
        }

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

//        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .baseUrl(API)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        BookAPI bookAPI = retrofit.create(BookAPI.class);
//
//
//        final Call<List<Book>> listCallBooks = bookAPI.getBooks();
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    Response<List<Book>> response = listCallBooks.execute();
//                    List<Book> books = response.body();
//                    //List<Book> books = listCallBooks.execute().body();
//                    if (books != null) {
//                        for (Book b : books) {
//                            bookListJson.add(b);
//                        }
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        return bookListJson;
    }

    @Override
    public void onUpcomingMoviesLoaded(List<Book> listBooks) {
        bookAdapter.setBooks(listBooks);
    }
}
