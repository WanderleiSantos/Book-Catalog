package com.wanderlei.bookcatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskLoadBooks;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.activity.BookActivity;
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
    private ListBookAdapter bookAdapter;

    private static final String MYBOOKLIST_KEY = "booklist_key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);

        bookAdapter = new ListBookAdapter(getActivity());
        listView = (ListView) view.findViewById(R.id.listview_books);
        listView.setAdapter(bookAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) parent.getAdapter().getItem(position);
                startActivity(BookActivity.newIntent(getActivity(), book));
            }
        });

        if (savedInstanceState != null){
            Book[] bookArray = (Book[]) savedInstanceState.getParcelableArray(MYBOOKLIST_KEY);
            if (bookArray != null){
                bookList = Arrays.asList(bookArray);
                bookAdapter.setBooks(bookList);
            } else {
                new AsyncTaskLoadBooks(this, getActivity()).execute();
            }
        } else {
            new AsyncTaskLoadBooks(this, getActivity()).execute();
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

    @Override
    public void onUpcomingMoviesLoaded(List<Book> listBooks) {
        bookAdapter.setBooks(listBooks);
        this.bookList = listBooks;
    }
}
