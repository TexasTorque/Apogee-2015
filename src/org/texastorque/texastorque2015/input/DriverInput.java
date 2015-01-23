package org.texastorque.texastorque2015.input;

import org.texastorque.torquelib.util.GenericController;

public class DriverInput extends Input {
    
    GenericController driver;
    GenericController operator;
    
    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.1);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.1);
    }

    @Override
    public void run() {
        leftSpeed = driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = driver.getLeftYAxis() - driver.getRightXAxis();
        frontStrafeSpeed = driver.getLeftXAxis() + driver.getRightXAxis();
        rearStrafeSpeed = driver.getLeftXAxis() - driver.getRightXAxis();
    }

}
