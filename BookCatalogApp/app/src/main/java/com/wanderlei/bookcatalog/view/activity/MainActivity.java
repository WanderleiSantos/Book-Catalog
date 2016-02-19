package com.wanderlei.bookcatalog.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.view.fragment.ListBookFragment;

/**
 * Created by wanderlei on 28/01/16.
 */
public class MainActivity extends AppCompatActivity {

    public static final String API = "https://www.googleapis.com/books/v1/volumes?q=colecionador&key=";
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.drawer_authors) {
                    drawerLayout.closeDrawers();
                    viewPager.setCurrentItem(1);
                  //  startActivity(AuthorActivity.newIntent(MainActivity.this));
                } else  if (item.getItemId() == R.id.drawer_lancamentos) {
                    drawerLayout.closeDrawers();
                    viewPager.setCurrentItem(0);
                    //  startActivity(AuthorActivity.newIntent(MainActivity.this));
                }

                return true;
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, BookActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                case 0:
                    return new ListBookFragment();
                case 1:
                    return new ListBookFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                default:
                case 0:
                    return getString(R.string.text_lançamentos);
                case 1:
                    return getString(R.string.text_authors);
            }
        }
    }

}
