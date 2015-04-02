package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class TwoCanGrabber extends AutoMode {

    @Override
    public void run() {
        stingersSpeed = 1.0;
        double time = Constants.leftStingerP.getDouble();
        
        wait(time);
        
        stingersSpeed = 0.0;
        
        wait(0.1);
        
//        leftSpeed = 1.0;
//        rightSpeed = 1.0;
//        
//        wait(1.0);
//        
//        leftSpeed = 0.0;
//        rightSpeed = 0.0;
    }

}
