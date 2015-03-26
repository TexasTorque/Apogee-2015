package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    private double leftIntakeSpeed;
    private double rightIntakeSpeed;
    private boolean intakesIn;

    public Intake() {
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        leftIntakeSpeed = input.getLeftIntakeSpeed();
        rightIntakeSpeed = input.getRightIntakeSpeed();
        intakesIn = input.getIntakeIn();

        if (outputEnabled) {
            output.setIntakeMotorSpeed(leftIntakeSpeed, rightIntakeSpeed);
            output.setIntakeGrasp(intakesIn);
        } else {
            output.setIntakeMotorSpeed(0.0, 0.0);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("IntakeSpeed", leftIntakeSpeed);
        SmartDashboard.putBoolean("IntakesIn", intakesIn);
    }

    @Override
    public String getLogNames() {
        return "IntakeSpeed,IntakesIn,";
    }

    @Override
    public String getLogValues() {
        return leftIntakeSpeed + "," + intakesIn + ",";
    }

}
