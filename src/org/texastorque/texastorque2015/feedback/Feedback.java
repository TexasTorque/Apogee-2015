package org.texastorque.texastorque2015.feedback;

public abstract class Feedback implements Runnable {

    private double elevatorHeight;
    private double elevatorVelocity;

    public double getElevatorHeight() {
        return elevatorHeight;
    }

    public double getElevatorVelocity() {
        return elevatorVelocity;
    }
}
