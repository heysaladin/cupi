package com.codingdemos.flowers;

import java.util.ArrayList;

public class HomeDestinationsModel {

    String menuName;
    ArrayList < DestinationsModel > destinationsModelArrayList;

    public HomeDestinationsModel() {}

    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public ArrayList < DestinationsModel > getDestinationsModelArrayList() {
        return destinationsModelArrayList;
    }

    public void setDestinationsModelArrayList(ArrayList < DestinationsModel > destinationsModelArrayList) {
        this.destinationsModelArrayList = destinationsModelArrayList;
    }

}