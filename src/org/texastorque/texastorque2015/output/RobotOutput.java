package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

public class RobotOutput extends Output {

    //Drivebase
    private TorqueMotor leftDriveAMotor;
    private TorqueMotor leftDriveBMotor;
    private TorqueMotor rightDriveAMotor;
    private TorqueMotor rightDriveBMotor;
    private TorqueMotor frontStrafeMotor;
    private TorqueMotor rearStrafeMotor;

    //Crazy Arms
    private Solenoid openSolenoid;
    private Solenoid punchSolenoid;
    private DoubleSolenoid tiltSolenoid;

    //Intake
    private TorqueMotor leftIntakeMotor;
    private TorqueMotor rightIntakeMotor;

    public RobotOutput() {
//        TorqueMotor m1 = new TorqueMotor(new VictorSP(0), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m2 = new TorqueMotor(new VictorSP(1), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m3 = new TorqueMotor(new VictorSP(2), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m4 = new TorqueMotor(new VictorSP(3), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m5 = new TorqueMotor(new VictorSP(4), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m6 = new TorqueMotor(new VictorSP(5), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m7 = new TorqueMotor(new VictorSP(6), false, TorqueMotor.LinearizationType.kNone);
//        TorqueMotor m8 = new TorqueMotor(new VictorSP(7), false, TorqueMotor.LinearizationType.kNone);
        TorqueMotor m9 = new TorqueMotor(new VictorSP(8), false, TorqueMotor.LinearizationType.kNone);
        TorqueMotor m10 = new TorqueMotor(new VictorSP(9), false, TorqueMotor.LinearizationType.kNone);

        //Drivebase
        leftDriveAMotor = new TorqueMotor(new VictorSP(Ports.LEFT_A_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        leftDriveBMotor = new TorqueMotor(new VictorSP(Ports.LEFT_B_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rightDriveAMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_A_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightDriveBMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_B_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        frontStrafeMotor = new TorqueMotor(new VictorSP(Ports.FRONT_STRAFE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rearStrafeMotor = new TorqueMotor(new VictorSP(Ports.REAR_STRAFE_PORT), true, TorqueMotor.LinearizationType.kNone);

        //Crazy Arms
        openSolenoid = new Solenoid(Ports.OPEN_SOLENOID_PORT);
        punchSolenoid = new Solenoid(Ports.PUNCH_SOLENOID);
        tiltSolenoid = new DoubleSolenoid(Ports.TILT_SOLENOID_FORWARD_PORT, Ports.TILT_SOLENOID_BACKWARD_PORT);

        //Intake
        leftIntakeMotor = new TorqueMotor(new VictorSP(Ports.LEFT_INTAKE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightIntakeMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_INTAKE_PORT), false, TorqueMotor.LinearizationType.kNone);
    }

    //Drivebase
    @Override
    public void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe) {
        leftDriveAMotor.set(left);
        leftDriveBMotor.set(left);
        rightDriveAMotor.set(right);
        rightDriveBMotor.set(right);
        frontStrafeMotor.set(frontStrafe);
        rearStrafeMotor.set(rearStrafe);
    }

    //Elevator
    @Override
    public void setElevatorMotorSpeeds(double speed) {
    }

    //Crazy Arms
    @Override
    public void setArmsOpen(boolean open) {
        openSolenoid.set(open);
    }

    @Override
    public void setTiltUp(boolean on) {
        tiltSolenoid.set(on ? Value.kForward : Value.kReverse);
    }

    @Override
    public void setPunchOut(boolean out) {
        punchSolenoid.set(out);
    }

    //Intake
    @Override
    public void setIntakeMotorSpeed(double speed) {
        leftIntakeMotor.set(speed);
        rightIntakeMotor.set(speed);
    }
}
