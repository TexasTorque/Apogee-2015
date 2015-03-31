package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardFeedback extends Feedback {

    @Override
    public void resetDriveEncoders() {
        leftDrivePosition = 0.0;
        rightDrivePosition = 0.0;
    }

    @Override
    public void resetGyro() {
        angle = 0.0;
    }

    @Override
    public void resetElevatorEncoders() {
        elevatorHeight = 0.0;
    }

    @Override
    public void run() {
        //Drivebase
        //Units use feet and seconds.
        leftDrivePosition = SmartDashboard.getNumber("leftPosition", 0.0);
        rightDrivePosition = SmartDashboard.getNumber("rightPosition", 0.0);

        leftDriveVelocity = SmartDashboard.getNumber("leftVelocity", 0.0);
        rightDriveVelocity = SmartDashboard.getNumber("rightVelocity", 0.0);

        leftDriveAcceleration = SmartDashboard.getNumber("leftDriveAcceleration", 0.0);
        rightDriveAcceleration = SmartDashboard.getNumber("rightDriveAcceleration", 0.0);

        //Elevator
        elevatorHeight = SmartDashboard.getNumber("ElevatorTargetPosition", 0.0);
        elevatorVelocity = SmartDashboard.getNumber("ElevatorTargetVelocity", 0.0);

        elevatorAtTop = SmartDashboard.getBoolean("elevatorAtTop", false);
        elevatorAtBottom = SmartDashboard.getBoolean("elevatorAtBottom", false);

        //angle (radians)
        angle = SmartDashboard.getNumber("angle", 0.0);
    }

    @Override
    public void resetStingerAngle() {
    }
}
