package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;

public class TestAuto extends AutoMode {

    @Override
    public void run() {
        newPosition = true;
        elevatorPosition = 0;
        elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
        
        intakeState = Intake.OPEN_ROLL_IN;
        
        runCommand(new DriveDistance("drive through first tote", 6.0, 0.25, 10, 5.0));
        
        intakeState = Intake.INTAKE;
    }
}
