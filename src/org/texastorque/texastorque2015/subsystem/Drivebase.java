package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;

public class Drivebase extends Subsystem {

    //Motor speeds
    private double leftSpeed;
    private double rightSpeed;
    private double strafeSpeed;

    //Sensor values (linear)
    private double leftPosition;
    private double rightPosition;
    private double leftVelocity;
    private double rightVelocity;
    private double leftAcceleration;
    private double rightAcceleration;

    //Sensor values (angular)
    private double angle;
    private double angularVelocity;

    //Control loop stuff (linear)
    private double setPointPosition;
    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    //Control loop stuff (angular)
    private double setPointAngle;
    private double targetAngle;
    private double targetAngularVelocity;
    private double targetAngularAcceleration;

    //linear
    private TorqueTMP linearProfile;
    private TorquePV leftPV;
    private TorquePV rightPV;

    //angular
    private TorqueTMP angularProfile;
    private TorquePV turnPV;

    double prevTime;

    public Drivebase() {
        linearProfile = new TorqueTMP(Constants.DrivebaseMaxV.getDouble(), Constants.DrivebaseMaxA.getDouble());
        angularProfile = new TorqueTMP(Constants.DrivebaseMaxAngleV.getDouble(), Constants.DrivebaseMaxAngleA.getDouble());
        leftPV = new TorquePV();
        rightPV = new TorquePV();
        turnPV = new TorquePV();
    }

    //Resets the TMP so that the robot does not try to move. Called every time the robot enables.
    @Override
    public void init() {
        setPointPosition = 0.0;
        setPointAngle = 0.0;
        linearProfile.generateTrapezoid(0.0, 0.0, (leftVelocity + rightVelocity) / 2);
        angularProfile.generateTrapezoid(0.0, 0.0, angle);
        feedback.resetDriveEncoders();
        feedback.resetGyro();

        prevTime = Timer.getFPGATimestamp();
    }

    @Override
    public void run() {
        leftPosition = feedback.getLeftDrivePosition();
        rightPosition = feedback.getRightDrivePosition();

        leftVelocity = feedback.getLeftDriveVelocity();
        rightVelocity = feedback.getRightDriveVelocity();

        leftAcceleration = feedback.getLeftDriveAcceleration();
        rightAcceleration = feedback.getRightDriveAcceleration();

        angle = feedback.getAngle();
        angularVelocity = feedback.getAngularVelocity();

        /**
         * Drive directions are defined as following: +1 for leftSpeed and
         * rightSpeed: full forward -1 for leftSpeed and rightSpeed: full
         * reverse +1 for strafe: full right -1 for strafe: full left
         *
         */
        double dt = Timer.getFPGATimestamp() - prevTime;
        prevTime = Timer.getFPGATimestamp();

        //Linear control loop operation
        if (input.isDrivebaseControlled() && input.getDriveAngle() == 0.0) {
            if (setPointPosition != input.getDriveDistance()) {
                setPointPosition = input.getDriveDistance();

                linearProfile.generateTrapezoid(setPointPosition, 0.0, (leftVelocity + rightVelocity) / 2);

                feedback.resetDriveEncoders();
            }

            linearProfile.calculateNextSituation(dt);

            targetVelocity = linearProfile.getCurrentVelocity();
            targetPosition = linearProfile.getCurrentPosition();
            targetAcceleration = linearProfile.getCurrentAcceleration();

            leftSpeed = leftPV.calculate(linearProfile, leftPosition, leftVelocity);
            rightSpeed = rightPV.calculate(linearProfile, rightPosition, rightVelocity);

            //Turning control loop operation
        } else if (input.isDrivebaseControlled()) {
            if (setPointAngle != input.getDriveAngle()) {
                setPointAngle = input.getDriveAngle();

                angularProfile.generateTrapezoid(setPointAngle, 0.0, angularVelocity);

                feedback.resetGyro();
            }

            angularProfile.calculateNextSituation(dt);

            targetAngularVelocity = angularProfile.getCurrentVelocity();
            targetAngle = angularProfile.getCurrentPosition();
            targetAngularAcceleration = angularProfile.getCurrentAcceleration();

            leftSpeed = turnPV.calculate(angularProfile, angle, angularVelocity);
            rightSpeed = -leftSpeed;
        } else {
            //Regular teleop control
            leftSpeed = input.getLeftSpeed();
            rightSpeed = input.getRightSpeed();
            strafeSpeed = input.getStrafeSpeed();

            targetPosition = 0.0;
            targetVelocity = 0.0;
        }

        if (outputEnabled) {
            output.setDriveSpeeds(leftSpeed, rightSpeed, strafeSpeed);
        } else {
            output.setDriveSpeeds(0.0, 0.0, 0.0);
        }
    }

    @Override
    public void loadParams() {
        linearProfile = new TorqueTMP(Constants.DrivebaseMaxV.getDouble(), Constants.DrivebaseMaxA.getDouble());
        angularProfile = new TorqueTMP(Constants.DrivebaseMaxAngleV.getDouble(), Constants.DrivebaseMaxAngleA.getDouble());

        //pv
        leftPV.setGains(Constants.DrivebaseLeftP.getDouble(), Constants.DrivebaseLeftV.getDouble(),
                Constants.DrivebaseLeftffV.getDouble(), Constants.DrivebaseLeftffA.getDouble());
        rightPV.setGains(Constants.DrivebaseRightP.getDouble(), Constants.DrivebaseRightV.getDouble(),
                Constants.DrivebaseRightffV.getDouble(), Constants.DrivebaseRightffA.getDouble());
        leftPV.setTunedVoltage(Constants.DrivebaseTunedVoltage.getDouble());
        rightPV.setTunedVoltage(Constants.DrivebaseTunedVoltage.getDouble());
        //set turn gains
        turnPV.setTunedVoltage(Constants.DrivebaseTunedVoltage.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);
        SmartDashboard.putNumber("LeftPosition", leftPosition);
        SmartDashboard.putNumber("RightPosition", rightPosition);
        SmartDashboard.putNumber("LeftVelocity", leftVelocity);
        SmartDashboard.putNumber("RightVelocity", rightVelocity);
        SmartDashboard.putNumber("LeftAcceleration", leftAcceleration);
        SmartDashboard.putNumber("RightAcceleration", rightAcceleration);
        SmartDashboard.putNumber("TargetPosition", targetPosition);
        SmartDashboard.putNumber("TargetVelocity", targetVelocity);
        SmartDashboard.putNumber("TargetAcceleration", targetAcceleration);
        SmartDashboard.putNumber("TargetAngle", targetAngle);
        SmartDashboard.putNumber("TargetAngularVelocity", targetAngularVelocity);
        SmartDashboard.putNumber("TargetAngularAcceleration", targetAngularAcceleration);
    }

    @Override
    public String getLogNames() {
        return "LeftSpeed, RightSpeed, "
                + "LeftPosition, RightPosition, LeftVelocity, RightVelocity, TargetPosition, TargetVelocity, ";
    }

    @Override
    public String getLogValues() {
        return leftSpeed + ", " + rightSpeed + ", "
                + leftPosition + ", " + rightPosition + ", " + leftVelocity + ", "
                + rightVelocity + ", " + targetPosition + ", " + targetVelocity + ", ";
    }

}
