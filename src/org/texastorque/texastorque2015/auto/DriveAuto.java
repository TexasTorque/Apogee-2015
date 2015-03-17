package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class DriveAuto extends AutoMode {

    //test auto to try out commands and raw inputs.
    @Override
    public void run() {
        newPosition = true;
        elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
        wait(2.0);
        intakeState = Intake.OUTTAKE;
        runCommand(new DriveDistance("drive forward", 11.5, 1.0, 10.0, 5.0));
        runCommand(new DriveDistance("drive back", -0.25, 0.5, 10.0, 2.0));
    }

}
