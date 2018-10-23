package com.codingdemos.vacapedia;

public class NotificationsModel {

    String title, postID, image, menuID, menuName;

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
                "}";
    }

}