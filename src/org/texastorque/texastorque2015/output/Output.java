package org.texastorque.texastorque2015.output;

public abstract class Output {

    public abstract void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe);

    public abstract void setElevatorMotorSpeeds(double speed);

    public abstract void setArmsOpen(boolean open);

    public abstract void setTiltUp(boolean on);

    public abstract void setPunchOut(boolean out);
    
    public abstract void setIntakeMotorSpeed(double speed);
}
