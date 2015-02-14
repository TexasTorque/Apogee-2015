package org.texastorque.texastorque2015.auto;

public class YellowToteAuto extends AutoMode {
    
    @Override
    public void run() {
        runCommand(new ToteStack("Pick up first tote.", 10, 2));
        runCommand(new DriveDistance("Drive to second tote.", 10, 0.2, 10, 2));
        runCommand(new ToteStack("Pick up second tote.", 10, 2));
        runCommand(new DriveDistance("Drive to second tote.", 10, 0.2, 10, 2));
        runCommand(new ToteStack("Pick up third tote", 10, 2));
    }
    
}
