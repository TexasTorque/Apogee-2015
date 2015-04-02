package org.texastorque.texastorque2015.input;

import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueFilter;

public class DriverInput extends Input {

    private GenericController driver;
    private GenericController operator;

    private TorqueFilter driveAccelFilter;
    private TorqueFilter turnAccelFilter;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.2);

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
            stingersSpeed = -0.5;
        } else {
            stingersSpeed = 0.0;
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

    }

    private void calcOverride() {
        overrideElevatorMotorSpeed = -1 * operator.getLeftYAxis();
    }

    private void calcIntake() {
        intakeIn = operator.getRightBumper();

        leftIntakeSpeed = operator.getRightYAxis() - operator.getRightXAxis();
        rightIntakeSpeed = operator.getRightYAxis() + operator.getRightXAxis();
    }
}
