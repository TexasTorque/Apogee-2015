package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueFilter;

public class DriverInput extends Input {

    private GenericController driver;
    private GenericController operator;
    private OperatorConsole panel;

    private TorqueFilter driveAccelFilter;
    private TorqueFilter turnAccelFilter;

    private boolean wentDown;
    private boolean isAutoStack;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.2);
        panel = new OperatorConsole(2);

        driveAccelFilter = new TorqueFilter(25);
        turnAccelFilter = new TorqueFilter(25);

        override = false;
        armOpen = false;
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

        if (driver.getAButton()) {
            leftStingerSpeed = -0.25;
            rightStingerSpeed = 0.0;
        } else if (driver.getXButton()) {
            rightStingerSpeed = -0.25;
            leftStingerSpeed = 0.0;
        } else if (driver.getYButton()) {
            leftStingerSpeed = 0.25;
            rightStingerSpeed = 0.25;
        } else {
            leftStingerSpeed = 0.0;
            rightStingerSpeed = 0.0;
        }
        if (driver.getBButton()) {
            stingerRetractSpeed = 1.0;
        } else {
            stingerRetractSpeed = 0.0;
        }

        calcIntake();
        calcDrivebase();
    }

    //Drivebase
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
//        if (operator.getAButton()) {
//            autoStack = true;
//        }
//        if (operator.getXButton()) {
//            autoStack = false;
//            elevatorPosition = feedback.getElevatorHeight();
//            newPosition = true;
//            wentDown = false;
//        }
        if (operator.getAButton() && !autoStack) {
            armOpen = false;
            elevatorPosition = Constants.autoStackLevel.getDouble();
            newPosition = true;
            numTotes++;
            autoStack = true;
//            if (!wentDown && feedback.isElevatorHere(Constants.autoStackLevel.getDouble())) {
//                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
//                newPosition = true;
//                wentDown = true;
//
//                numTotes++;
//            } else if (!wentDown) {
//                elevatorPosition = Constants.autoStackLevel.getDouble();
//                newPosition = true;
//            } else if (wentDown && feedback.isElevatorHere(Constants.FloorElevatorLevel2.getDouble())) {
//                wentDown = false;
//                autoStack = false;
//            }
        } else if (operator.getRightTrigger()) {
            autoStack = false;
            wentDown = false;
            numTotes = 0;
            newPosition = true;
            elevatorPosition = Constants.PlaceLevel1.getDouble();
            if (feedback.isElevatorDone()) {
                armOpen = true;
            } else {
                armOpen = false;
            }
        } else {
            autoStack = false;
            wentDown = false;
            armOpen = false;
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
            } else {
                newPosition = false;
            }
        }

        armOpen = armOpen || operator.getLeftTrigger();
    }

    private void calcOverride() {
        overrideElevatorMotorSpeed = -1 * operator.getLeftYAxis();

        armOpen = operator.getLeftTrigger();
    }

    private void calcIntake() {
        intakeIn = operator.getRightBumper();

        leftIntakeSpeed = operator.getRightYAxis() - operator.getRightXAxis();
        rightIntakeSpeed = operator.getRightYAxis() + operator.getRightXAxis();
    }
}
