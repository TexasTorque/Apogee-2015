package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class TakeSomethingAuto extends AutoMode { 

    @Override
    public void run() {
        armOpen = false;
        
        leftSpeed = 1.0;
        rightSpeed = -1.0;
        
        wait(Constants.turnTime.getDouble());
        
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        
        wait(Constants.driveForwardTime.getDouble());
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }

}
