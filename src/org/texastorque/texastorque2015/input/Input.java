package org.texastorque.texastorque2015.input;

public abstract class Input implements Runnable {

    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile double frontStrafeSpeed;
    protected volatile double rearStrafeSpeed;

    //Elevator
    protected volatile double elevatorPosition;

    public double getElevatorPosition() {
        return elevatorPosition;
    }

    public double getLeftSpeed() {
        return leftSpeed;
    }

    public double getRightSpeed() {
        return rightSpeed;
    }

    public double getFrontStrafeSpeed() {
        return frontStrafeSpeed;
    }

    public double getRearStrafeSpeed() {
        return rearStrafeSpeed;
    }

}
