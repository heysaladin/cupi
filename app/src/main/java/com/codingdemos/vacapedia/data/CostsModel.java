package com.codingdemos.vacapedia.data;

public class CostsModel {

    private String _id;
    private String name;
    private String cost;

    public CostsModel() {}

    @Override
    public String toString() {
        return "CostsModel{" +
                "_id='" + get_id() + "\'" +
                ", name='" + getName() + "\'" +
                ", cost='" + getCost() + "\'" +
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
