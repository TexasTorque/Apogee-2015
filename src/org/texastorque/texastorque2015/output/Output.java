package org.texastorque.texastorque2015.output;

public abstract class Output {

    //Drivebase
    public abstract void setDriveSpeeds(double left, double right, double strage);

    //Elevator
    public abstract void setElevatorMotorSpeeds(double speed);

    //Crazy Arms
    public abstract void setTiltUp(boolean on);

    public abstract void setPunchOut(boolean out);
    
    public abstract void setCanHolderUp(boolean up);

    //Intake
    public abstract void setIntakeMotorSpeed(double left, double right);

    public abstract void setIntakeGrasp(boolean grasp);
    
    //Stingers
    public abstract void setStingerMotorSpeeds(double left, double right, double retract);
}
