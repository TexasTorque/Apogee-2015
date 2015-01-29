package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

public class RobotOutput extends Output {

    private TorqueMotor leftDriveAMotor;
    private TorqueMotor leftDriveBMotor;
    private TorqueMotor rightDriveAMotor;
    private TorqueMotor rightDriveBMotor;
    private TorqueMotor frontStrafeMotor;
    private TorqueMotor rearStrafeMotor;

    private TorqueMotor leftIntakeMotor;
    private TorqueMotor rightIntakeMotor;

    public RobotOutput() {
        leftDriveAMotor = new TorqueMotor(new VictorSP(Ports.LEFT_A_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        leftDriveBMotor = new TorqueMotor(new VictorSP(Ports.LEFT_B_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rightDriveAMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_A_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightDriveBMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_B_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        frontStrafeMotor = new TorqueMotor(new VictorSP(Ports.FRONT_STRAFE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rearStrafeMotor = new TorqueMotor(new VictorSP(Ports.REAR_STRAFE_PORT), true, TorqueMotor.LinearizationType.kNone);

        leftIntakeMotor = new TorqueMotor(new VictorSP(Ports.LEFT_INTAKE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightIntakeMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_INTAKE_PORT), false, TorqueMotor.LinearizationType.kNone);

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

    @Override
    public void setIntakeMotorSpeed(double speed) {
        leftIntakeMotor.set(speed);
        rightIntakeMotor.set(speed);
    }

}
