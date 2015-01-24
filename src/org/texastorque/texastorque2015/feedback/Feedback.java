package org.texastorque.texastorque2015.feedback;

public abstract class Feedback implements Runnable {

    protected volatile double leftDrivePosition;
    protected volatile double rightDrivePosition;
    protected volatile double leftDriveVelocity;
    protected volatile double rightDriveVelocity;
}
