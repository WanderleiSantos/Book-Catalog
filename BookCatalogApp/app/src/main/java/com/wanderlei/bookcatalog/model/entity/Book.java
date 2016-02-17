package com.wanderlei.bookcatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by wanderlei on 28/01/16.
 */
public class Book implements Parcelable {

    private String Kind;
    private String id;
    private VolumeInfo volumeInfo;


    public Book(String kind, String id, Image image, VolumeInfo volumeInfo) {
        Kind = kind;
        this.id = id;
        this.volumeInfo = volumeInfo;
    }



    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Kind);
        dest.writeString(this.id);
        dest.writeParcelable((Parcelable) this.volumeInfo, 0);
    }

    protected Book(Parcel in) {
        this.Kind = in.readString();
        this.id = in.readString();
        this.volumeInfo = in.readParcelable(VolumeInfo.class.getClassLoader());
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
