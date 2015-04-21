package org.texastorque.texastorque2015.feedback;

import org.texastorque.texastorque2015.constants.Constants;

public abstract class Feedback implements Runnable {

    //Drivebase
    protected volatile double leftDrivePosition;
    protected volatile double rightDrivePosition;
    protected volatile double leftDriveVelocity;
    protected volatile double rightDriveVelocity;
    protected volatile double leftDriveAcceleration;
    protected volatile double rightDriveAcceleration;
    protected volatile double angle;

    /**
     * Get the position of the left side of the drivetrain.
     *
     * @return The position in feet
     */
    public synchronized double getLeftDrivePosition() {
        return leftDrivePosition;
    }

    /**
     * Get the position of the right side of the drivetrain.
     *
     * @return The position in feet
     */
    public synchronized double getRightDrivePosition() {
        return rightDrivePosition;
    }

    /**
     * Get the velocity of the left side of the drivetrain.
     *
     * @return The velocity in ft/s
     */
    public synchronized double getLeftDriveVelocity() {
        return leftDriveVelocity;
    }

    /**
     * Get the velocity of the right side of the drivetrain.
     *
     * @return The velocity in ft/s
     */
    public synchronized double getRightDriveVelocity() {
        return rightDriveVelocity;
    }

    /**
     * Get the acceleration of the left side of the drivetrain.
     *
     * @return The acceleration in ft/s^2
     */
    public synchronized double getLeftDriveAcceleration() {
        return leftDriveAcceleration;
    }

    /**
     * Get the acceleration of the right side of the drivetrain.
     *
     * @return The acceleration in ft/s^2
     */
    public synchronized double getRightDriveAcceleration() {
        return rightDriveAcceleration;
    }

    /**
     * Get the angle of the gyro.
     *
     * @return The angle in degrees
     */
    public synchronized double getAngle() {
        return angle;
    }

    /**
     * Reset the drive encoders to 0.
     */
    public abstract void resetDriveEncoders();

    /**
     * Reset the elevator encoders to 0.
     */
    public abstract void resetElevatorEncoders();

    /**
     * Reset the gyro accumulators.
     */
    public abstract void resetGyro();

    //Elevator
    protected double elevatorHeight;
    protected double elevatorVelocity;
    protected boolean elevatorDone;

    public synchronized boolean isElevatorDone() {
        return elevatorDone;
    }

    public synchronized void setElevatorDone(boolean elevatorDone) {
        this.elevatorDone = elevatorDone;
    }

    public synchronized boolean isElevatorHere(double height) {
        return Math.abs(height - elevatorHeight) <= Constants.ElevatorPDoneRange.getDouble()
                && Math.abs(elevatorVelocity) <= Constants.ElevatorVDoneRange.getDouble();
    }

    /**
     * Get the height of the elevator in inches.
     *
     * @return The height in inches.
     */
    public synchronized double getElevatorHeight() {
        return elevatorHeight;
    }

    /**
     * Get the velocity of the elevator in inches/second.
     *
     * @return The velocity in inches/second.
     */
    public synchronized double getElevatorVelocity() {
        return elevatorVelocity;
    }
    
    //Stingers
    protected volatile double leftStingerAngle;
    protected volatile double rightStingerAngle;

    public synchronized double getLeftStingerAngle() {
        return leftStingerAngle;
    }

    public synchronized double getRightStingerAngle() {
        return rightStingerAngle;
    }
}
