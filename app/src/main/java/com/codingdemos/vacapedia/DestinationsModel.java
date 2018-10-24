package com.codingdemos.vacapedia;

import android.widget.EditText;

public class DestinationsModel {

    private String name;
    private String postID;
    private String image;
    private String menuID;
    private String menuName;
    private String _id;
    private String category;
    private String location;
    private String description;
    private String latitude;
    private String longitude;
    private String address;
    private String distance;
    private String note;
    private String costs;
    private String total_cost;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "DestinationsModel{" +
                "_id='" + _id + "\'" +
                ", name='" + name + "\'" +
                ", postID='" + postID + "\'" +
                ", image='" + image + "\'" +
                ", menuID='" + menuID + "\'" +
                ", menuName='" + menuName + "\'" +
                ", category='" + category + "\'" +
                ", location='" + location + "\'" +
                ", description='" + description + "\'" +
                ", latitude='" + latitude + "\'" +
                ", longitude='" + longitude + "\'" +
                ", address='" + address + "\'" +
                ", distance='" + distance + "\'" +
                ", note='" + note + "\'" +
                ", costs='" + costs + "\'" +
                ", total_cost='" + total_cost + "\'" +
                "}";
    }

}
