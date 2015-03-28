package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

public class RobotOutput extends Output {

    //Drivebase
    private TorqueMotor leftDriveMotorA;
    private TorqueMotor leftDriveMotorB;
    private TorqueMotor rightDriveMotorA;
    private TorqueMotor rightDriveMotorB;
    private TorqueMotor strafeMotor;

    //Crazy Arms
    private DoubleSolenoid openSolenoid;
    private Solenoid punchSolenoid;
    private Solenoid intakeSolenoid;
    private DoubleSolenoid tiltSolenoid;

    //Intake
    private TorqueMotor leftIntakeMotor;
    private TorqueMotor rightIntakeMotor;

    //Elevator
    private TorqueMotor leftElevatorMotorA;
    private TorqueMotor rightElevatorMotorA;
    
    //Stingers
    private Servo leftStingerServo;
    private Servo rightStingerServo;
    private TorqueMotor leftStingerMotor;
    private TorqueMotor rightStingerMotor;

    public RobotOutput() {
        //Drivebase
        leftDriveMotorA = new TorqueMotor(new VictorSP(Ports.LEFT_A_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        leftDriveMotorB = new TorqueMotor(new VictorSP(Ports.LEFT_B_DRIVE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rightDriveMotorA = new TorqueMotor(new VictorSP(Ports.RIGHT_A_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        rightDriveMotorB = new TorqueMotor(new VictorSP(Ports.RIGHT_B_DRIVE_PORT), true, TorqueMotor.LinearizationType.kNone);
        strafeMotor = new TorqueMotor(new VictorSP(Ports.STRAFE_PORT), true, TorqueMotor.LinearizationType.kNone);

        //Crazy Arms
        openSolenoid = new DoubleSolenoid(Ports.ARM_A, Ports.ARM_B);
        punchSolenoid = new Solenoid(Ports.PUNCH_SOLENOID);
        intakeSolenoid = new Solenoid(Ports.INTAKE_SOLENOID);
        tiltSolenoid = new DoubleSolenoid(Ports.TILT_SOLENOID_A_PORT, Ports.TILT_SOLENOID_B_PORT);

        //Intake
        leftIntakeMotor = new TorqueMotor(new VictorSP(Ports.LEFT_INTAKE_PORT), false, TorqueMotor.LinearizationType.kNone);
        rightIntakeMotor = new TorqueMotor(new VictorSP(Ports.RIGHT_INTAKE_PORT), true, TorqueMotor.LinearizationType.kNone);

        //Elevator
        leftElevatorMotorA = new TorqueMotor(new VictorSP(Ports.LEFT_ELEVATOR), true, TorqueMotor.LinearizationType.kNone);
        rightElevatorMotorA = new TorqueMotor(new VictorSP(Ports.RIGHT_ELEVATOR), false, TorqueMotor.LinearizationType.kNone);
        
        leftStingerServo = new Servo(Ports.leftStingerServoPort);
        rightStingerServo = new Servo(Ports.rightStingerServoPort);
        leftStingerMotor = new TorqueMotor(new VictorSP(Ports.leftStingerMotor), false, TorqueMotor.LinearizationType.kNone);
        rightStingerMotor = new TorqueMotor(new VictorSP(Ports.rightStingerMotor), false, TorqueMotor.LinearizationType.kNone);
    }

    //Drivebase
    @Override
    public void setDriveSpeeds(double left, double right, double strafe) {
        leftDriveMotorA.set(left);
        leftDriveMotorB.set(left);
        rightDriveMotorA.set(right);
        rightDriveMotorB.set(right);
        strafeMotor.set(strafe);
    }

    //Elevator
    @Override
    public void setElevatorMotorSpeeds(double speed) {
        leftElevatorMotorA.set(speed);
        rightElevatorMotorA.set(speed);
    }

    //Crazy Arms
    @Override
    public void setArmsOpen(boolean open) {
        openSolenoid.set((open) ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
    }

    @Override
    public void setTiltUp(boolean on) {
        tiltSolenoid.set(on ? Value.kReverse : Value.kForward);
    }

    @Override
    public void setPunchOut(boolean out) {
        punchSolenoid.set(out);
    }

    //Intake
    @Override
    public void setIntakeMotorSpeed(double left, double right) {
        leftIntakeMotor.set(left);
        rightIntakeMotor.set(right);
    }

    @Override
    public void setIntakeGrasp(boolean grasp) {
        intakeSolenoid.set(grasp);
    }

    @Override
    public void setStingerLatch(boolean latched) {
        double angle = (latched) ? 0.0 : 0.0;
        leftStingerServo.setAngle(angle);
        rightStingerServo.setAngle(angle);
    }

    @Override
    public void setStingerMotorSpeeds(double left, double right) {
        leftStingerMotor.set(left);
        rightStingerMotor.set(right);
    }
}
