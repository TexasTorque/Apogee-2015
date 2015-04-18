package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        double turnDoneRange = Constants.turnDoneRange.getDouble();
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();

        newPosition = true;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

        //intakeState = Intake.INTAKE;

        runCommand(new DriveDistance("drive through first tote", Constants.DriveWithFirstToteFeet.getDouble(), distanceDoneRange, 10, 5.0));
        runCommand(new TurnAngle("turn right", Constants.TurnToSecondToteDegrees.getDouble(), turnDoneRange, 10, 5.0));

        wait(0.25);

        //intakeState = Intake.OFF;

        //wait for elevator to lower
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }

        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

        wait(0.5);

        runCommand(new DriveDistance("drive forward with first tote", 0.5, distanceDoneRange, 10, 2.0));
        
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }
        
        runCommand(new TurnAngle("turn right", Constants.TurnToSecondToteDegrees.getDouble(), turnDoneRange, 10, 5.0));
        
        //intakeState = Intake.OPEN_ROLL_IN;
        
        runCommand(new DriveDistance("drive through second tote", Constants.DriveToSecondToteFeet.getDouble(), distanceDoneRange, 10, 6.0));
        
        //intakeState = Intake.INTAKE;
//        
//        wait(0.5);
//        
//        armOpen = true;
//        elevatorPosition = Constants.autoStackLevel.getDouble();
//        intakeState = Intake.OFF;
//        
//        //wait for elevator to lower
//        while (!feedback.isElevatorHere(elevatorPosition)) {
//            wait(0.05);
//        }
//        
//        armOpen = false;
//        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
    }
}
