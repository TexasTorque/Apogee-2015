package org.texastorque.texastorque2015.output;

public abstract class Output {

    public abstract void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe);

    public abstract void setElevatorMotorSpeeds(double speed);
    
    public abstract void setIntakeMotorSpeed(double speed);
}
