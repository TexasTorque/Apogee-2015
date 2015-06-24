package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        wait(.2);
        leftSpeed = 0.0;
        rightSpeed = 0.0;
//        double distanceDoneRange = Constants.distanceDoneRange.getDouble();
//        runCommand(new DriveDistance("forward", Constants.DriveForwardFeet.getDouble(), distanceDoneRange, 10.0, 10.0));
    }
}
