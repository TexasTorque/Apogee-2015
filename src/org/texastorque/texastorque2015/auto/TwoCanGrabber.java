package org.texastorque.texastorque2015.auto;

public class TwoCanGrabber extends AutoMode {

    @Override
    public void run() {
        stingerLatch = true;
        stingersDown = true;
        
        wait(2.0);
        
        stingerLatch = false;
//        
//        leftSpeed = 1.0;
//        rightSpeed = 1.0;
//        
//        wait(1.0);
//        
//        leftSpeed = 0.0;
//        rightSpeed = 0.0;
    }

}