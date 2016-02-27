package com.wanderlei.bookcatalog.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.bookcatalog.BookCatalogApplication;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.dagger.LancBookViewModule;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.presenter.LancBookPresenter;
import com.wanderlei.bookcatalog.view.LancBookView;
import com.wanderlei.bookcatalog.view.activity.BookActivity;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;
import com.wanderlei.bookcatalog.view.listviewadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 12/02/16.
 */
public class ListBookFragment extends Fragment implements LancBookView, SwipeRefreshLayout.OnRefreshListener {


    private static final String MYBOOKLIST_KEY = "booklist_key";

    @Inject
    LancBookPresenter presenter;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    @Bind(R.id.swipeBooksHits)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recyclerview_book)
    RecyclerView recyclerView;

    private List<Book> bookList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        ButterKnife.bind(this, view);
        ((BookCatalogApplication) getActivity().getApplication()).getObjectGraph().plus(new LancBookViewModule(this)).inject(this);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(MYBOOKLIST_KEY) != null){
            bookList = savedInstanceState.getParcelableArrayList(MYBOOKLIST_KEY);
            showBook(bookList);
        } else{
            presenter.loadLancBooks();
        }

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (bookList != null){
            outState.putParcelableArrayList(MYBOOKLIST_KEY, new ArrayList<>(bookList));
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onRefresh() {
        presenter.loadLancBooks();
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void errorToListBooks() {
        Toast.makeText(getActivity(), "Nenhum livro encontrado.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBook(List<Book> books) {
        this.bookList = books;
        recyclerView.setAdapter(new ListBookAdapter(books, new OnItemClickListener<Book>() {
            @Override
            public void onClick(Book book) {
                startActivity(BookActivity.newIntent(getActivity(), book));
            }
        }));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void errorBookNotFound() {
        Toast.makeText(getActivity(), "Nenhum livro encontrado.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
