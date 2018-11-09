package com.codingdemos.vacapedia.data;

import org.json.JSONArray;

public class PlanModel {

    private String _id;
    private String title;
    private String content;
    private String body_copy;
    private String target_date;
    private String target_time;
    private JSONArray destinations;
    private String image;
    private String cost;
    private String location;

    public PlanModel() {}

    @Override
    public String toString() {
        return "UserModel{" +
                "_id='" + get_id() + "\'" +
                ", title='" + getTitle() + "\'" +
                ", content='" + getContent() + "\'" +
                ", body_copy='" + getBody_copy() + "\'" +
                ", target_date='" + getTarget_date() + "\'" +
                ", target_time='" + getTarget_time() + "\'" +
                ", destinations='" + getDestinations() + "\'" +
                ", image='" + getImage() + "\'" +
                ", cost='" + getCost() + "\'" +
                ", location='" + getLocation() + "\'" +
                "}";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBody_copy() {
        return body_copy;
    }

    public void setBody_copy(String body_copy) {
        this.body_copy = body_copy;
    }

    public String getTarget_date() {
        return target_date;
    }

    public void setTarget_date(String target_date) {
        this.target_date = target_date;
    }

    public String getTarget_time() {
        return target_time;
    }

    public void setTarget_time(String target_time) {
        this.target_time = target_time;
    }

    public JSONArray getDestinations() {
        return destinations;
    }

    public void setDestinations(JSONArray destinations) {
        this.destinations = destinations;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
