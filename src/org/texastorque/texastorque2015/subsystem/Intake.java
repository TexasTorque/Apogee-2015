package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends Subsystem {

    private double intakeSpeed;
    private boolean intakesIn;

    public Intake() {
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        intakeSpeed = input.getIntakeSpeed();
        intakesIn = input.areIntakesIn();
        
        if (outputEnabled) {
            output.setIntakeMotorSpeed(intakeSpeed);
            output.setIntakeGrasp(intakesIn);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("IntakeSpeed", intakeSpeed);
        SmartDashboard.putBoolean("IntakesIn", intakesIn);
    }

    @Override
    public String getLogNames() {
        return "IntakeSpeed, IntakesIn, ";
    }

    @Override
    public String getLogValues() {
        return intakeSpeed + ", " + intakesIn + ", ";
    }

}
