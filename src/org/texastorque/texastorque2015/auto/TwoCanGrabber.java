package org.texastorque.texastorque2015.auto;

public class TwoCanGrabber extends AutoMode {

    @Override
    public void run() {
        stingersOff = false;
        stingerSpeedOverride = 0.0;
        stingerAngle = 17.0;
        
        wait(0.250);//.25
        
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        
        wait(0.150);
        stingerAngle = 10.0;
        wait(0.150);
        
        //wait(0.5);//.75

        stingerAngle = 30.0;
        
        wait(0.200);
        
        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }

}
