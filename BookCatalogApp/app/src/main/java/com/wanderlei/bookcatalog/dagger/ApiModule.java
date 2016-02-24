package com.wanderlei.bookcatalog.dagger;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.ItemTypeAdapterFactory;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wanderlei on 23/02/16.
 */
@Module(library = true, includes = AppModule.class)
public class ApiModule {


    public static final String API = "https://www.googleapis.com/books/v1/";

    @Provides
    public Retrofit provideRetrofit(Context context){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory()).create();
        return new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    public BookAPI provideBookAPI(Context context){
        return provideRetrofit(context).create(BookAPI.class);
    }

}
