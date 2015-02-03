package org.texastorque.texastorque2015.feedback;

public abstract class Feedback implements Runnable {

    //Drivebase
    protected volatile double leftDrivePosition;
    protected volatile double rightDrivePosition;
    protected volatile double leftDriveVelocity;
    protected volatile double rightDriveVelocity;
    protected volatile double leftDriveAcceleration;
    protected volatile double rightDriveAcceleration;

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
     * Reset the drive encoders to 0.
     *
     */
    public abstract void resetDriveEncoders();

    //Elevator
    private double elevatorHeight;
    private double elevatorVelocity;

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
}
