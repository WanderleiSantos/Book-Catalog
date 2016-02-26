package com.wanderlei.bookcatalog.model.api.asynctask.exception;

/**
 * Created by wanderlei on 25/02/16.
 */
public class RequestErrorException extends Exception {

    public RequestErrorException() {
    }

    public RequestErrorException(String error){
        super(error);
    }
}
