package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private double toteInTime;
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
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("tiltToggle", tiltToggle.get());
        SmartDashboard.putBoolean("wentToBottom", wentToBottom);
        SmartDashboard.putNumber("toteInTime", toteInTime);
        SmartDashboard.putBoolean("toteAvailable", toteAvailable);
        SmartDashboard.putNumber("autoStackHeight", autoStackHeight);
        SmartDashboard.putBoolean("stepStack", stepStack);
        SmartDashboard.putBoolean("elevationInputThisCycle", elevationInputThisCycle);
    }

    @Override
    public boolean newPosition() {
        return newPosition;
    }

    @Override
    public void run() {
        //numTotes override
        calcNumTotesOverride();

        elevationInputThisCycle = false;

        //Drivebase
        calcDrivebase();

        //Elevator
        if (operator.getElevatorDownButton() || operator.getElevatorUpButton()) {
            elevatorFFpOff = override = true;
        }

        feederStack = operator.getFeederStackButton();
        if (!feederStack) {
            autoStack = operator.getAutoStackButton();
        }

        if (override) {
            calcOverride();
            calcArms();
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
                elevationInputThisCycle = true;

                numTotes++;
                wentToBottom = true;
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
                elevationInputThisCycle = true;
            }
        } else if (feederStack) {
            //setup feederStack cycle
            elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
            elevationInputThisCycle = true;

            armOpen = false;
            punchOut = false;
            tiltUp = false;

            if (feedback.isToteInSluice() && !toteAvailable) {
                //get the time for the start of the feederstack cycle
                toteInTime = Timer.getFPGATimestamp();
                toteAvailable = true;
            } else if (toteAvailable && feedback.isElevatorHere(elevatorPosition)) {
                double toteSlideTime = feedback.getToteSlideTime();
                intakeState = Intake.SLUICE_GATHER;
                if (Timer.getFPGATimestamp() - toteSlideTime < Constants.ToteSluiceWaitTime.getDouble() + Constants.TotePullBAckTime.getDouble()) {
                    autoStack = true;
                    intakeState = Intake.OFF;
                }
            }
        } else {
            toteAvailable = false;
            wentToBottom = false;
            if (!operator.getNumTotesButton() && !operator.getNumTotesResetButton()) {
                calcNormal();
            }
            calcArms();
            calcIntake();
        }

        newPosition = elevationInputThisCycle;
    }

    private void calcNumTotesOverride() {
        if (operator.getNumTotesButton()) {
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
        if (operator.getNumTotesResetButton()) {
            numTotes = 0;
        }
    }

    //Drivebase
    private void calcDrivebase() {
        leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis();
    }

    //Elevator
    private void calcNormal() {
        if (operator.getCoopStackButton()) {
            stepStack = true;

            if (operator.getLevel1Button()) {
                elevatorPosition = Constants.StepElevatorLevel1.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel2Button()) {
                elevatorPosition = Constants.StepElevatorLevel2.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel3Button()) {
                elevatorPosition = Constants.StepElevatorLevel3.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel4Button()) {
                elevatorPosition = Constants.StepElevatorLevel4.getDouble();
                elevationInputThisCycle = true;
            }
        } else {
            stepStack = false;

            if (operator.getLevel1Button()) {
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel2Button()) {
                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel3Button()) {
                elevatorPosition = Constants.FloorElevatorLevel3.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel4Button()) {
                elevatorPosition = Constants.FloorElevatorLevel4.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel5Button()) {
                elevatorPosition = Constants.FloorElevatorLevel5.getDouble();
                elevationInputThisCycle = true;
            } else if (operator.getLevel6Button()) {
                elevatorPosition = Constants.FloorElevatorLevel6.getDouble();
                elevationInputThisCycle = true;
            }
        }

        if (operator.getScoreButton()) {
            numTotes = 0;
            if (tiltToggle.get()) {
                punchOut = true;
                armOpen = false;
            } else if (stepStack) {
                if (elevatorPosition == Constants.StepElevatorLevel1.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel1.getDouble();
                    elevationInputThisCycle = true;
                } else if (elevatorPosition == Constants.StepElevatorLevel2.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel2.getDouble();
                    elevationInputThisCycle = true;
                } else if (elevatorPosition == Constants.StepElevatorLevel3.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel3.getDouble();
                    elevationInputThisCycle = true;
                } else if (elevatorPosition == Constants.StepElevatorLevel4.getDouble()) {
                    elevatorPosition = Constants.StepPlaceLevel4.getDouble();
                    elevationInputThisCycle = true;
                }
                armOpen = true;
                punchOut = false;
            } else {
                elevatorPosition = Constants.PlaceLevel.getDouble();
                elevationInputThisCycle = true;

                armOpen = true;
                punchOut = false;
            }
        } else {
            armOpen = false;
            punchOut = false;
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
            punchOut = operator.getScoreButton();
            armToggle.set(false);
            armOpen = false;
        } else {
            armToggle.calc(operator.getScoreButton());
            armOpen = armToggle.get();
            punchOut = false;
        }
    }

    //Arms
    private void calcArms() {
        tiltToggle.calc(operator.getTiltButton());
        tiltUp = tiltToggle.get();
    }

    //Intake
    private void calcIntake() {
        if (operator.getIntakeButton()) {
            intakeState = Intake.INTAKE;
        } else {
            intakeState = Intake.OFF;
        }
    }
}
