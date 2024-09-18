package com.stmu.jqasample.core;

public record Location(Double latitude, Double longitude) {

    @Override
    public String toString() {
        return latitude + "-" + longitude;
    }
}
