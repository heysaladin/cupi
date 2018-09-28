package com.codingdemos.flowers;

/**
 * Created by abdalla on 1/12/18.
 */

public class DestinationData {

    private String destinationName;
    private String destinationDescription;
    private int destinationImage;

    public DestinationData(String destinationName, String destinationDescription, int destinationImage) {
        this.destinationName = destinationName;
        this.destinationDescription = destinationDescription;
        this.destinationImage = destinationImage;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getDestinationDescription() {
        return destinationDescription;
    }

    public int getDestinationImage() {
        return destinationImage;
    }
}
