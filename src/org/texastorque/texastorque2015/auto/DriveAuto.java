package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class DriveAuto extends AutoMode {

    @Override
    public void run() {
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();
        runCommand(new DriveDistance("forward", Constants.DriveForwardFeet.getDouble(), distanceDoneRange, 10.0, 4.0));
    }
}
