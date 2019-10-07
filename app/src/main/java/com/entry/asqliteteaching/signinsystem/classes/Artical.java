package com.entry.asqliteteaching.signinsystem.classes;

import android.graphics.Bitmap;

import java.util.List;

public class Artical {

    private int articalImageId;
    private int commentNum;
    private Bitmap articlePhoto;
    private String articalTitleText;
    private String articalContextText;

    private User linkUser;
    private String mWriterId;//用于识别LostItem对象的Id,在构造方法中已经初始化

    public Artical(){

    }

    @Override
    public String toString() {
        return "(" + articalTitleText + "),(" + articalContextText + "),(" + commentNum +")";
    }

    public String getmWriterId() {
        return mWriterId;
    }

    public void setmWriterId(String mWriterId) {
        this.mWriterId = mWriterId;
    }

    public int getArticalImageId() {
        return articalImageId;
    }

    public void setArticalImageId(int articalImageId) {
        this.articalImageId = articalImageId;
    }

    public String getArticalTitleText() {
        return articalTitleText;
    }

    public void setArticalTitleText(String articalTitleText) {
        this.articalTitleText = articalTitleText;
    }

    public String getArticalContextText() {
        return articalContextText;
    }

    public void setArticalContextText(String articalContextText) {
        this.articalContextText = articalContextText;
    }

    public User getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(User linkUser) {
        this.linkUser = linkUser;
    }

    public Bitmap getArticlePhoto() {
        return articlePhoto;
    }

    public void setArticlePhoto(Bitmap articlePhoto) {
        this.articlePhoto = articlePhoto;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
