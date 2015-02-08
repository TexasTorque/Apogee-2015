package org.texastorque.texastorque2015.input;

import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class DriverInput extends Input {

    GenericController driver;
    OperatorConsole operator;
    
    TorqueToggle tiltToggle;
    TorqueToggle armOpenToggle;

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

        if (elevatorOverride) {
            calcElevatorOverride();
        } else {
            calcElevator();
        }
        
        //Intake
        if (operator.getIntakeButton()) {
            intakeSpeed = 1.0;
            intakesIn = true;
        } else if (operator.getOuttakeButton()) {
            intakeSpeed = -1.0;
            intakesIn = true;
        } else {
            intakeSpeed = 0.0;
            intakesIn = false;
        }
        
        //Arms
        calcArms();
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
        armOpen = armOpenToggle.get();
        
        punchOut = operator.getPunchButton();
    }
}
