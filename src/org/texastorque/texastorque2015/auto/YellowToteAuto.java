package org.texastorque.texastorque2015.auto;

public class YellowToteAuto extends AutoMode {
    
    @Override
    public void run() {
        runCommand(new PickupTote("Pick up first tote.", 10));
        runCommand(new DriveDistance("Drive to second tote.", 10, 0.2, 10));
        runCommand(new PickupTote("Pick up second tote.", 10));
        runCommand(new DriveDistance("Drive to second tote.", 10, 0.2, 10));
        runCommand(new PickupTote("Pick up third tote", 10));
    }
    
}
