package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class DriveAuto extends AutoMode {

    //test auto to try out commands and raw inputs.
    @Override
    public void run() {
        System.out.println("Starting drive auto!");
        
        elevatorPosition = Constants.FloorElevatorLevel3.getDouble();

        leftSpeed = 1.0;
        rightSpeed = 1.0;
        
        wait(Constants.driveForwardTime.getDouble());
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
        
    }

}
