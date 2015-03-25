package org.texastorque.texastorque2015.input;

import org.texastorque.texastorque2015.subsystem.Intake;
import org.texastorque.torquelib.util.GenericController;

public class DriverInput extends Input {

    GenericController driver;
    GenericController operator;
    
    private boolean isOverride;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.2);

        isOverride = false;
    }

    @Override
    public void run() {
        if (operator.getLeftCenterButton()) {
            isOverride = true;
        } else if (operator.getRightCenterButton()) {
            isOverride = false;
        }
        
        if (isOverride) {
            calcOverride();
        } else {
            calcElevator();
        }
        
        calcIntake();
        calcDrivebase();
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
    
    private void calcElevator() {
        
    }
    
    private void calcOverride() {
        overrideElevatorMotorSpeed = -1 * operator.getLeftYAxis();
    }
    
    private void calcIntake() {
        if (operator.getRightBumper()) {
            leftIntakeSpeed = 1.0 - operator.getRightXAxis() / 2;
            rightIntakeSpeed = 1.0 + operator.getRightXAxis() / 2;
            
            if (operator.getRightYAxis() < 0.75) {
                intakeIn = true;
            } else {
                intakeIn = false;
            }
        }
    }
}
