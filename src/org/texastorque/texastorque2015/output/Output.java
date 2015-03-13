package org.texastorque.texastorque2015.output;

public abstract class Output {

    //Drivebase
    public abstract void setDriveSpeeds(double left, double right);

    //Elevator
    public abstract void setElevatorMotorSpeeds(double speed);

    //Crazy Arms
    public abstract void setArmsOpen(boolean open);

    public abstract void setPunchOut(boolean out);

    public abstract void setTiltMotorSpeed(double speed);

    //Intake
    public abstract void setIntakeMotorSpeed(double speed);

    public abstract void setIntakeGrasp(boolean grasp);
}
