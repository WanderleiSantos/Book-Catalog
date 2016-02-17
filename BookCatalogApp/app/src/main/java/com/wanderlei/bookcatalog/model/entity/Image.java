package com.wanderlei.bookcatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanderlei on 12/02/16.
 */
public class Image implements Parcelable {

    private String smallThumbnail;
    private String thumbnail;

    public Image(String smallThumbnail, String thumbnail) {
        this.smallThumbnail = smallThumbnail;
        this.thumbnail = thumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.smallThumbnail);
        dest.writeString(this.thumbnail);
    }

    protected Image(Parcel in) {
        this.smallThumbnail = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
