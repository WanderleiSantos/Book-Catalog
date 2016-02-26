package com.wanderlei.bookcatalog.model.api.asynctask;

/**
 * Created by wanderlei on 25/02/16.
 */
public interface ApiResultListner {

    public void onResult(Object object);

    public void onException(Exception exception);


}
