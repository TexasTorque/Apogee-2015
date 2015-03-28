package org.texastorque.texastorque2015.input;

import org.texastorque.texastorque2015.feedback.Feedback;

public abstract class Input implements Runnable {

    protected Feedback feedback;
    protected volatile int numTotes;

    public void setNumTotes(int totes) {
        numTotes = totes;
    }
    
    public int getNumTotes() {
        return numTotes;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void pushToDashboard() {
    }

    //Drivebase
    protected volatile double leftSpeed;
    protected volatile double rightSpeed;
    protected volatile double strafeSpeed;

    protected volatile boolean drivebaseControlled;
    protected volatile double driveDistance;
    protected volatile double driveAngle;

    public double getLeftSpeed() {
        return leftSpeed;
    }

    public double getRightSpeed() {
        return rightSpeed;
    }

    public double getStrafeSpeed() {
        return strafeSpeed;
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
    protected volatile boolean override;
    protected volatile double overrideElevatorMotorSpeed;
    protected volatile boolean elevatorFFpOff;
    protected volatile boolean newPosition;

    public boolean isOverride() {
        return override;
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

    public boolean newPosition() {
        return newPosition;
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
    protected volatile double leftIntakeSpeed;
    protected volatile double rightIntakeSpeed;
    protected volatile boolean intakeIn;

    public double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }

    public boolean getIntakeIn() {
        return intakeIn;
    }

    //Auto stacking
    protected volatile boolean autoStack;
    protected volatile boolean feederStack;

    public boolean wantAutoStack() {
        return autoStack;
    }
    
    //Stingers
    protected volatile boolean stingerLatch;
    protected volatile boolean stingersDown;

    public boolean isStingerLatched() {
        return stingerLatch;
    }

    public boolean areStingersDown() {
        return stingersDown;
    }

}
