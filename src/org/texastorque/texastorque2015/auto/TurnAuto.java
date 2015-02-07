package org.texastorque.texastorque2015.auto;

public class TurnAuto extends AutoMode {

    @Override
    public void run() {
        System.out.println("Starting drive auto!");

        runCommand(new TurnAngle("TurnLeft", 12, 0.25, 10));
    }
}
