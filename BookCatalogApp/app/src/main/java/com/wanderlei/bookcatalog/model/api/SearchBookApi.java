package com.wanderlei.bookcatalog.model.api;

/**
 * Created by wanderlei on 25/02/16.
 */
public interface SearchBookApi  extends AsyncService{

    void searchByName(String name);

    void searchLanc();
}
