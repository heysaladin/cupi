package com.codingdemos.flowers;

public class DestinationsModel {

    String name,postID,image,menuID,menuName;

    public DestinationsModel() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
                "name='" + name + "\'" +
                ", postID='" + postID + "\'" +
                ", image='" + image + "\'" +
                ", menuID='" + menuID + "\'" +
                ", menuName='" + menuName + "\'" +
                "}";
    }

}
