package com.wanderlei.bookcatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by wanderlei on 16/02/16.
 */
public class VolumeInfo implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("authors")
    private String[] author;

    private String publisher;

    @SerializedName("imageLinks")
    private Image image;

    private String publishedDate;

    @SerializedName("description")
    private String description;

    @SerializedName("categories")
    private String[] categories;

    private String printType;

    private int pageCount;


    public VolumeInfo(String title, String[] author, String publisher, Image image, String publishedDate, String description, String[] categories, String printType, int pageCount) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
        this.publishedDate = publishedDate;
        this.description = description;
        this.categories = categories;
        this.printType = printType;
        this.pageCount = pageCount;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeStringArray(this.author);
        dest.writeString(this.publisher);
        dest.writeParcelable(this.image, 0);
        dest.writeString(this.publishedDate);
        dest.writeString(this.description);
        dest.writeStringArray(this.categories);
        dest.writeString(this.printType);
        dest.writeInt(this.pageCount);
    }

    protected VolumeInfo(Parcel in) {
        this.title = in.readString();
        this.author = in.createStringArray();
        this.publisher = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.publishedDate = in.readString();
        this.description = in.readString();
        this.categories = in.createStringArray();
        this.printType = in.readString();
        this.pageCount = in.readInt();
    }

    public static final Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {
        public VolumeInfo createFromParcel(Parcel source) {
            return new VolumeInfo(source);
        }

        public VolumeInfo[] newArray(int size) {
            return new VolumeInfo[size];
        }
    };
}
