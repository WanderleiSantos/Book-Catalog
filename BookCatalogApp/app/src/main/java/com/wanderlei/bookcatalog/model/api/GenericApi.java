package com.wanderlei.bookcatalog.model.api;

import android.content.Context;

import com.wanderlei.bookcatalog.model.api.asynctask.ApiResultListner;

/**
 * Created by wanderlei on 25/02/16.
 */
public  abstract class GenericApi implements AsyncService{
    private Context context;

    private ApiResultListner listener;

    public GenericApi(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public ApiResultListner getServiceResultListener() {
        return listener;
    }

    public void setServiceResultListener(ApiResultListner listener) {
        this.listener = listener;
    }

    public void verifyServiceResultListener() {
        if (getServiceResultListener() == null) {
            throw new RuntimeException("Before invoke this method, you must set an ApiResultListener instance");
        }
    }
}
