package com.codingdemos.vacapedia.data;

import org.json.JSONArray;

public class FamilyModel {

    private String _id;
    private String name;
    private String description;
    private String location;
    private String family_photo;
    private String cover_photo;
    private JSONArray members;

    public FamilyModel() {}

    @Override
    public String toString() {
        return "FamilyModel{" +
                "_id='" + get_id() + "\'" +
                ", name='" + getName() + "\'" +
                ", description='" + getDescription() + "\'" +
                ", location='" + getLocation() + "\'" +
                ", family_photo='" + getFamily_photo() + "\'" +
                ", cover_photo='" + getCover_photo() + "\'" +
                ", members='" + getMembers() + "\'" +
                "}";
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFamily_photo() {
        return family_photo;
    }

    public void setFamily_photo(String family_photo) {
        this.family_photo = family_photo;
    }

    public String getCover_photo() {
        return cover_photo;
    }

    public void setCover_photo(String cover_photo) {
        this.cover_photo = cover_photo;
    }

    public JSONArray getMembers() {
        return members;
    }

    public void setMembers(JSONArray members) {
        this.members = members;
    }
}
