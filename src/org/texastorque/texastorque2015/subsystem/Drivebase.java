package org.texastorque.texastorque2015.subsystem;

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
    }
    
    @Override
    public void run() {
        leftSpeed = input.getLeftSpeed();
        rightSpeed = input.getRightSpeed();
        frontStrafeSpeed = input.getFrontStrafeSpeed();
        rearStrafeSpeed = input.getRearStrafeSpeed();
        
        if (outputEnabled) {
            output.setDriveSpeeds(leftSpeed, rightSpeed, frontStrafeSpeed, rearStrafeSpeed);
        }
    }
    
}
