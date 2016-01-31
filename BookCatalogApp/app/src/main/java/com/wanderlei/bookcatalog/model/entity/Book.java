package com.wanderlei.bookcatalog.model.entity;

import java.util.Date;

/**
 * Created by wanderlei on 28/01/16.
 */
public class Book {

    private String Kind;
    private int id;
    private String title;
    private String description;
    private String access;
    private Date updated;
    private Date created;
    private int volumeCount;
    private Date volumesLastUpdated;

    public Book(){

    }

    public Book(String kind, int id, String title, String description, String access, Date updated, Date created, int volumeCount, Date volumesLastUpdated) {
        Kind = kind;
        this.id = id;
        this.title = title;
        this.description = description;
        this.access = access;
        this.updated = updated;
        this.created = created;
        this.volumeCount = volumeCount;
        this.volumesLastUpdated = volumesLastUpdated;
    }


    public String getKind() {
        return Kind;
    }

    public void setKind(String kind) {
        Kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getVolumeCount() {
        return volumeCount;
    }

    public void setVolumeCount(int volumeCount) {
        this.volumeCount = volumeCount;
    }

    public Date getVolumesLastUpdated() {
        return volumesLastUpdated;
    }

    public void setVolumesLastUpdated(Date volumesLastUpdated) {
        this.volumesLastUpdated = volumesLastUpdated;
    }
}
