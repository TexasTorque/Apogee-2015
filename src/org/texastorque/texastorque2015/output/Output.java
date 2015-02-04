package org.texastorque.texastorque2015.output;

public abstract class Output {

    //Drivebase
    public abstract void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe);

    //Elevator
    public abstract void setElevatorMotorSpeeds(double speed);

    //Crazy Arms
    public abstract void setArmsOpen(boolean open);

    public abstract void setTiltUp(boolean on);

    public abstract void setPunchOut(boolean out);

    //Intake
    public abstract void setIntakeMotorSpeed(double speed);
}
