package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class TakeSomethingAuto extends AutoMode { 

    @Override
    public void run() {
        newPosition = true;
        elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
        intakeState = Intake.INTAKE;
        
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();
        double turnDoneRange = Constants.turnDoneRange.getDouble();
        
        runCommand(new TurnAngle("turn right", Constants.turnRightDegrees.getDouble(), turnDoneRange, 10, 3.0));
        
        runCommand(new DriveDistance("drive forward", Constants.TakeSomethingFeet.getDouble(), distanceDoneRange, 10, 4.0));
        
        intakeState = Intake.OUTTAKE;
        
        runCommand(new DriveDistance("drive back", -0.5, distanceDoneRange, 10.0, 1.0));
    }

}
