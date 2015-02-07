package org.texastorque.texastorque2015.auto;

public class TurnAuto extends AutoMode {

    @Override
    public void run() {
        System.out.println("Starting turn auto!");

        runCommand(new TurnAngle("TurnLeft", 180, 1, 10));
    }
}
