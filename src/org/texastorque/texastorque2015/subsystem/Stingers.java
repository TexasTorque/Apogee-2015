package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Stingers extends Subsystem {
    
    private double leftMotorSpeed;
    private double rightMotorSpeed;
    private double retractSpeed;
    
    public Stingers() {
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        leftMotorSpeed = input.getLeftStingersSpeed();
        rightMotorSpeed = input.getRightStingerSpeed();
        retractSpeed = feedback.isLeftStingerUp() && feedback.isRightStingerUp() ? 0.0 : input.getStingerRetractSpeed();
        
        if (outputEnabled) {
            output.setStingerMotorSpeeds(leftMotorSpeed, rightMotorSpeed);
            output.setStingerRetractSpeed(retractSpeed);
        } else {
            output.setStingerMotorSpeeds(0.0, 0.0);
            output.setStingerRetractSpeed(0.0);
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
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("leftStingerMotorSpeed", leftMotorSpeed);
    }
}
