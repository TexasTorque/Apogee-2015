package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arms extends Subsystem {

    //Solenoid states
    private boolean punchOut;
    private boolean tiltUp;
    private boolean canHolderUp;


    @Override
    public void init() {
    }

    @Override
    public void run() {
        punchOut = input.isPunchOut();
        canHolderUp = input.isCanHolderUp();
        tiltUp = input.isTiltUp();

        if (outputEnabled) {
            output.setPunchOut(punchOut);
            output.setTiltUp(tiltUp);
            output.setCanHolderUp(canHolderUp);
        }
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("PunchOut", punchOut);
        SmartDashboard.putBoolean("TiltUp", tiltUp);
        SmartDashboard.putBoolean("CanHolderUp", canHolderUp);
    }

    @Override
    public String getLogNames() {
        return "PunchOut,TiltUp,CanHolderUp";
    }

    @Override
    public String getLogValues() {
        return punchOut + "," + tiltUp + "," + canHolderUp + ",";
    }

}

