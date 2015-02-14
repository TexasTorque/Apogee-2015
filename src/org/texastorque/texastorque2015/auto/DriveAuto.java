package org.texastorque.texastorque2015.auto;

public class DriveAuto extends AutoMode {

    //test auto to try out commands and raw inputs.
    @Override
    public void run() {
        System.out.println("Starting drive auto!");

        runCommand(new DriveDistance("DriveForward", 12, 0.25, 10, 2));
    }

}
