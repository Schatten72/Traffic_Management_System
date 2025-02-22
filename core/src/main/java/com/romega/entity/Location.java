package com.romega.entity;

import java.io.Serializable;

public class Location implements Serializable, Comparable<Location> {
    private Double latitude;
    private Double longitude;

    public Location() {}

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int compareTo(Location location) {
        double latitudeDifference = Math.abs(this.latitude - location.getLatitude());
        double longitudeDifference = Math.abs(this.longitude - location.getLongitude());
        double tolerance = 1e-9;

        if (latitudeDifference < tolerance && longitudeDifference < tolerance) {
            return 1;
        } else {
            return 0;
        }
    }
}
