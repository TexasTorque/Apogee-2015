package org.texastorque.texastorque2015.subsystem;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePID;

public class Stingers extends Subsystem {
    
    private boolean latched;
    private boolean down;
    
    private TorquePID leftPID;
    private TorquePID rightPID;
    
    private double leftMotorSpeed;
    private double rightMotorSpeed;
    
    public Stingers() {
        
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        latched = input.isStingerLatched();
        down = input.areStingersDown();
        
        double armSetpoint = (down) ? 0.0 : 90.0;
        
        leftPID.setSetpoint(armSetpoint);
        rightPID.setSetpoint(armSetpoint);
        
        leftMotorSpeed = leftPID.calculate(feedback.getLeftStingerAngle());
        rightMotorSpeed = rightPID.calculate(feedback.getRightStingerAngle());

        output.setStingerLatch(latched);
        if (outputEnabled) {
            output.setStingerMotorSpeeds(leftMotorSpeed, rightMotorSpeed);
        } else {
            output.setStingerMotorSpeeds(0.0, 0.0);
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
    }
}
