package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private boolean wentDown;
    private boolean isAutoStack;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.2);
        panel = new OperatorConsole(2);

        driveAccelFilter = new TorqueFilter(25);
        turnAccelFilter = new TorqueFilter(25);
        
        tiltToggle = new TorqueToggle();

        override = false;
        armOpen = false;
        punchOut = false;
        tiltUp = false;
    }

    @Override
    public void run() {
        tiltToggle.calc(operator.getLeftBumper());
        tiltUp = tiltToggle.get();
        
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
        if (operator.getAButton()) {
            //autoStack = bring elevator down and back up to stack tote
            tiltUp = false;
            punchOut = false;

            newPosition = true;
            if (feedback.isElevatorHere(Constants.autoStackLevel.getDouble()) && !wentDown) {
                SmartDashboard.putNumber("bla", 0);
                armOpen = false;

                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();

                numTotes++;
                wentDown = true;
            } else if (feedback.isElevatorDone() && wentDown) {
                SmartDashboard.putNumber("bla", 1);
                autoStack = false;
                wentDown = false;
            } else if (elevatorPosition == Constants.FloorElevatorLevel2.getDouble() && wentDown) {
                //catch else to make sure that nothing happens if elevator is not donee
                SmartDashboard.putNumber("bla", 2);
            } else {
                SmartDashboard.putNumber("bla", 3);
                elevatorPosition = Constants.autoStackLevel.getDouble();

                armOpen = feedback.getElevatorHeight() < Constants.autoStackArmOpenLevel.getDouble();
            }
        } else if (operator.getRightTrigger()) {
            autoStack = false;
            wentDown = false;
            numTotes = 0;
            newPosition = true;
            elevatorPosition = Constants.PlaceLevel1.getDouble();
            armOpen = feedback.isElevatorDone();
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
