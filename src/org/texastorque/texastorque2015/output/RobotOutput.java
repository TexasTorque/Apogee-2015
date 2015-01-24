package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.Motor;

public class RobotOutput extends Output {

    private Motor leftDriveAMotor;
    private Motor leftDriveBMotor;
    private Motor rightDriveAMotor;
    private Motor rightDriveBMotor;
    private Motor frontStrafeMotor;
    private Motor rearStrafeMotor;

    public RobotOutput() {
        leftDriveAMotor = new Motor(new VictorSP(Ports.LEFT_A_DRIVE_PORT), false);
        leftDriveBMotor = new Motor(new VictorSP(Ports.LEFT_B_DRIVE_PORT), false);
        rightDriveAMotor = new Motor(new VictorSP(Ports.RIGHT_A_DRIVE_PORT), true);
        rightDriveBMotor = new Motor(new VictorSP(Ports.RIGHT_B_DRIVE_PORT), true);
        frontStrafeMotor = new Motor(new VictorSP(Ports.FRONT_STRAFE_PORT), false);
        rearStrafeMotor = new Motor(new VictorSP(Ports.REAR_STRAFE_PORT), true);
    }

    @Override
    public void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe) {
        leftDriveAMotor.set(left);
        leftDriveBMotor.set(left);
        rightDriveAMotor.set(right);
        rightDriveBMotor.set(right);
        frontStrafeMotor.set(frontStrafe);
        rearStrafeMotor.set(rearStrafe);
    }

    @Override
    public void setElevatorMotorSpeeds(double speed) {
    }

}
