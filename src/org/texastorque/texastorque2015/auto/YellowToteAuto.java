package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class YellowToteAuto extends AutoMode {

    @Override
    public void run() {
        double turnDoneRange = Constants.turnDoneRange.getDouble();
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();

        newPosition = true;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
        armOpen = true;
        intakeState = Intake.INTAKE;

        wait(1.0);

        intakeState = Intake.OFF;

        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();

        //wait for elevator to lower
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }

        //close and lift tote
        armOpen = false;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

        runCommand(new TurnAngle("turn left with first tote", Constants.FirstLeftTurnDegrees.getDouble(), turnDoneRange, 10, 4.0));
        runCommand(new DriveDistance("drive after first tote", Constants.DriveWithFirstToteFeet.getDouble(), distanceDoneRange, 10, 4.0));
        
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }
        
        runCommand(new TurnAngle("turn right to second tote", Constants.TurnToSecondToteDegrees.getDouble(), turnDoneRange, 10, 10.0));

        intakeState = Intake.OPEN_ROLL_IN;

        runCommand(new DriveDistance("drive forward to second tote", Constants.DriveToSecondToteFeet.getDouble(), distanceDoneRange, 10, 4.0));

        intakeState = Intake.INTAKE;

        wait(0.5);

        intakeState = Intake.OFF;
        armOpen = true;
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();

        //wait for elevator to lower
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }

        //close and lift tote
        armOpen = false;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

        runCommand(new DriveDistance("drive forward with second tote", Constants.DriveWithSecondToteFeet.getDouble(), distanceDoneRange, 10, 4.0));
        runCommand(new TurnAngle("turn left to third tote", Constants.TurnToThirdToteDegrees.getDouble(), turnDoneRange, 10, 4.0));

        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }

        intakeState = Intake.OPEN_ROLL_IN;

        runCommand(new DriveDistance("drive forward to third tote", Constants.DriveToThirdToteFeet.getDouble(), distanceDoneRange, 10, 4.0));

        intakeState = Intake.INTAKE;

        wait(0.5);
        
        intakeState = Intake.OFF;
        armOpen = true;
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();

        //wait for elevator to lower
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }

        //close and lift tote
        armOpen = false;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

        runCommand(new TurnAngle("turn left to face auto zone", Constants.TurnWithThirdToteDegrees.getDouble(), turnDoneRange, 10, 4.0));
        runCommand(new DriveDistance("back up into auto zone", Constants.DriveTotesToZoneFeet.getDouble(), distanceDoneRange, 10, 4.0));
        
        elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
        
        while (!feedback.isElevatorHere(elevatorPosition)) {
            wait(0.05);
        }
        
        armOpen = true;
        intakeState = Intake.OUTTAKE;
        
        wait(0.5);
        
        runCommand(new DriveDistance("back away from totes", Constants.BackAwayFromTotesFeet.getDouble(), distanceDoneRange, 10, 4.0));
    }

}
