package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePID;

public class Stingers extends Subsystem {
    
    private boolean down;
    
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
    }

    @Override
    public void run() {
        retractSpeed = input.getStingerRetractSpeed();
        down = input.areStingersDown();
        
        leftAngle = feedback.getLeftStingerAngle();
        rightAngle = feedback.getRightStingerAngle();
        
        setPoint = (down) ? 60.0 : 0.0;
        SmartDashboard.putNumber("S_setpoint", setPoint);
        
        leftPID.setSetpoint(setPoint);
        rightPID.setSetpoint(setPoint);
        
        leftMotorSpeed = leftPID.calculate(leftAngle);
        rightMotorSpeed = rightPID.calculate(rightAngle);

        if (outputEnabled) {
            output.setStingerMotorSpeeds(leftMotorSpeed, rightMotorSpeed, retractSpeed);
        } else {
            output.setStingerMotorSpeeds(0.0, 0.0, 0.0);
        }
    }

    @Override
    public String getLogNames() {
        return "";
    }

    @Override
    public String getLogValues() {
        return "";
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
    }
}
