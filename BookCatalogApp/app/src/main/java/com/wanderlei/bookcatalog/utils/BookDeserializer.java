package com.wanderlei.bookcatalog.utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.wanderlei.bookcatalog.model.entity.Book;

import java.lang.reflect.Type;

/**
 * Created by wanderlei on 13/02/16.
 */
public class BookDeserializer implements JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement book = json.getAsJsonObject();
        if (json.getAsJsonObject().get("book") != null){
            book = json.getAsJsonObject().get("book");
        }
        return (new Gson().fromJson(book, Book.class));
    }
}
