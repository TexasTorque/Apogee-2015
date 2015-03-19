package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class TakeSomethingAuto extends AutoMode { 

    @Override
    public void run() {
        newPosition = true;
        elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
        wait(2.0);
        intakeState = Intake.OUTTAKE;
        
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();
        
        runCommand(new DriveDistance("drive forward", Constants.TakeSomethingFeet.getDouble(), distanceDoneRange, 10.0, 4.0));
        runCommand(new DriveDistance("drive back", -0.25, distanceDoneRange, 10.0, 1.0));
    }

}
