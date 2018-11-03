package com.codingdemos.vacapedia.data;

public class SlidesModel {

    private String _id;
    private String title;
    private String image;
    private String category;
    private String body_copy;
    private String description;

    public SlidesModel() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBody_copy() {
        return body_copy;
    }

    public void setBody_copy(String body_copy) {
        this.body_copy = body_copy;
    }

    @Override
    public String toString() {
        return "SlidesModel{" +
                "_id='" + _id + "\'" +
                ", title='" + title + "\'" +
                ", body_copy='" + body_copy + "\'" +
                ", image='" + image + "\'" +
                ", category='" + category + "\'" +
                ", description='" + description + "\'" +
                "}";
    }

}
