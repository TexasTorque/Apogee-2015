package org.texastorque.texastorque2015.auto;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new TurnAngle("test", 360.0, 5.0, 10, 30.0));
    }
}
