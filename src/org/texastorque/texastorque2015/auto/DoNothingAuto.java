package org.texastorque.texastorque2015.auto;

public class DoNothingAuto extends AutoMode {

    @Override
    public void run() {
        stingersOff = true;
        System.out.println("Do nothing auto started!");
    }
}
