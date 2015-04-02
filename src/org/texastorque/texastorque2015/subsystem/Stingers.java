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
        leftMotorSpeed = rightMotorSpeed = input.getStingersSpeed();
        retractSpeed = input.getStingerRetractSpeed();

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
