package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

public class RobotOutput extends Output {

    //Drivebase
    private TorqueMotor leftDriveMotorA;
    private TorqueMotor leftDriveMotorB;
    private TorqueMotor rightDriveMotorA;
    private TorqueMotor rightDriveMotorB;
    private TorqueMotor frontStrafeMotor;
    private TorqueMotor rearStrafeMotor;

    //Crazy Arms
    private Solenoid openSolenoid;
    private Solenoid punchSolenoid;
    private Solenoid intakeSolenoid;
    private DoubleSolenoid tiltSolenoid;

    //Intake
    private TorqueMotor leftIntakeMotor;
    private TorqueMotor rightIntakeMotor;

    //Elevator
    private TorqueMotor leftElevatorMotorA;
    private TorqueMotor leftElevatorMotorB;
    private TorqueMotor rightElevatorMotorA;
    private TorqueMotor rightElevatorMotorB;

    public RobotOutput() {
        //Drivebase
        leftDriveMotorA = new TorqueMotor(new VictorSP(Ports.LEFT_A_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        leftDriveMotorB = new TorqueMotor(new VictorSP(Ports.LEFT_B_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rightDriveMotorA = new TorqueMotor(new VictorSP(Ports.RIGHT_A_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightDriveMotorB = new TorqueMotor(new VictorSP(Ports.RIGHT_B_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        frontStrafeMotor = new TorqueMotor(new VictorSP(Ports.FRONT_STRAFE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rearStrafeMotor = new TorqueMotor(new VictorSP(Ports.REAR_STRAFE_PORT), true, TorqueMotor.LinearizationType.kNone);

        //Crazy Arms
        openSolenoid = new Solenoid(Ports.ARM);
        punchSolenoid = new Solenoid(Ports.PUNCH_SOLENOID);
        intakeSolenoid = new Solenoid(Ports.INTAKE_SOLENOID);
        tiltSolenoid = new DoubleSolenoid(Ports.TILT_SOLENOID_A_PORT, Ports.TILT_SOLENOID_B_PORT);

        //Intake
        leftIntakeMotor = new TorqueMotor(new VictorSP(Ports.LEFT_INTAKE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightIntakeMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_INTAKE_PORT), false, TorqueMotor.LinearizationType.kNone);

        //Elevator
        leftElevatorMotorA = new TorqueMotor(new VictorSP(Ports.LEFT_ELEVATOR_A), true, TorqueMotor.LinearizationType.kNone);
        leftElevatorMotorB = new TorqueMotor(new VictorSP(Ports.LEFT_ELEVATOR_B), true, TorqueMotor.LinearizationType.kNone);
        rightElevatorMotorA = new TorqueMotor(new VictorSP(Ports.RIGHT_ELEVATOR_A), false, TorqueMotor.LinearizationType.kNone);
        rightElevatorMotorB = new TorqueMotor(new VictorSP(Ports.RIGHT_ELEVATOR_B), false, TorqueMotor.LinearizationType.kNone);
    }

    //Drivebase
    @Override
    public void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe) {
        leftDriveMotorA.set(left);
        leftDriveMotorB.set(left);
        rightDriveMotorA.set(right);
        rightDriveMotorB.set(right);
        frontStrafeMotor.set(frontStrafe);
        rearStrafeMotor.set(rearStrafe);
    }

    //Elevator
    @Override
    public void setElevatorMotorSpeeds(double speed) {
        leftElevatorMotorA.set(speed);
        leftElevatorMotorB.set(speed);
        rightElevatorMotorA.set(speed);
        rightElevatorMotorB.set(speed);
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

    @Override
    public void setIntakeGrasp(boolean grasp) {
        intakeSolenoid.set(grasp);
    }
}
