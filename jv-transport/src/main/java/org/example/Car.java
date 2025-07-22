package org.example;

public class Car extends Transport implements Info, Distance{
    private int doors;

    public Car(int doors, String color, double maxSpeed) {
        super(color, maxSpeed);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    @Override
    public double getTravelTime(double distance) {
        if (getMaxSpeed() <= 0) {
            return -1;
        } else {
            return distance / getMaxSpeed();
        }
    }

    @Override
    public void getInfo() {
        System.out.println(String.format("Car speed: %.2f, color: %s, doors: %d",
                getMaxSpeed(), getColor(), getDoors()));
    }
}
