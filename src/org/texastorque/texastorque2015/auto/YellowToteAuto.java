package org.texastorque.texastorque2015.auto;

public class YellowToteAuto extends AutoMode {
    
    @Override
    public void run() {
        runCommand(new TurnAngle("turn left", -30.0, 20.0, 10.0, 30.0));
        runCommand(new DriveDistance("drive after first tote", 4.0, 1.0, 10, 5.0));
        runCommand(new TurnAngle("turn right", 70.0, 20.0, 10.0, 10.0));
        runCommand(new DriveDistance("drive forward", 10.0, 1.0, 10.0, 20.0));
    }
    
}
