package com.codingdemos.flowers;

public class SubGroupModel {

    private String menuID, menuName, name, postID,image,bgimage, id, isParent, child;
    private int favouriteStatus;

    public SubGroupModel() {}

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

    public String getBgimage() {
        return bgimage;
    }
    public void setBgimage(String bgimage) {
        this.bgimage = bgimage;
    }

    public int getFavouriteStatus() {
        return favouriteStatus;
    }
    public void setFavouriteStatus(int favouriteStatus) {
        this.favouriteStatus = favouriteStatus;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIsParent() {
        return isParent;
    }
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getChild() {
        return child;
    }
    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "SubGroupModel{" +
                "menuID='" + menuID + "\'" +
                ", menuName='" + menuName + "\'" +
                ", name='" + name + "\'" +
                ", postID='" + postID + "\'" +
                ", image='" + image + "\'" +
                ", bgimage='" + bgimage + "\'" +
                ", favouriteStatus=" + favouriteStatus + "\'" +
                ", id='" + id + "\'" +
                ", isParent='" + isParent + "\'" +
                ", child='" + child + "\'" +
                "}";
    }

}
