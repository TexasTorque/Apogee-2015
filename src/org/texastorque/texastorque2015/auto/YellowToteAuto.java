package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;

public class YellowToteAuto extends AutoMode {
    
    @Override
    public void run() {
        double turnDoneRange = Constants.turnDoneRange.getDouble();
        double distanceDoneRange = Constants.distanceDoneRange.getDouble();
        
        runCommand(new TurnAngle("turn left with first tote", Constants.FirstLeftTurnDegrees.getDouble(), turnDoneRange, 10, 4.0));
        runCommand(new DriveDistance("drive after first tote", Constants.DriveWithFirstToteFeet.getDouble(), distanceDoneRange, 10, 4.0));
        runCommand(new TurnAngle("turn right to second tote", Constants.TurnToSecondToteDegrees.getDouble(), turnDoneRange, 10, 10.0));
        runCommand(new DriveDistance("drive forward to second tote", Constants.DriveToSecondToteFeet.getDouble(),distanceDoneRange, 10, 4.0));
        runCommand(new DriveDistance("drive forward with second tote", Constants.DriveWithSecondToteFeet.getDouble(), distanceDoneRange, 10, 4.0));
        runCommand(new TurnAngle("turn left to third tote", Constants.TurnToThirdToteDegrees.getDouble(), turnDoneRange, 10, 4.0));
        runCommand(new DriveDistance("drive forward to third tote", Constants.DriveToThirdToteFeet.getDouble(), distanceDoneRange, 10, 4.0));
        runCommand(new TurnAngle("turn left to face auto zone", Constants.TurnWithThirdToteDegrees.getDouble(), turnDoneRange, 10, 4.0));
        runCommand(new DriveDistance("back up into auto zone", Constants.DriveTotesToZoneFeet.getDouble(), distanceDoneRange, 10, 4.0));
        runCommand(new DriveDistance("back away from totes", Constants.BackAwayFromTotesFeet.getDouble(), distanceDoneRange, 10, 4.0));
    }
    
}
