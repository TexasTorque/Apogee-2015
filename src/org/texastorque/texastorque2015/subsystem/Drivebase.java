package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivebase extends Subsystem {
    
    private double leftSpeed;
    private double rightSpeed;
    private double frontStrafeSpeed;
    private double rearStrafeSpeed;
    
    @Override
    public void loadParams() {
    }
    
    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("LeftSpeed", leftSpeed);
        SmartDashboard.putNumber("RightSpeed", rightSpeed);
        SmartDashboard.putNumber("FrontStrafeSpeed", frontStrafeSpeed);
        SmartDashboard.putNumber("RearStrafeSpeed", rearStrafeSpeed);
    }
    
    @Override
    public void run() {
        /**
         * Drive directions are defined as following:
         * +1 for leftSpeed and rightSpeed: full forward
         * -1 for leftSpeed and rightSpeed: full reverse
         * +1 for strafe: full right
         * -1 for strafe: full left
         * 
         */
        leftSpeed = input.getLeftSpeed();
        rightSpeed = input.getRightSpeed();
        frontStrafeSpeed = input.getFrontStrafeSpeed();
        rearStrafeSpeed = input.getRearStrafeSpeed();
        
        if (outputEnabled) {
            output.setDriveSpeeds(leftSpeed, rightSpeed, frontStrafeSpeed, rearStrafeSpeed);
        }
    }
    
}
