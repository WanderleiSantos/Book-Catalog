package com.wanderlei.bookcatalog.model.api.asynctask.impl;

import android.content.Context;

import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.asynctask.AsyncTaskResult;
import com.wanderlei.bookcatalog.model.api.asynctask.BaseAsyncTask;
import com.wanderlei.bookcatalog.model.api.asynctask.exception.RequestErrorException;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.io.IOException;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by wanderlei on 28/02/16.
 */
public class AsyncTaskLoadLancEbook  extends BaseAsyncTask<String, Void, List<Book>> {

    private BookAPI bookAPI;

    public AsyncTaskLoadLancEbook(Context context, BookAPI bookAPI) {
        super(context);
        this.bookAPI = bookAPI;
    }

    @Override
    protected AsyncTaskResult<List<Book>> doInBackground(String... params){
        try {
            retrofit.Response<List<Book>> listResponse = bookAPI.getEbooks().execute();
            switch (listResponse.code()){
                case HTTP_OK:
                    return new AsyncTaskResult<>(listResponse.body());
                default:
                    return new AsyncTaskResult<>(new RequestErrorException());
            }
        } catch (IOException e){
            return new AsyncTaskResult<>(new RequestErrorException());
        }
    }
}
