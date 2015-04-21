package org.texastorque.texastorque2015.input;

import org.texastorque.texastorque2015.feedback.Feedback;

public abstract class Input implements Runnable {

    protected Feedback feedback;

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

    public synchronized double getLeftSpeed() {
        return leftSpeed;
    }

    public synchronized double getRightSpeed() {
        return rightSpeed;
    }

    public synchronized double getStrafeSpeed() {
        return strafeSpeed;
    }

    public synchronized boolean isDrivebaseControlled() {
        return drivebaseControlled;
    }

    public synchronized double getDriveDistance() {
        return driveDistance;
    }

    public synchronized double getDriveAngle() {
        return driveAngle;
    }

    //Elevator
    protected volatile double elevatorPosition;
    protected volatile boolean override;
    protected volatile double overrideElevatorMotorSpeed;
    protected volatile boolean elevatorFFpOff;
    protected volatile boolean newPosition;

    public synchronized boolean isOverride() {
        return override;
    }

    public synchronized double getOverrideElevatorMotorSpeed() {
        return overrideElevatorMotorSpeed;
    }

    public synchronized double getElevatorPosition() {
        return elevatorPosition;
    }

    public synchronized boolean isElevatorFFpOff() {
        return elevatorFFpOff;
    }

    public synchronized boolean newPosition() {
        return newPosition;
    }

    //Crazy Arms
    protected volatile boolean punchOut;
    protected volatile boolean tiltUp;
    protected volatile boolean canHolderUp;

    public synchronized boolean isPunchOut() {
        return punchOut;
    }

    public synchronized boolean isTiltUp() {
        return tiltUp;
    }
    
    public synchronized boolean isCanHolderUp() {
        return canHolderUp;
    }
    
    //Intake
    protected volatile double leftIntakeSpeed;
    protected volatile double rightIntakeSpeed;
    protected volatile boolean intakeIn;

    public synchronized double getLeftIntakeSpeed() {
        return leftIntakeSpeed;
    }

    public synchronized double getRightIntakeSpeed() {
        return rightIntakeSpeed;
    }

    public synchronized boolean getIntakeIn() {
        return intakeIn;
    }

    //Stingers
    protected volatile double stingerAngle;
    protected volatile double stingerRetractSpeed;
    protected volatile boolean stingersOff;

    public synchronized double getStingerRetractSpeed() {
        return stingerRetractSpeed;
    }

    public synchronized double getStingerAngle() {
        return stingerAngle;
    }

    public synchronized boolean areStingersOff() {
        return stingersOff;
    }

}
