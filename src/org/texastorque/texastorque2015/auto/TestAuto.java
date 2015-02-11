package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
        wait(10.0);
        elevatorPosition = Constants.FloorElevatorLevel6.getDouble();
    }
}
