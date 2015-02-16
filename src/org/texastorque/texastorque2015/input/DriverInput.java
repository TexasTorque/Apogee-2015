package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class DriverInput extends Input {

    GenericController driver;
    OperatorConsole operator;

    TorqueToggle tiltToggle;

    private boolean wentToBottom;
    private double toteInTime;
    private boolean toteAvailable;
    private double autoStackHeight;
    private boolean coopStack;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new OperatorConsole(1);

        tiltToggle = new TorqueToggle();

        wentToBottom = false;
        toteAvailable = false;
        autoStackHeight = 0.0;
    }

    @Override
    public boolean newPosition() {
        return newPosition;
    }

    @Override
    public void run() {
        //Drivebase
        calcDrivebase();

        //Elevator
        if (operator.getElevatorDownButton() || operator.getElevatorUpButton()) {
            elevatorFFpOff = override = true;
        }

        if (operator.getAutoStackButton()) {
            autoStack = true;
        }
        feederStack = operator.getFeederStackButton();

        if (override) {
            calcOverride();
            calcArmsOverride();
            calcIntake();
        } else if (autoStack) {
            //autoStack = bring elevator down and back up to stack tote
            armOpen = false;
            tiltUp = false;
            punchOut = false;

            if (feedback.isElevatorHere(Constants.FloorElevatorLevel1.getDouble())) {
                // go back up to get ready for another tote in from underneath
                // if autoStacking from feederStack, go back to level 3, else go to 2
                if (feederStack) {
                    autoStackHeight = Constants.FloorElevatorLevel3.getDouble();
                } else {
                    autoStackHeight = Constants.FloorElevatorLevel2.getDouble();
                }
                elevatorPosition = autoStackHeight;
                wentToBottom = true;
                intakeSpeed = 0.0;
                intakesIn = false;
            } else if (feedback.isElevatorHere(autoStackHeight) && wentToBottom) {
                //if autoStacking cycle has been finished, reset
                autoStack = false;
                toteAvailable = false;
                wentToBottom = false;
            } else if (elevatorPosition == autoStackHeight && wentToBottom) {
                //catch else to make sure that nothing happens if elevator is not done
            } else {
                //intake the tote while the elevator is moving down
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                intakeSpeed = 1.0;
                intakesIn = true;
            }
        } else if (feederStack) {
            //feederStack means intake tote from sluice, outtake it, re-intake it, then autoStack
            double currentTime = Timer.getFPGATimestamp();

            //setup feederStack cycle
            elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
            armOpen = false;
            punchOut = false;
            tiltUp = false;

            if (feedback.isToteInSluice() && !toteAvailable) {
                //get the time for the start of the feederstack cycle
                toteInTime = Timer.getFPGATimestamp();
                toteAvailable = true;
            } else if (toteAvailable) {
                //can intake tote
                if (currentTime - Constants.ToteSluiceWaitTime.getDouble() > toteInTime) {
                    if (currentTime - Constants.ToteSluiceWaitTime.getDouble() - Constants.TotePullBAckTime.getDouble() > toteInTime) {
                        //wait for the elevator to be ready
                        autoStack = feedback.isElevatorDone();
                    } else {
                        //pull the tote back in to get ready for the elevator to pick it up
                        intakeSpeed = 1.0;
                    }
                } else {
                    //Reverse intake to make sure the tote comes off of the sluice
                    intakeSpeed = -1.0;
                    intakesIn = true;
                }
            }
        } else {
            //not feederStack-ing or autoStack-ing = normal operation
            toteAvailable = false;
            wentToBottom = false;
            calcNormal();
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
    private void calcNormal() {
        if (operator.getCoopStackButton()) {
            if (operator.getLevel1Button()) {
                elevatorPosition = Constants.StepElevatorLevel1.getDouble();
                newPosition = true;
                coopStack = true;
            } else if (operator.getLevel2Button()) {
                elevatorPosition = Constants.StepElevatorLevel2.getDouble();
                newPosition = true;
                coopStack = true;
            } else if (operator.getLevel3Button()) {
                elevatorPosition = Constants.StepElevatorLevel3.getDouble();
                newPosition = true;
                coopStack = true;
            } else if (operator.getLevel4Button()) {
                elevatorPosition = Constants.StepElevatorLevel4.getDouble();
                newPosition = true;
                coopStack = true;
            } else {
                newPosition = false;
            }
        } else {
            if (operator.getLevel1Button()) {
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                newPosition = true;
                coopStack = false;
            } else if (operator.getLevel2Button()) {
                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                newPosition = true;
                coopStack = false;
            } else if (operator.getLevel3Button()) {
                elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
                newPosition = true;
                coopStack = false;
            } else if (operator.getLevel4Button()) {
                elevatorPosition = Constants.FloorElevatorLevel4.getDouble();
                newPosition = true;
                coopStack = false;
            } else if (operator.getLevel5Button()) {
                elevatorPosition = Constants.FloorElevatorLevel5.getDouble();
                newPosition = true;
                coopStack = false;
            } else if (operator.getLevel6Button()) {
                elevatorPosition = Constants.FloorElevatorLevel6.getDouble();
                newPosition = true;
                coopStack = false;
            } else {
                newPosition = false;
            }
        }

        armOpen = false;
        
        if (operator.getScoreButton()) {
            if (tiltToggle.get()) {
                punchOut = true;
            } else if (coopStack) {
                if (elevatorPosition == Constants.StepElevatorLevel1.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel1.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel2.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel2.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel3.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel3.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel4.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel4.getDouble();
                }
                armOpen = true;
            } else {
                elevatorPosition = Constants.PlaceLevel.getDouble();
                armOpen = true;
            }
        }
    }

    private void calcOverride() {
        if (operator.getElevatorUpButton()) {
            overrideElevatorMotorSpeed = 0.4;
        } else if (operator.getElevatorDownButton()) {
            overrideElevatorMotorSpeed = -0.4;
        }

        if (tiltToggle.get()) {
            punchOut = operator.getScoreButton();
        } else {
            armOpen = operator.getScoreButton();
        }
    }

    //Arms
    private void calcArms() {
        tiltToggle.calc(operator.getTiltButton());
        tiltUp = tiltToggle.get();
        punchOut = operator.getScoreButton();
    }

    private void calcArmsOverride() {
        tiltToggle.calc(operator.getTiltButton());
        tiltUp = tiltToggle.get();
        punchOut = operator.getScoreButton();
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
