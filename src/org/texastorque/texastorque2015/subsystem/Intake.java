package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;

public class Intake extends Subsystem {

    private double intakeSpeed;
    private boolean intakesIn;

    private byte state;
    public static final byte OFF = 0;
    public static final byte INTAKE = 1;
    public static final byte OUTTAKE = 2;
    public static final byte SLUICE_GATHER = 3;
    public static final byte OPEN_ROLL_IN = 4;

    private double toteSlideTime;
    private boolean toteInSluice;

    public Intake() {
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        state = input.getIntakeState();
        
        toteInSluice = feedback.isToteInSluice();

        switch (state) {
            case INTAKE:
                intakeSpeed = 1.0;
                intakesIn = true;
                break;
            case OUTTAKE:
                intakeSpeed = -0.75;
                intakesIn = true;
                break;
            case SLUICE_GATHER:
                toteSlideTime = feedback.getToteSlideTime();
                if (Timer.getFPGATimestamp() - toteSlideTime < Constants.ToteSluiceWaitTime.getDouble()) {
                    intakeSpeed = -0.2;
                    intakesIn = true;
                } else if (Timer.getFPGATimestamp() - toteSlideTime < Constants.ToteSluiceWaitTime.getDouble() + Constants.TotePullBAckTime.getDouble()) {
                    intakeSpeed = 1.0;
                    intakesIn = true;
                } else {
                    intakeSpeed = 0.0;
                    intakesIn = false;
                }
                break;
            case OPEN_ROLL_IN:
                intakesIn = false;
                intakeSpeed = 1.0;
            case OFF:
                intakeSpeed = 0.0;
                intakesIn = false;
            default:
                intakeSpeed = 0.0;
                intakesIn = false;
        }

        if (outputEnabled) {
            output.setIntakeMotorSpeed(intakeSpeed);
            output.setIntakeGrasp(intakesIn);
        } else {
            output.setIntakeMotorSpeed(0.0);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("IntakeSpeed", intakeSpeed);
        SmartDashboard.putBoolean("IntakesIn", intakesIn);
        SmartDashboard.putNumber("IntakeState", state);
        SmartDashboard.putBoolean("ToteInSluice", toteInSluice);
        SmartDashboard.putNumber("ToteSlideTime", toteSlideTime);
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
