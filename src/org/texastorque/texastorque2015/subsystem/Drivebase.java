package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;

public class Drivebase extends Subsystem {

    //Motor speeds
    private double leftSpeed;
    private double rightSpeed;
    private double frontStrafeSpeed;
    private double rearStrafeSpeed;

    //Sensor values
    private double leftPosition;
    private double rightPosition;
    private double leftVelocity;
    private double rightVelocity;
    private double leftAcceleration;
    private double rightAcceleration;

    //Control loop stuff
    private double setPointPosition;
    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private TorqueTMP profile;
    private TorquePV leftPV;
    private TorquePV rightPV;

    public Drivebase() {
        profile = new TorqueTMP(Constants.DrivebaseMaxV.getDouble(), Constants.DrivebaseMaxA.getDouble());
        leftPV = new TorquePV();
        rightPV = new TorquePV();
    }

    @Override
    public void loadParams() {
        profile = new TorqueTMP(Constants.DrivebaseMaxV.getDouble(), Constants.DrivebaseMaxA.getDouble());

        //pv
        leftPV.setGains(Constants.DrivebaseLeftP.getDouble(), Constants.DrivebaseLeftV.getDouble(),
                Constants.DrivebaseLeftffV.getDouble(), Constants.DrivebaseLeftffA.getDouble());
        rightPV.setGains(Constants.DrivebaseRightP.getDouble(), Constants.DrivebaseRightV.getDouble(),
                Constants.DrivebaseRightffV.getDouble(), Constants.DrivebaseRightffA.getDouble());
        leftPV.setTunedVoltage(Constants.DrivebaseTunedVoltage.getDouble());
        rightPV.setTunedVoltage(Constants.DrivebaseTunedVoltage.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);
        SmartDashboard.putNumber("FrontStrafeSpeed", frontStrafeSpeed);
        SmartDashboard.putNumber("RearStrafeSpeed", rearStrafeSpeed);
        SmartDashboard.putNumber("LeftPosition", leftPosition);
        SmartDashboard.putNumber("RightPosition", rightPosition);
        SmartDashboard.putNumber("LeftVelocity", leftVelocity);
        SmartDashboard.putNumber("RightVelocity", rightVelocity);
        SmartDashboard.putNumber("LeftAcceleration", leftAcceleration);
        SmartDashboard.putNumber("RightAcceleration", rightAcceleration);
        SmartDashboard.putNumber("TargetPosition", targetPosition);
        SmartDashboard.putNumber("TargetVelocity", targetVelocity);
        SmartDashboard.putNumber("TargetAcceleration", targetAcceleration);
    }

    @Override
    public void enable() {
        setPointPosition = 0.0;
        profile.generateTrapezoid(0.0, 0.0, (leftVelocity + rightVelocity) / 2);
        feedback.resetDriveEncoders();
    }

    @Override
    public void run() {
        leftPosition = feedback.getLeftDrivePosition();
        rightPosition = feedback.getRightDrivePosition();

        leftVelocity = feedback.getLeftDriveVelocity();
        rightVelocity = feedback.getRightDriveVelocity();

        leftAcceleration = feedback.getLeftDriveAcceleration();
        rightAcceleration = feedback.getRightDriveAcceleration();

        /**
         * Drive directions are defined as following: +1 for leftSpeed and
         * rightSpeed: full forward -1 for leftSpeed and rightSpeed: full
         * reverse +1 for strafe: full right -1 for strafe: full left
         *
         */
        if (input.isDrivebaseControlled()) {
            if (setPointPosition != input.getDriveDistance()) {
                setPointPosition = input.getDriveDistance();

                profile.generateTrapezoid(setPointPosition, 0.0, (leftVelocity + rightVelocity) / 2);

                feedback.resetDriveEncoders();
            }

            profile.calculateNextSituation();

            targetVelocity = profile.getCurrentVelocity();
            targetPosition = profile.getCurrentPosition();
            targetAcceleration = profile.getCurrentAcceleration();

            leftSpeed = leftPV.calculate(profile, leftPosition, leftVelocity);
            rightSpeed = rightPV.calculate(profile, rightPosition, rightVelocity);

        } else {
            leftSpeed = input.getLeftSpeed();
            rightSpeed = input.getRightSpeed();
            frontStrafeSpeed = input.getFrontStrafeSpeed();
            rearStrafeSpeed = input.getRearStrafeSpeed();

            targetPosition = 0.0;
            targetVelocity = 0.0;
        }

        if (outputEnabled) {
            output.setDriveSpeeds(leftSpeed, rightSpeed, frontStrafeSpeed, rearStrafeSpeed);
        } else {
            output.setDriveSpeeds(0.0, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public String getLogNames() {
        return "LeftSpeed, RightSpeed, FrontStrafeSpeed, RearStrafeSpeed, " +
                "LeftPosition, RightPosition, LeftVelocity, RightVelocity, TargetPosition, TargetVelocity, ";
    }

    @Override
    public String getLogValues() {
        return leftSpeed + ", " + rightSpeed + ", " + frontStrafeSpeed + ", " + rearStrafeSpeed + ", "
                + leftPosition + ", " + rightPosition + ", " + leftVelocity + ", "
                + rightVelocity + ", " + targetPosition + ", " + targetVelocity + ", ";
    }

}
