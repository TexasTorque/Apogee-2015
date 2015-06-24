package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class OneToteAuto extends AutoMode {

    @Override
    public void run() {
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
        newPosition = true;
        wait(1.0);
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        wait(.05);
        leftSpeed = 0.0;
        rightSpeed = 0.0;
        wait(.5);
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
        newPosition = true;
        wait(2.0);
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
        newPosition = true;
        wait(2.0);
        leftSpeed = -1.0;
        rightSpeed = 1.0;
        wait(1.0);//turn 90 degrees TUNE
        leftSpeed = 1.0;
        rightSpeed = 1.0;
        wait(1.5);//drive into auto zone far enough TUNE
        leftSpeed = 0.0;
        rightSpeed = 0.0;
        wait(.5);
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
        newPosition = true;
        wait(1.0);
        leftSpeed = -1.0;
        rightSpeed = -1.0;
        wait(.25);
        leftSpeed = 0.0;
        rightSpeed = 0.0;
    }

}
