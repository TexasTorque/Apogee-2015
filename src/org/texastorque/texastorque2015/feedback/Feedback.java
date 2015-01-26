package org.texastorque.texastorque2015.feedback;

public abstract class Feedback implements Runnable {

    //Drivebase
    protected volatile double leftDrivePosition;
    protected volatile double rightDrivePosition;
    protected volatile double leftDriveVelocity;
    protected volatile double rightDriveVelocity;

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
     * @return The position in feet
     */
    public double getLeftDriveVelocity() {
        return leftDriveVelocity;
    }

    /**
     * Get the velocity of the right side of the drivetrain.
     *
     * @return The position in feet
     */
    public double getRightDriveVelocity() {
        return rightDriveVelocity;
    }

    private double elevatorHeight;
    private double elevatorVelocity;

    public double getElevatorHeight() {
        return elevatorHeight;
    }

    public double getElevatorVelocity() {
        return elevatorVelocity;
    }
}
