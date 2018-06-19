
package com.soldiersofmobile.pixabayviewer.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Hit implements Serializable {

    @SerializedName("previewHeight")
    @Expose
    private Long previewHeight;
    @SerializedName("likes")
    @Expose
    private Long likes;
    @SerializedName("favorites")
    @Expose
    private Long favorites;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("webformatHeight")
    @Expose
    private Long webformatHeight;
    @SerializedName("views")
    @Expose
    private Long views;
    @SerializedName("webformatWidth")
    @Expose
    private Long webformatWidth;
    @SerializedName("previewWidth")
    @Expose
    private Long previewWidth;
    @SerializedName("comments")
    @Expose
    private Long comments;
    @SerializedName("downloads")
    @Expose
    private Long downloads;
    @SerializedName("pageURL")
    @Expose
    private String pageURL;
    @SerializedName("previewURL")
    @Expose
    private String previewURL;
    @SerializedName("webformatURL")
    @Expose
    private String webformatURL;
    @SerializedName("imageWidth")
    @Expose
    private Long imageWidth;
    @SerializedName("user_id")
    @Expose
    private Long userId;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("userImageURL")
    @Expose
    private String userImageURL;
    @SerializedName("imageHeight")
    @Expose
    private Long imageHeight;

    public Long getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(Long previewHeight) {
        this.previewHeight = previewHeight;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getFavorites() {
        return favorites;
    }

    public void setFavorites(Long favorites) {
        this.favorites = favorites;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(Long webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(Long webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public Long getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(Long previewWidth) {
        this.previewWidth = previewWidth;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public Long getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Long imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public Long getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(Long imageHeight) {
        this.imageHeight = imageHeight;
    }

}
