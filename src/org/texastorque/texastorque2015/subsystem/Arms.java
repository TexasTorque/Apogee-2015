package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePID;

public class Arms extends Subsystem {

    public static double UP = 0.0;
    public static double HORIZONTAL = 0.0;
    public static double DOWN = 0.0;

    //Solenoid states
    private boolean armsOpen;
    private boolean punchOut;

    //pid
    private double setPointAngle;

    private double leftAngle;
    private double leftMotorSpeed;
    private TorquePID leftPID;

    private double rightAngle;
    private double rightMotorSpeed;
    private TorquePID rightPID;

    public Arms() {
        leftPID = new TorquePID();
        rightPID = new TorquePID();
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        armsOpen = input.isArmOpen();
        punchOut = input.isPunchOut();

        leftAngle = feedback.getLeftTiltAngle();
        rightAngle = feedback.getRightTiltAngle();

        if (!input.isOverride()) {

            setPointAngle = input.getTiltAngle();

            leftPID.setSetpoint(setPointAngle);
            rightPID.setSetpoint(setPointAngle);

            leftMotorSpeed = leftPID.calculate(leftAngle);
            rightMotorSpeed = rightPID.calculate(rightAngle);
        } else {
            leftMotorSpeed = input.getTiltOverrideMotorSpeed();
            rightMotorSpeed = input.getTiltOverrideMotorSpeed();
        }

        if (outputEnabled) {
            output.setArmsOpen(armsOpen);
            output.setPunchOut(punchOut);
            output.setTiltMotorSpeeds(leftMotorSpeed, rightMotorSpeed);
        }
    }

    @Override
    public void loadParams() {
        UP = Constants.TiltUpAngle.getDouble();
        HORIZONTAL = Constants.TiltHorizontalAngle.getDouble();
        DOWN = Constants.TiltDownAngle.getDouble();

        leftPID.setPIDGains(Constants.TiltP.getDouble(),
                Constants.TiltI.getDouble(),
                Constants.TiltD.getDouble());
        rightPID.setPIDGains(Constants.TiltP.getDouble(),
                Constants.TiltI.getDouble(),
                Constants.TiltD.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("ArmsOpen", armsOpen);
        SmartDashboard.putBoolean("PunchOut", punchOut);
        SmartDashboard.putNumber("LeftTiltMotorSpeed", leftMotorSpeed);
        SmartDashboard.putNumber("RightTiltMotorSpeed", rightMotorSpeed);
        SmartDashboard.putNumber("TiltSetpoint", setPointAngle);
    }

    @Override
    public String getLogNames() {
        return "ArmsOpen, PunchOut, TiltMotorSpeed, ";
    }

    @Override
    public String getLogValues() {
        return armsOpen + ", " + punchOut + ", " + leftMotorSpeed + ", ";
    }

}
