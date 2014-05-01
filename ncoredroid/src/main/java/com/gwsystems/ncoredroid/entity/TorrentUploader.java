package com.gwsystems.ncoredroid.entity;

import java.io.Serializable;

/**
 * Created by paalgyula on 2014.05.01..
 */
public class TorrentUploader implements Serializable {
    private String username;
    private String userId;
    private String color;

    public TorrentUploader(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public TorrentUploader(String username, String userId, String color) {
        this.username = username;
        this.userId = userId;
        this.color = color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TorrentUploader{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
