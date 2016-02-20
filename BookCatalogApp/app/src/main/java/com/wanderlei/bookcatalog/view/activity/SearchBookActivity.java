package com.wanderlei.bookcatalog.view.activity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.ProgressBar;

import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskLoadBooks;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskSearchBooksByName;
import com.wanderlei.bookcatalog.model.api.asynctask.BookLoadedListener;
import com.wanderlei.bookcatalog.model.entity.Book;
import com.wanderlei.bookcatalog.view.listviewadapter.ListBookAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanderlei on 19/02/16.
 */
public class SearchBookActivity extends AppCompatActivity implements BookLoadedListener {

    private static final String INTENT_KEY_NOME = "intent_key_nome";
    private final String BUNDLE_KEY_BOOK = "bundle_key_book";

    private ListView listViewBooks;
    private List<Book> bookList;
    private ListBookAdapter bookAdapter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbook);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        bookAdapter = new ListBookAdapter(this);

        String nome = getIntent().getStringExtra(INTENT_KEY_NOME);
        getSupportActionBar().setSubtitle(getString(R.string.searchbookActivityactivity_substitle_todos));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null){
            Book[] bookArray = (Book[]) savedInstanceState.getParcelableArray(BUNDLE_KEY_BOOK);
            if (bookArray != null){
                bookList = Arrays.asList(bookArray);
                bookAdapter.setBooks(bookList);
            } else {
                new AsyncTaskSearchBooksByName(SearchBookActivity.this, nome, progressBar).execute();
            }
        } else {
            new AsyncTaskSearchBooksByName(SearchBookActivity.this, nome, progressBar).execute();
        }


        listViewBooks = (ListView) findViewById(R.id.listview_books);
        listViewBooks.setAdapter(bookAdapter);
        listViewBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = (Book) parent.getAdapter().getItem(position);
                startActivity(BookActivity.newIntent(SearchBookActivity.this, book));
            }
        });



    }

    public static Intent newIntent(Context context, @Nullable String nome) {
        Intent intent = new Intent(context, SearchBookActivity.class);
        intent.putExtra(INTENT_KEY_NOME, nome);

        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_books, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.buscarbooks));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new AsyncTaskSearchBooksByName(SearchBookActivity.this, query, progressBar).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (bookList != null){
            outState.putParcelableArray(BUNDLE_KEY_BOOK, bookList.toArray(new Book[bookList.size()]));
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpcomingMoviesLoaded(List<Book> listBooks) {
        bookAdapter.setBooks(listBooks);
        this.bookList = listBooks;
    }
}
