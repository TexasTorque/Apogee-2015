package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.subsystem.Intake;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class DriverInput extends Input {

    GenericController driver;
    OperatorConsole operator;

    TorqueToggle tiltToggle;
    TorqueToggle armToggle;

    private boolean wentToBottom;
    private boolean toteAvailable;
    private double autoStackHeight;
    private boolean stepStack;

    private boolean elevationInputThisCycle;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new OperatorConsole(1);

        tiltToggle = new TorqueToggle();
        armToggle = new TorqueToggle();

        wentToBottom = false;
        toteAvailable = false;
        autoStackHeight = 0.0;

        newPosition = false;
        numTotes = 0;

        elevatorFFpOff = false;
    }

    @Override
    public boolean newPosition() {
        return newPosition;
    }

    @Override
    public void run() {
        elevationInputThisCycle = false;

        calcDrivebase();

        if (operator.getElevatorDownButton() || operator.getElevatorUpButton()) {
            override = true;
        }

        feederStack = operator.getFeederStackButton();
        if (!feederStack) {
            autoStack = operator.getAutoStackButton();
        }

        if (override) {
            calcOverride();
            calcIntake();
            calcTilt();
        } else if (autoStack) {

            intakeState = Intake.OFF;

            //autoStack = bring elevator down and back up to stack tote
            tiltUp = false;
            punchOut = false;

            if (feedback.isElevatorHere(Constants.autoStackLevel.getDouble()) && !wentToBottom) {
                armOpen = false;

                if (feederStack) {
                    autoStackHeight = Constants.FloorElevatorLevel3.getDouble();
                } else {
                    autoStackHeight = Constants.FloorElevatorLevel2.getDouble();
                }
                elevatorPosition = autoStackHeight;
                elevationInputThisCycle = true;

                numTotes++;
                wentToBottom = true;
            } else if (feedback.isElevatorHere(autoStackHeight) && wentToBottom) {
                autoStack = false;
                toteAvailable = false;
                wentToBottom = false;
            } else if (elevatorPosition == autoStackHeight && wentToBottom) {
                //catch else to make sure that nothing happens if elevator is not done
            } else {
                elevatorPosition = Constants.autoStackLevel.getDouble();
                elevationInputThisCycle = true;

                armOpen = feedback.getElevatorHeight() < Constants.autoStackArmOpenLevel.getDouble();
            }
        } else if (feederStack) {
            elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
            elevationInputThisCycle = true;

            armOpen = false;
            punchOut = false;
            tiltUp = false;

            toteAvailable = feedback.isToteInSluice();

            if (toteAvailable && feedback.isElevatorHere(elevatorPosition)) {
                double toteSlideTime = feedback.getToteSlideTime();
                intakeState = Intake.SLUICE_GATHER;
                if (Timer.getFPGATimestamp() - toteSlideTime > Constants.ToteSluiceWaitTime.getDouble() + Constants.TotePullBAckTime.getDouble()) {
                    autoStack = true;
                    intakeState = Intake.OFF;
                }
            }
        } else {
            toteAvailable = false;
            wentToBottom = false;
            if (driver.getYButton() || driver.getAButton()) {
                calcNumTotesOverride();
            } else {
                calcNormal();
            }

            calcTilt();
            calcIntake();
        }

        armOpen = armOpen || operator.getScoreButton();
        newPosition = elevationInputThisCycle;
    }

    private void calcNumTotesOverride() {
        if (driver.getAButton()) {
            if (operator.getLevel1Button()) {
                numTotes = 1;
            } else if (operator.getLevel2Button()) {
                numTotes = 2;
            } else if (operator.getLevel3Button()) {
                numTotes = 3;
            } else if (operator.getLevel4Button()) {
                numTotes = 4;
            } else if (operator.getLevel5Button()) {
                numTotes = 5;
            } else if (operator.getLevel6Button()) {
                numTotes = 6;
            }
        }
        if (driver.getYButton()) {
            numTotes = 0;
        }
    }

    //Drivebase
    private void calcDrivebase() {
        leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis();
        strafeSpeed = driver.getLeftXAxis();

        if (driver.getLeftBumper()) {
            leftSpeed = leftSpeed / 2;
            rightSpeed = rightSpeed / 2;
        }
    }

    //Elevator
    private void calcNormal() {
        if (driver.getRightTrigger()) {
            numTotes = 0;
            if (tiltToggle.get()) {
                punchOut = true;
                armOpen = false;
            } else if (stepStack) {
                elevationInputThisCycle = true;

                if (elevatorPosition == Constants.StepElevatorLevel1.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel1.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel2.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel2.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel3.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel3.getDouble();
                } else if (elevatorPosition == Constants.StepElevatorLevel4.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel4.getDouble();
                }
                if (feedback.isElevatorHere(elevatorPosition)) {
                    armOpen = true;
                }
                punchOut = false;
            } else {
                if (elevatorPosition == Constants.FloorElevatorLevel1.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel1.getDouble();
                } else if (elevatorPosition == Constants.FloorElevatorLevel2.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel2.getDouble();
                } else if (elevatorPosition == Constants.FloorElevatorLevel3.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel3.getDouble();
                } else if (elevatorPosition == Constants.FloorElevatorLevel4.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel4.getDouble();
                } else if (elevatorPosition == Constants.FloorElevatorLevel5.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel5.getDouble();
                } else if (elevatorPosition == Constants.FloorElevatorLevel6.getDouble()) {
                    elevatorPosition = Constants.PlaceLevel6.getDouble();
                }

                elevationInputThisCycle = true;

                if (feedback.isElevatorHere(elevatorPosition)) {
                    armOpen = true;
                }
                punchOut = false;
            }
        } else if (operator.getCoopStackButton()) {
            armOpen = false;
            punchOut = false;

            stepStack = true;
            elevationInputThisCycle = true;

            if (operator.getLevel1Button()) {
                elevatorPosition = Constants.StepElevatorLevel1.getDouble();
            } else if (operator.getLevel2Button()) {
                elevatorPosition = Constants.StepElevatorLevel2.getDouble();
            } else if (operator.getLevel3Button()) {
                elevatorPosition = Constants.StepElevatorLevel3.getDouble();
            } else if (operator.getLevel4Button()) {
                elevatorPosition = Constants.StepElevatorLevel4.getDouble();
            }
        } else {
            armOpen = false;
            punchOut = false;

            stepStack = false;
            elevationInputThisCycle = true;

            if (tiltToggle.get()) {
                if (operator.getLevel1Button()) {
                    elevatorPosition = Constants.RCElevatorLevel1.getDouble();
                } else if (operator.getLevel2Button()) {
                    elevatorPosition = Constants.RCElevatorLevel2.getDouble();
                } else if (operator.getLevel3Button()) {
                    elevatorPosition = Constants.RCElevatorLevel3.getDouble();
                } else if (operator.getLevel4Button()) {
                    elevatorPosition = Constants.RCElevatorLevel4.getDouble();
                } else if (operator.getLevel5Button()) {
                    elevatorPosition = Constants.RCElevatorLevel5.getDouble();
                } else if (operator.getLevel6Button()) {
                    elevatorPosition = Constants.RCElevatorLevel6.getDouble();
                }
            } else {
                if (operator.getLevel1Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                } else if (operator.getLevel2Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                } else if (operator.getLevel3Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
                } else if (operator.getLevel4Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel4.getDouble();
                } else if (operator.getLevel5Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel5.getDouble();
                } else if (operator.getLevel6Button()) {
                    elevatorPosition = Constants.FloorElevatorLevel6.getDouble();
                }
            }
        }
    }

    private void calcOverride() {
        if (operator.getElevatorUpButton()) {
            overrideElevatorMotorSpeed = 1.0;
        } else if (operator.getElevatorDownButton()) {
            overrideElevatorMotorSpeed = -1.0;
        } else {
            overrideElevatorMotorSpeed = 0.0;
        }

        if (tiltToggle.get()) {
            punchOut = driver.getRightTrigger();
            armToggle.set(false);
            numTotes = 0;
            armOpen = false;
        } else {
            armToggle.calc(driver.getRightTrigger());
            armOpen = armToggle.get();
            if (armOpen) {
                numTotes = 0;
            }
            punchOut = false;
        }
    }

    //Arms
    private void calcTilt() {
        tiltToggle.calc(operator.getTiltButton());
        tiltUp = tiltToggle.get();
    }

    //Intake
    private void calcIntake() {
        if (operator.getIntakeButton()) {
            intakeState = Intake.INTAKE;
        } else if (driver.getRightBumper()) {
            intakeState = Intake.OUTTAKE;
        } else {
            intakeState = Intake.OFF;
        }
    }
}
