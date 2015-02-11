package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class DriverInput extends Input {

    GenericController driver;
    OperatorConsole operator;

    TorqueToggle tiltToggle;
    TorqueToggle armOpenToggle;

    private boolean wentToBottom;
    private double toteInTime;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new OperatorConsole(1);

        tiltToggle = new TorqueToggle();
        armOpenToggle = new TorqueToggle();
    }

    @Override
    public void run() {
        //Drivebase
        calcDrivebase();

        //Elevator
        elevatorOverride = operator.getElevatorOverrideSwitch();
        elevatorFFpOff = operator.getElevatorFFpOffSwitch();

        if (operator.getAutoStackButton()) {
            autoStack = true;
        }
        feederStack = operator.getFeederStackButton();

        //Calculate what all of the subsystems should do either independently or 
        //synchronized for complicated actions.
        if (elevatorOverride) {
            calcElevatorOverride();
            calcArms();
            calcIntake();
        } else if (autoStack) {
            armOpen = false;
            tiltUp = false;
            punchOut = false;
            
            if (elevatorPosition == Constants.FloorElevatorLevel1.getDouble() && feedback.isElevatorDone()) {
                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                wentToBottom = true;
                intakeSpeed = 0.0;
                intakesIn = false;
            } else if (elevatorPosition == Constants.FloorElevatorLevel2.getDouble() && wentToBottom && feedback.isElevatorDone()) {
                autoStack = false;
            } else {
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                intakeSpeed = 1.0;
                intakesIn = true;
            }
        } else if (feederStack) {
            double currentTime = Timer.getFPGATimestamp();
            
            elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
            armOpen = false;
            punchOut = false;
            tiltUp = false;
            
            if (feedback.isToteInSluice()) {
                toteInTime = Timer.getFPGATimestamp();
            }
            if (currentTime - Constants.ToteSluiceWaitTime.getDouble() > toteInTime) {
                intakeSpeed = 1.0;
                if (currentTime - Constants.ToteSluiceWaitTime.getDouble() - Constants.TotePullBAckTime.getDouble() > toteInTime) {
                    autoStack = true;
                }
            } else {
                if (feedback.isElevatorDone()) {
                    intakeSpeed = -1.0;
                    intakesIn = true;
                } else {
                    intakeSpeed = 0.0;
                    intakesIn = false;
                }
            }
        } else {
            calcElevator();
            calcArms();
            calcIntake();
        }
    }

//Drivebase
    private void calcDrivebase() {
        /**
         * Left stick controls translation, right stick controls rotation. Both
         * the forward and strafe wheels are utilized for rotation.
         */
        if (driver.getRightBumper()) { //Turn over front of robot
            leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis() * 0.1;
            rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis() * 0.1;
            frontStrafeSpeed = -1 * driver.getLeftXAxis() - driver.getRightXAxis() * 121 / 400;
            rearStrafeSpeed = -1 * driver.getLeftXAxis() + driver.getRightXAxis();
        } else if (driver.getLeftBumper()) { //Turn over back of robot
            leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis() * 0.1;
            rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis() * 0.1;
            frontStrafeSpeed = -1 * driver.getLeftXAxis() - driver.getRightXAxis();
            rearStrafeSpeed = -1 * driver.getLeftXAxis() + driver.getRightXAxis() * 100 / 841;
        } else { //Turn over center of robot
            leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis();
            rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis();
            frontStrafeSpeed = -1 * driver.getLeftXAxis() - driver.getRightXAxis() * 16 / 25;
            rearStrafeSpeed = -1 * driver.getLeftXAxis() + driver.getRightXAxis();
        }
    }

    //Elevator
    private void calcElevator() {
        if (operator.getPlaceButton()) {
            elevatorPosition = Constants.SPElevatorLevel1.getDouble();
        }
    }

    private void calcElevatorOverride() {
        if (operator.getElevatorUpButton()) {
            overrideElevatorMotorSpeed = 0.4;
        } else if (operator.getElevatorDownButton()) {
            overrideElevatorMotorSpeed = -0.4;
        }
    }

    //Arms
    private void calcArms() {
        tiltToggle.calc(operator.getTiltButton());
        tiltUp = tiltToggle.get();

        armOpenToggle.calc(operator.getArmOpenButton());
        if (operator.getPlaceButton()) {
            armOpen = true;
        } else {
            armOpen = armOpenToggle.get();
        }

        punchOut = operator.getPunchButton();
    }

    //Intake
    private void calcIntake() {
        if (operator.getIntakeButton()) {
            intakeSpeed = 1.0;
            intakesIn = true;
        } else {
            intakeSpeed = 0.0;
            intakesIn = false;
        }
    }
}
