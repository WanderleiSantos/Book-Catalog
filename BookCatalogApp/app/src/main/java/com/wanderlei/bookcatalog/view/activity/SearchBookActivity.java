package com.wanderlei.bookcatalog.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wanderlei.bookcatalog.BookCatalogApplication;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.dagger.SearchBookViewModule;
import com.wanderlei.bookcatalog.model.api.asynctask.impl.AsyncTaskSearchBooksByName;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.presenter.SearchBookPresenter;
import com.wanderlei.bookcatalog.view.SearchBookView;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;
import com.wanderlei.bookcatalog.view.listviewadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 19/02/16.
 */
public class SearchBookActivity extends AppCompatActivity implements SearchBookView {

    private final String BUNDLE_KEY_BOOK = "bundle_key_book";
    private static final String BUNDLE_KEY_NOME = "bundle_key_nome";

    @Inject
    SearchBookPresenter bookPresenter;

    @Bind(R.id.recyclerview_book)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;

    private List<Book> bookList;
    private String nome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);
        ButterKnife.bind(this);
        ((BookCatalogApplication) getApplication()).getObjectGraph().plus(new SearchBookViewModule(this)).inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null){
            if (savedInstanceState.getString(BUNDLE_KEY_NOME) != null){
                nome = savedInstanceState.getString(BUNDLE_KEY_NOME);
                getSupportActionBar().setSubtitle(nome);
            }
            if (savedInstanceState.getParcelableArrayList(BUNDLE_KEY_BOOK) != null){
                bookList = savedInstanceState.getParcelableArrayList(BUNDLE_KEY_BOOK);
                showBook(bookList);
            } else if (nome != null){
                bookPresenter.searchByName(nome);
            }
        } else {
            nome = getIntent().getStringExtra(BUNDLE_KEY_NOME);
            getSupportActionBar().setSubtitle(nome);
            bookPresenter.searchByName(nome);
        }

    }

    public static Intent newIntent(Context context, String nome) {
        Intent intent = new Intent(context, SearchBookActivity.class);
        intent.putExtra(BUNDLE_KEY_NOME, nome);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_books, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setQueryHint(getString(R.string.buscarbooks));
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        menu.findItem(R.id.action_search).expandActionView();

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(bookList != null) {
            outState.putParcelableArrayList(BUNDLE_KEY_BOOK, new ArrayList<Parcelable>(bookList));
        }
        if(nome != null) {
            outState.putString(BUNDLE_KEY_NOME, nome);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void startActivity(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            nome = intent.getStringExtra(SearchManager.QUERY);
            bookPresenter.searchByName(nome);
            getSupportActionBar().setSubtitle(nome);
        } else {
            super.startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void errorToListBooks() {
        Toast.makeText(SearchBookActivity.this, "Erro ao carregar a listagem de livros.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBook(List<Book> books) {
        this.bookList = books;
        recyclerView.setAdapter(new ListBookAdapter(books, new OnItemClickListener<Book>() {
            @Override
            public void onClick(Book book) {
                startActivity(BookActivity.newIntent(SearchBookActivity.this, book));
            }
        }));
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchBookActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void errorBookNotFound() {
        Toast.makeText(SearchBookActivity.this, "Nenhum livro encontrado.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
