package com.wanderlei.bookcatalog.model.api.asynctask.exception;

/**
 * Created by wanderlei on 25/02/16.
 */
public class ErrorUnauthorizedException  extends Exception{

    public ErrorUnauthorizedException() {
    }

    public ErrorUnauthorizedException(String error) {
        super(error);
    }
}
