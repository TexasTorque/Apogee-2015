package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class TwoCanGrabber extends AutoMode {

    @Override
    public void run() {
        //runCommand(new DriveDistance("touch drive", (-4.0/12.0), (0.3/12.0), 10, 3.0));
         leftSpeed = -0.35;
        rightSpeed = -0.35;
        
        wait(0.15);
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
        leftStingerSpeed = 1.0;
        rightStingerSpeed = 1.0;
        double time = Constants.leftStingerP.getDouble();
        
        wait(time);
        
        leftStingerSpeed = 0.0;
        rightStingerSpeed = 0.0;
        
        wait(2.0);
        
        leftSpeed = 0.175;
        rightSpeed = 0.175;
        
        wait(10.0);
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
        
//        wait(0.5);
//        
//        leftSpeed = 1.0;
//        rightSpeed = 1.0;
//        
//        wait(0.5);
//        
//        leftSpeed = 0.0;
//        rightSpeed = 0.0;
    }

}
