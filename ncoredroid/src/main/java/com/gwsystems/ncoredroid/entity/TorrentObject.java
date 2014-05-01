package com.gwsystems.ncoredroid.entity;

import java.io.Serializable;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentObject implements Serializable {
    private String name;
    private String size;

    private String link;
    private String downloaded;

    private String leech;
    private String seed;
    private String category;

    private TorrentUploader torrentUploader;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDownloaded() {
        return downloaded;
    }

    public void setDownloaded(String downloaded) {
        this.downloaded = downloaded;
    }

    public String getLeech() {
        return leech;
    }

    public void setLeech(String leech) {
        this.leech = leech;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "TorrentObject{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", link='" + link + '\'' +
                ", downloaded='" + downloaded + '\'' +
                ", leech='" + leech + '\'' +
                ", seed='" + seed + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public TorrentUploader getTorrentUploader() {
        return torrentUploader;
    }

    public void setTorrentUploader(TorrentUploader torrentUploader) {
        this.torrentUploader = torrentUploader;
    }
}
