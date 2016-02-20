package com.wanderlei.bookcatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskLoadBooks;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.activity.BookActivity;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookFragment extends Fragment implements BookLoadedListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView listView;
    private List<Book> bookList;
    private ListBookAdapter bookAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String MYBOOKLIST_KEY = "booklist_key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeBooksHits);
        mSwipeRefreshLayout.setOnRefreshListener(this);

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
                new AsyncTaskLoadBooks(this, progressBar).execute();
            }
        } else {
            new AsyncTaskLoadBooks(this, progressBar).execute();
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

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        bookAdapter.setBooks(listBooks);
        this.bookList = listBooks;
    }

    @Override
    public void onRefresh() {
        new AsyncTaskLoadBooks(this, progressBar).execute();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
