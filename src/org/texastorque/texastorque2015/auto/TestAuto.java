package org.texastorque.texastorque2015.auto;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new DriveDistance("test", 25.0, 0.25, 10, 30.0));
    }
}
