package com.app.shopdodientu.model;

public class FeedbackModel {
    private int id;
    private String content;
    private int star;
    private String image;
    private String avatar;
    private String username;
    private String time;

    public FeedbackModel() {
    }

    public FeedbackModel(int id, String content, int star, String image, String avatar, String username, String time) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.image = image;
        this.avatar = avatar;
        this.username = username;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
