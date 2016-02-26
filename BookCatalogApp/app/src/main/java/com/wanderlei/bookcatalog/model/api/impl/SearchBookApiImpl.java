package com.wanderlei.bookcatalog.model.api.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.bookcatalog.model.api.BookAPI;
import com.wanderlei.bookcatalog.model.api.GenericApi;
import com.wanderlei.bookcatalog.model.api.SearchBookApi;
import com.wanderlei.bookcatalog.model.api.asynctask.impl.AsyncTaskSearchBooksByName;

/**
 * Created by wanderlei on 25/02/16.
 */
public class SearchBookApiImpl extends GenericApi implements SearchBookApi {

    private AsyncTaskSearchBooksByName asyncTaskSearchBooksByName;
    private BookAPI bookAPI;

    public SearchBookApiImpl(Context context, BookAPI bookAPI) {
        super(context);
        this.bookAPI = bookAPI;
    }

    @Override
    public void searchByName(String name) {
        verifyServiceResultListener();
        asyncTaskSearchBooksByName = new AsyncTaskSearchBooksByName(getContext(), bookAPI);
        asyncTaskSearchBooksByName.setApiResultListner(getServiceResultListener());
        asyncTaskSearchBooksByName.execute(name);
    }

    @Override
    public void cancelAllService() {
        if(asyncTaskSearchBooksByName != null && asyncTaskSearchBooksByName.getStatus() == AsyncTask.Status.RUNNING) {
            asyncTaskSearchBooksByName.cancel(true);
        }
    }
}