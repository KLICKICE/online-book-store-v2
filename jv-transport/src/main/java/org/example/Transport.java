package org.example;

public class Transport {
    private String color;
    private double maxSpeed;

    public Transport(String color, double maxSpeed) {
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
