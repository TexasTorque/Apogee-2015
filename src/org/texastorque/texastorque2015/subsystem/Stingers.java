package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePID;

public class Stingers extends Subsystem {

    private TorquePID leftPID;
    private TorquePID rightPID;

    private double leftMotorSpeed;
    private double rightMotorSpeed;
    private double retractSpeed;

    private double leftAngle;
    private double rightAngle;
    private double setPoint;

    public Stingers() {
        leftPID = new TorquePID();
        rightPID = new TorquePID();
    }

    @Override
    public void init() {
        leftAngle = 0.0;
        setPoint = 0.0;
        leftPID.reset();
        leftPID.setSetpoint(setPoint);

        rightAngle = 0.0;
        rightPID.reset();
        rightPID.setSetpoint(setPoint);
    }

    @Override
    public void run() {
        retractSpeed = input.getStingerRetractSpeed();

        leftAngle = feedback.getLeftStingerAngle();
        rightAngle = feedback.getRightStingerAngle();

        setPoint = input.getStingerAngle();

        leftPID.setSetpoint(setPoint);
        rightPID.setSetpoint(setPoint);

        if (input.getStingerSpeedOverride() == 0.0) {
                leftMotorSpeed = leftPID.calculate(leftAngle);
                rightMotorSpeed = rightPID.calculate(rightAngle);
        } else {
            leftMotorSpeed = rightMotorSpeed = input.getStingerSpeedOverride();
        }

        if (DriverStation.getInstance().isOperatorControl()) {
            leftMotorSpeed = Math.min(0.5, leftMotorSpeed);
            rightMotorSpeed = Math.min(0.5, rightMotorSpeed);
        }

        if (outputEnabled && !input.areStingersOff()) {
            output.setStingerMotorSpeeds(leftMotorSpeed, rightMotorSpeed, retractSpeed);
        } else {
            output.setStingerMotorSpeeds(0.0, 0.0, 0.0);
        }
    }

    @Override
    public String getLogNames() {
        return "S_R_MotorSpeed, S_L_MotorSpeed, S_R_Angle, S_L_Angle";
    }

    @Override
    public String getLogValues() {
        return rightMotorSpeed + "," + leftMotorSpeed + "," + rightAngle + "," + leftAngle;
    }

    @Override
    public void loadParams() {
        leftPID.setPIDGains(Constants.leftStingerP.getDouble(), Constants.leftStingerI.getDouble(), Constants.leftStingerP.getDouble());
        rightPID.setPIDGains(Constants.rightStingerP.getDouble(), Constants.rightStingerI.getDouble(), Constants.rightStingerD.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("leftStingerAngle", leftAngle);
        SmartDashboard.putNumber("rightStingerAngle", rightAngle);
        SmartDashboard.putNumber("leftStingerMotorSpeed", leftMotorSpeed);
        SmartDashboard.putNumber("rightStingerMotorSpeed", rightMotorSpeed);
    }
}
