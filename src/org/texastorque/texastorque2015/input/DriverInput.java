package org.texastorque.texastorque2015.input;

import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueFilter;
import org.texastorque.torquelib.util.TorqueToggle;

public class DriverInput extends Input {

    private GenericController driver;
    private GenericController operator;
    private OperatorConsole panel;

    private TorqueFilter driveAccelFilter;
    private TorqueFilter turnAccelFilter;

    private TorqueToggle tiltToggle;
    private TorqueToggle canHolderToggle;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.2);
        panel = new OperatorConsole(2);

        driveAccelFilter = new TorqueFilter(25);
        turnAccelFilter = new TorqueFilter(25);

        tiltToggle = new TorqueToggle();
        canHolderToggle = new TorqueToggle();

        override = false;
        punchOut = false;
        tiltUp = false;
    }

    @Override
    public void run() {
        if (operator.getLeftCenterButton()) {
            override = true;
        } else if (operator.getRightCenterButton()) {
            override = false;
        }

        if (override) {
            calcOverride();
        } else {
            calcElevator();
        }

        if (driver.getBButton()) {
            stingerRetractSpeed = 1.0;
        } else {
            stingerRetractSpeed = 0.0;
        }

        calcIntake();
        calcDrivebase();
        calcArms();
    }

    private void calcArms() {
        tiltToggle.calc(operator.getLeftBumper());
        tiltUp = tiltToggle.get();
        canHolderToggle.calc(operator.getLeftTrigger());
        canHolderUp = canHolderToggle.get();
    }

    private void calcDrivebase() {
        strafeSpeed = driver.getLeftXAxis();

        if (driver.getLeftBumper()) {
            driveAccelFilter.add(-driver.getLeftYAxis());
            turnAccelFilter.add(driver.getRightXAxis());

            leftSpeed = (driveAccelFilter.getAverage() + turnAccelFilter.getAverage()) / 2;
            rightSpeed = (driveAccelFilter.getAverage() - turnAccelFilter.getAverage()) / 2;
        } else {
            driveAccelFilter.reset();
            turnAccelFilter.reset();

            leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis();
            rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis();
        }
    }

    private void calcElevator() {
        if (operator.getRightTrigger()) {
            numTotes = 0;
            punchOut = false;
            newPosition = true;
            elevatorPosition = Constants.PlaceLevel1.getDouble();
        } else {
            punchOut = false;
            if (panel.getLevel1Button() || operator.getYButton()) {
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                newPosition = true;
            } else if (panel.getLevel2Button() || operator.getBButton()) {
                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                newPosition = true;
            } else if (panel.getLevel3Button()) {
                elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
                newPosition = true;
            } else if (panel.getLevel4Button()) {
                elevatorPosition = Constants.FloorElevatorLevel4.getDouble();
                newPosition = true;
            } else if (panel.getLevel5Button()) {
                elevatorPosition = Constants.FloorElevatorLevel5.getDouble();
                newPosition = true;
            } else if (panel.getLevel6Button()) {
                elevatorPosition = Constants.FloorElevatorLevel6.getDouble();
                newPosition = true;
            } else if (operator.getLeftStickClick()) {
                elevatorPosition = Constants.transportLevel.getDouble();
                newPosition = true;
            } else if (operator.getXButton()) {
                elevatorPosition = Constants.Carry4ToteLevel.getDouble();
                newPosition = true;
            } else {
                newPosition = false;
            }
        }
    }

    private void calcOverride() {
        overrideElevatorMotorSpeed = -1 * operator.getLeftYAxis();

        if (operator.getRightTrigger() && tiltUp) {
            punchOut = true;
        } else {
            punchOut = false;
        }
    }

    private void calcIntake() {
        intakeIn = operator.getRightBumper();

        leftIntakeSpeed = operator.getRightYAxis() - operator.getRightXAxis();
        rightIntakeSpeed = operator.getRightYAxis() + operator.getRightXAxis();
    }
}
