package com.wanderlei.bookcatalog.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by wanderlei on 28/01/16.
 */
public class Book implements Parcelable {

    private String Kind;
    private String id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private Date publishedDate;
    private String isbn_13;
    private String isbn_10;
    private int pageCount;
    private String printType;
    private String categories;
    private String smallThumbnail;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setIsbn_13(String isbn_13) {
        this.isbn_13 = isbn_13;
    }

    public void setIsbn_10(String isbn_10) {
        this.isbn_10 = isbn_10;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    private String thumbnail;

    public String getKind() {
        return Kind;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public String getIsbn_13() {
        return isbn_13;
    }

    public String getIsbn_10() {
        return isbn_10;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public String getCategories() {
        return categories;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Kind);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.author);
        dest.writeString(this.publisher);
        dest.writeLong(publishedDate != null ? publishedDate.getTime() : -1);
        dest.writeString(this.isbn_13);
        dest.writeString(this.isbn_10);
        dest.writeInt(this.pageCount);
        dest.writeString(this.printType);
        dest.writeString(this.categories);
        dest.writeString(this.smallThumbnail);
        dest.writeString(this.thumbnail);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.Kind = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.author = in.readString();
        this.publisher = in.readString();
        long tmpPublishedDate = in.readLong();
        this.publishedDate = tmpPublishedDate == -1 ? null : new Date(tmpPublishedDate);
        this.isbn_13 = in.readString();
        this.isbn_10 = in.readString();
        this.pageCount = in.readInt();
        this.printType = in.readString();
        this.categories = in.readString();
        this.smallThumbnail = in.readString();
        this.thumbnail = in.readString();
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
