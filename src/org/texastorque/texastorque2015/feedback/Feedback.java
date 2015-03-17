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
    protected volatile double angularVelocity;

    /**
     * Get the position of the left side of the drivetrain.
     *
     * @return The position in feet
     */
    public double getLeftDrivePosition() {
        return leftDrivePosition;
    }

    /**
     * Get the position of the right side of the drivetrain.
     *
     * @return The position in feet
     */
    public double getRightDrivePosition() {
        return rightDrivePosition;
    }

    /**
     * Get the velocity of the left side of the drivetrain.
     *
     * @return The velocity in ft/s
     */
    public double getLeftDriveVelocity() {
        return leftDriveVelocity;
    }

    /**
     * Get the velocity of the right side of the drivetrain.
     *
     * @return The velocity in ft/s
     */
    public double getRightDriveVelocity() {
        return rightDriveVelocity;
    }

    /**
     * Get the acceleration of the left side of the drivetrain.
     *
     * @return The acceleration in ft/s^2
     */
    public double getLeftDriveAcceleration() {
        return leftDriveAcceleration;
    }

    /**
     * Get the acceleration of the right side of the drivetrain.
     *
     * @return The acceleration in ft/s^2
     */
    public double getRightDriveAcceleration() {
        return rightDriveAcceleration;
    }

    /**
     * Get the angle of the gyro.
     *
     * @return The angle in degrees
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Get the angle of the gyro.
     *
     * @return The angular velocity in degrees/s
     */
    public double getAngularVelocity() {
        return angularVelocity;
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
    protected boolean elevatorAtTop;
    protected boolean elevatorAtBottom;
    protected boolean elevatorDone;

    public boolean isElevatorDone() {
        return elevatorDone;
    }

    public void setElevatorDone(boolean elevatorDone) {
        this.elevatorDone = elevatorDone;
    }

    public boolean isElevatorHere(double height) {
        return Math.abs(height - elevatorHeight) <= Constants.ElevatorPDoneRange.getDouble()
                && Math.abs(elevatorVelocity) <= Constants.ElevatorVDoneRange.getDouble();
    }

    /**
     * Get the height of the elevator in inches.
     *
     * @return The height in inches.
     */
    public double getElevatorHeight() {
        return elevatorHeight;
    }

    /**
     * Get the velocity of the elevator in inches/second.
     *
     * @return The velocity in inches/second.
     */
    public double getElevatorVelocity() {
        return elevatorVelocity;
    }

    public boolean isElevatorAtTop() {
        return elevatorAtTop;
    }

    public boolean isElevatorAtBottom() {
        return elevatorAtBottom;
    }

    //Sluice
    protected boolean toteInSluice;
    protected volatile double toteSlideTime;

    public boolean isToteInSluice() {
        return toteInSluice;
    }

    public double getToteSlideTime() {
        return toteSlideTime;
    }
}
