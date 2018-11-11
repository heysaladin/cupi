package com.codingdemos.vacapedia.data;

public class NotificationsModel {

    String title;
    String postID;
    String image;
    String menuID;
    String menuName;
    private String content;
    private String category;
    private String _id;

    public NotificationsModel() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostID() {
        return postID;
    }
    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getMenuID() {
        return menuID;
    }
    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "DestinationsModel{" +
                "title='" + title + "\'" +
                ", postID='" + postID + "\'" +
                ", image='" + image + "\'" +
                ", menuID='" + menuID + "\'" +
                ", menuName='" + menuName + "\'" +
                ", content='" + content + "\'" +
                ", category='" + category + "\'" +
                ", _id='" + _id + "\'" +
                "}";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}