package org.texastorque.texastorque2015.auto;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        System.out.println("Starting drive auto!");
        
        runCommand(new DriveDistance("DriveForward", 8, 0.25, 10));
    }
    
}
