package com.wanderlei.bookcatalog.model.api;

import com.wanderlei.bookcatalog.model.api.asynctask.ApiResultListner;

/**
 * Created by wanderlei on 25/02/16.
 */
public interface AsyncService {

    ApiResultListner getServiceResultListener();

    void setServiceResultListener(ApiResultListner listener);

    void cancelAllService();
}
