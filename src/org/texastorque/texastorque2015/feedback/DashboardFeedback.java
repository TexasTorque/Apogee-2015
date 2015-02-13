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
    public void run() {
        //Drivebase
        //Units use feet and seconds.
        leftDrivePosition = SmartDashboard.getNumber("leftDrivePosition", 0.0);
        rightDrivePosition = SmartDashboard.getNumber("rightDrivePosition", 0.0);

        leftDriveVelocity = SmartDashboard.getNumber("leftDriveVelocity", 0.0);
        rightDriveVelocity = SmartDashboard.getNumber("rightDriveVelocity", 0.0);

        leftDriveAcceleration = SmartDashboard.getNumber("leftDriveAcceleration", 0.0);
        rightDriveAcceleration = SmartDashboard.getNumber("rightDriveAcceleration", 0.0);

        //Elevator
        elevatorHeight = SmartDashboard.getNumber("elevatorHeight", 0.0);
        elevatorVelocity = SmartDashboard.getNumber("elevatorVelocity", 0.0);

        elevatorAtTop = SmartDashboard.getBoolean("elevatorAtTop", false);
        elevatorAtBottom = SmartDashboard.getBoolean("elevatorAtBottom", true);

        //Sluice
        toteInSluice = SmartDashboard.getBoolean("toteInSluice", false);

        //angle (radians)
        angle = SmartDashboard.getNumber("angle", 0.0);
        angularVelocity = SmartDashboard.getNumber("angularVelocity", 0.0);
    }
}
