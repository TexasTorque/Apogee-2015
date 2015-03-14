package org.texastorque.texastorque2015.output;

public abstract class Output {

    //Drivebase
    public abstract void setDriveSpeeds(double left, double right, double strage);

    //Elevator
    public abstract void setElevatorMotorSpeeds(double speed);

    //Crazy Arms
    public abstract void setArmsOpen(boolean open);

    public abstract void setPunchOut(boolean out);

    public abstract void setTiltMotorSpeeds(double left, double right);

    //Intake
    public abstract void setIntakeMotorSpeed(double speed);

    public abstract void setIntakeGrasp(boolean grasp);
}
