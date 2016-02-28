package com.wanderlei.bookcatalog.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.wanderlei.bookcatalog.BookCatalogApplication;
import com.wanderlei.bookcatalog.R;
import com.wanderlei.bookcatalog.dagger.MainActivityViewModule;
import com.wanderlei.bookcatalog.view.MainActivityView;
import com.wanderlei.bookcatalog.view.fragment.ListBookFragment;
import com.wanderlei.bookcatalog.view.fragment.ListEbookFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wanderlei on 28/01/16.
 */
public class MainActivity extends AppCompatActivity implements MainActivityView{

    public static final String API = "https://www.googleapis.com/books/v1/volumes?q=colecionador&key=";

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.tabs)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((BookCatalogApplication) getApplication()).getObjectGraph().plus(new MainActivityViewModule(this)).inject(this);

        viewPager.setAdapter(new PagerAdapter());

        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.drawer_ebooks) {
                    drawerLayout.closeDrawers();
                    viewPager.setCurrentItem(1);
                    item.setChecked(true);
                } else if (item.getItemId() == R.id.drawer_lancamentos) {
                    drawerLayout.closeDrawers();
                    viewPager.setCurrentItem(0);
                    item.setChecked(true);
                }

                return true;
            }
        });


        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    default:
                    case 0:
                        navigationView.getMenu().findItem(R.id.drawer_lancamentos).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.drawer_ebooks).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
                startActivity(SearchBookActivity.newIntent(MainActivity.this, query));
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
                    return new ListEbookFragment();
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
                    return getString(R.string.text_lançamentos_ebooks);
            }
        }
    }

}
