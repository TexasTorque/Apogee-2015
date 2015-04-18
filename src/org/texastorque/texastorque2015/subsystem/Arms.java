package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arms extends Subsystem {

    //Solenoid states
    private boolean canHolderUp;

    @Override
    public void init() {
    }

    @Override
    public void run() {
        canHolderUp = input.isCanHolderUp();

        if (outputEnabled) {
            output.setCanHolderUp(canHolderUp);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("CanHolderUp", canHolderUp);
    }

    @Override
    public String getLogNames() {
        return "CanHolderUp";
    }

    @Override
    public String getLogValues() {
        return canHolderUp + ",";
    }

}

