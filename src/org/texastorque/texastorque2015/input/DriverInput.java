package org.texastorque.texastorque2015.input;

import org.texastorque.torquelib.util.GenericController;

public class DriverInput extends Input {

    GenericController driver;
    GenericController operator;

    public DriverInput() {
        driver = new GenericController(0, GenericController.TYPE_XBOX, 0.2);
        operator = new GenericController(1, GenericController.TYPE_XBOX, 0.1);
    }

    @Override
    public void run() {
        /**
         * Left stick controls translation, right stick controls rotation. Both
         * the forward and strafe wheels are utilized for rotation.
         */
        leftSpeed = -1 * driver.getLeftYAxis() + driver.getRightXAxis();
        rightSpeed = -1 * driver.getLeftYAxis() - driver.getRightXAxis();
        frontStrafeSpeed = -1 * driver.getLeftXAxis() - driver.getRightXAxis();
        rearStrafeSpeed = -1 * driver.getLeftXAxis() + driver.getRightXAxis();

        if (operator.getLeftCenterButton()) {
            elevatorOverride = true;
        } else if (operator.getRightCenterButton()) {
            elevatorOverride = false;
        }

        if (elevatorOverride) {
            calcElevatorOverride();
        } else {
            calcElevator();
        }
    }

    private void calcElevator() {
    }

    private void calcElevatorOverride() {
        overrideElevatorMotorSpeed = operator.getLeftYAxis() * -1;
    }
}
