package org.texastorque.texastorque2015.auto;

public class PickupToteAuto extends AutoMode {

    @Override
    public void run() {
        runCommand(new PickupTote("PickupTote", 10));
    }

}
