package com.mkemp.artbooktestingjava.model;

import com.google.gson.annotations.SerializedName;

public class ImageResult
{
    private final int comments;
    private final int downloads;
    private final int favorites;
    private final int id;
    private final int imageHeight;
    private final int imageSize;
    private final int imageWidth;
    private final String largeImageURL;
    private final int likes;
    private final String pageURL;
    private final int previewHeight;
    private final String previewURL;
    private final int previewWidth;
    private final String tags;
    private final String type;
    private final String user;

    @SerializedName("user_id")
    private final int userId;

    private final String userImageURL;
    private final int views;
    private final int webformatHeight;
    private final String webformatURL;
    private final int webformatWidth;

    public ImageResult(int comments,
                       int downloads,
                       int favorites,
                       int id,
                       int imageHeight,
                       int imageSize,
                       int imageWidth,
                       String largeImageURL,
                       int likes,
                       String pageURL,
                       int previewHeight,
                       String previewURL,
                       int previewWidth,
                       String tags,
                       String type,
                       String user,
                       int userId,
                       String userImageURL,
                       int views,
                       int webformatHeight,
                       String webformatURL,
                       int webformatWidth)
    {

        this.comments = comments;
        this.downloads = downloads;
        this.favorites = favorites;
        this.id = id;
        this.imageHeight = imageHeight;
        this.imageSize = imageSize;
        this.imageWidth = imageWidth;
        this.largeImageURL = largeImageURL;
        this.likes = likes;
        this.pageURL = pageURL;
        this.previewHeight = previewHeight;
        this.previewURL = previewURL;
        this.previewWidth = previewWidth;
        this.tags = tags;
        this.type = type;
        this.user = user;
        this.userId = userId;
        this.userImageURL = userImageURL;
        this.views = views;
        this.webformatHeight = webformatHeight;
        this.webformatURL = webformatURL;
        this.webformatWidth = webformatWidth;
    }

    public int getComments()
    {
        return comments;
    }

    public int getDownloads()
    {
        return downloads;
    }

    public int getFavorites()
    {
        return favorites;
    }

    public int getId()
    {
        return id;
    }

    public int getImageHeight()
    {
        return imageHeight;
    }

    public int getImageSize()
    {
        return imageSize;
    }

    public int getImageWidth()
    {
        return imageWidth;
    }

    public String getLargeImageURL()
    {
        return largeImageURL;
    }

    public int getLikes()
    {
        return likes;
    }

    public String getPageURL()
    {
        return pageURL;
    }

    public int getPreviewHeight()
    {
        return previewHeight;
    }

    public String getPreviewURL()
    {
        return previewURL;
    }

    public int getPreviewWidth()
    {
        return previewWidth;
    }

    public String getTags()
    {
        return tags;
    }

    public String getType()
    {
        return type;
    }

    public String getUser()
    {
        return user;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUserImageURL()
    {
        return userImageURL;
    }

    public int getViews()
    {
        return views;
    }

    public int getWebformatHeight()
    {
        return webformatHeight;
    }

    public String getWebformatURL()
    {
        return webformatURL;
    }

    public int getWebformatWidth()
    {
        return webformatWidth;
    }
}
