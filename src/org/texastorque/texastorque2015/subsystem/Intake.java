package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    private double intakeSpeed;

    public Intake() {
    }

    @Override
    public void enable() {
    }

    @Override
    public void run() {
        intakeSpeed = input.getIntakeSpeed();

        if (outputEnabled) {
            output.setIntakeMotorSpeed(intakeSpeed);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("IntakeSpeed", intakeSpeed);
    }

    @Override
    public String getLogNames() {
        return "IntakeSpeed, ";
    }

    @Override
    public String getLogValues() {
        return intakeSpeed + ", ";
    }

}
