package org.texastorque.texastorque2015.auto;

public class TakeSomethingAuto extends AutoMode { 

    @Override
    public void run() {
        armOpen = false;
        
        runCommand(new TurnAngle("turn right", 90.0, 10.0, 10.0, 5.0));
        runCommand(new DriveDistance("drive forward", 6.0, 1.0, 10.0, 5.0));
    }

}
