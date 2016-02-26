package com.wanderlei.bookcatalog.model.api.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.wanderlei.bookcatalog.R;

/**
 * Created by wanderlei on 25/02/16.
 */
public  abstract class BaseAsyncTask <PARAM, POGRESS, RETURN> extends AsyncTask<PARAM, POGRESS, AsyncTaskResult<RETURN>> {

    private Context context;
    private ApiResultListner apiResultListner;

    public BaseAsyncTask(Context context) {
        this.context = context;
    }

    public void setApiResultListner(ApiResultListner apiResultListner){
        this.apiResultListner = apiResultListner;
    }

    public Context getContext(){
        return  context;
    }

    public String getBaseUrl(){
        return context.getString(R.string.base_url_googlebooksapi);
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<RETURN> returnAsyncTaskResult) {
        if(returnAsyncTaskResult.getResult() != null){
            apiResultListner.onResult(returnAsyncTaskResult.getResult());
        } else if (returnAsyncTaskResult.getError() != null){
            apiResultListner.onException(returnAsyncTaskResult.getError());
        } else  {
            apiResultListner.onResult(returnAsyncTaskResult.getResult());
        }
    }
}
