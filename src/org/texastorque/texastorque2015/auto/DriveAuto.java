package org.texastorque.texastorque2015.auto;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new DriveDistance("forward", 6.0, 0.1, 10.0, 5.0));
    }
}
