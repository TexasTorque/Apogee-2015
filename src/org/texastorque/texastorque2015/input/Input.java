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

    protected volatile boolean drivebaseControlled;
    protected volatile double driveDistance;
    protected volatile double driveAngle;

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

    public boolean isDrivebaseControlled() {
        return drivebaseControlled;
    }

    public double getDriveDistance() {
        return driveDistance;
    }

    public double getDriveAngle() {
        return driveAngle;
    }

    //Elevator
    protected volatile double elevatorPosition;
    protected volatile boolean elevatorOverride;
    protected volatile double overrideElevatorMotorSpeed;
    protected volatile boolean elevatorFFpOff;

    public boolean isElevatorOverride() {
        return elevatorOverride;
    }

    public double getOverrideElevatorMotorSpeed() {
        return overrideElevatorMotorSpeed;
    }

    public double getElevatorPosition() {
        return elevatorPosition;
    }

    public boolean isElevatorFFpOff() {
        return elevatorFFpOff;
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

    //Intake
    protected volatile double intakeSpeed;
    protected volatile boolean intakesIn;

    public double getIntakeSpeed() {
        return intakeSpeed;
    }

    public boolean areIntakesIn() {
        return intakesIn;
    }
    
}
