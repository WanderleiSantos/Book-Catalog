package com.wanderlei.bookcatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.util.Arrays;

/**
 * Created by wanderlei on 12/02/16.
 */


public class BookActivity extends AppCompatActivity {

    private static final String INTENT_KEY_BOOK = "intent_key_book";
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Book book = getIntent().getParcelableExtra(INTENT_KEY_BOOK);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(book.getVolumeInfo().getTitle());


        final ImageView imageview_book = (ImageView) findViewById(R.id.imageview_top);
        if (book.getVolumeInfo().getImage() != null && book.getVolumeInfo().getImage().getThumbnail() != null) {
            Picasso.with(this).load(book.getVolumeInfo().getImage().getThumbnail()).placeholder(R.drawable.noimagebook).into(imageview_book);
        } else {
            imageview_book.setImageDrawable(this.getResources().getDrawable(R.drawable.noimagebook));
        }

        TextView textview_description = (TextView) findViewById(R.id.textview_description);
        textview_description.setText(book.getVolumeInfo().getDescription());

        TextView textview_publisher = (TextView) findViewById(R.id.textview_publisher);
        textview_publisher.setText(book.getVolumeInfo().getPublisher());

        TextView textview_publishedDate = (TextView) findViewById(R.id.textview_publishedDate);
        textview_publishedDate.setText(book.getVolumeInfo().getPublishedDate());

        TextView textview_printType = (TextView) findViewById(R.id.textview_printType);
        textview_printType.setText(book.getVolumeInfo().getPrintType());

        TextView textview_pageCount = (TextView) findViewById(R.id.textview_pageCount);
        textview_pageCount.setText(book.getVolumeInfo().getPageCount() + " páginas");

        TextView textview_author = (TextView) findViewById(R.id.textview_author);
        textview_author.setText(Arrays.toString(book.getVolumeInfo().getAuthor()));


    }

    public static Intent newIntent(Context context, Book book) {
        Intent intent = new Intent(context, BookActivity.class);
        intent.putExtra(INTENT_KEY_BOOK, book);

        return intent;
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


}
