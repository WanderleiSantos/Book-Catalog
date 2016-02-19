package com.wanderlei.bookcatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.model.entity.Book;

/**
 * Created by wanderlei on 12/02/16.
 */
public class BookActivity extends AppCompatActivity {

    private static final String INTENT_KEY_BOOK = "intent_key_book";
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_book);


        Book book = getIntent().getParcelableExtra(INTENT_KEY_BOOK);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), book.getVolumeInfo().getImage().getThumbnail());
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(book.getVolumeInfo().getTitle());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        TextView textview_name = (TextView) findViewById(R.id.textview_name);
        textview_name.setText(book.getVolumeInfo().getTitle());

        TextView textview_description = (TextView) findViewById(R.id.textview_description);
        textview_description.setText(book.getVolumeInfo().getDescription());

        final ImageView imageview_book = (ImageView) findViewById(R.id.imageview_book);
        if (book.getVolumeInfo().getImage() != null && book.getVolumeInfo().getImage().getSmallThumbnail() != null) {
            Picasso.with(this).load(book.getVolumeInfo().getImage().getSmallThumbnail()).placeholder(R.drawable.noimagebook).into(imageview_book);
        } else {
            imageview_book.setImageDrawable(this.getResources().getDrawable(R.drawable.noimagebook));
        }


        final ImageView image = (ImageView) findViewById(R.id.imageview_top);
        Picasso.with(this).load(book.getVolumeInfo().getImage().getThumbnail()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });

    }


    public static Intent newIntent(Context context, Book book) {
        Intent intent = new Intent(context, BookActivity.class);
        intent.putExtra(INTENT_KEY_BOOK, book);

        return intent;
    }

    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_material_dark);
        int primary = getResources().getColor(R.color.colorPrimary);

        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));

        updateBackground( palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground( Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorAccent));

    }

}
