package org.texastorque.texastorque2015.input;

import org.texastorque.texastorque2015.feedback.Feedback;

public abstract class Input implements Runnable {

    protected Feedback feedback;

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile double frontStrafeSpeed;
    protected volatile double rearStrafeSpeed;
    
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

    //Elevator
    protected volatile double elevatorPosition;
    protected volatile boolean elevatorOverride;
    protected volatile double overrideElevatorMotorSpeed;
    
    public boolean isElevatorOverride() {
        return elevatorOverride;
    }

    public double getOverrideElevatorMotorSpeed() {
        return overrideElevatorMotorSpeed;
    }

    public double getElevatorPosition() {
        return elevatorPosition;
    }

    //Crazy Arms
    protected volatile boolean armOpen;
    protected volatile boolean punchOut;
    protected volatile boolean tiltUp;

    public boolean isArmOpen() {
        return armOpen;
    }

    public boolean isPunchOut() {
        return punchOut;
    }

    public boolean isTiltUp() {
        return tiltUp;
    }

}
