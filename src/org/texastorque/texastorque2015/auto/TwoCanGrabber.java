package org.texastorque.texastorque2015.auto;

public class TwoCanGrabber extends AutoMode {

    @Override
    public void run() {
        stingersDown = true;
        stingersMiddle = false;
        
        wait(0.25);//.25
        
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        
        wait(0.75);
        
        stingersDown = false;
        stingersMiddle = true;
        
        wait(0.2);
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }

}
