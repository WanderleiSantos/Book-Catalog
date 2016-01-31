package com.wanderlei.bookcatalog.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wanderlei.bookcatalog.R;

/**
 * Created by wanderlei on 28/01/16.
 */
public class MainActivity extends AppCompatActivity {

    public static final String API = "https://www.googleapis.com/books/v1/volumes?q=colecionador&key=";
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   tvData = (TextView) findViewById(R.id.tv_data);
    }

}
