package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arms extends Subsystem {

    //Solenoid states
    private boolean armsOpen;
    private boolean punchOut;
    private boolean tiltUp;
    private boolean canHolderUp;


    @Override
    public void init() {
    }

    @Override
    public void run() {
        //if (feedback.isElevatorHere(input.getElevatorPosition()) || input.isOverride()) {
        armsOpen = input.isArmOpen();
        punchOut = input.isPunchOut();
        canHolderUp = input.isCanHolderUp();
        //} else {
        //    armsOpen = false;
        //    punchOut = false;
        //}

        tiltUp = input.isTiltUp();

        if (outputEnabled) {
            output.setArmsOpen(armsOpen);
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
        SmartDashboard.putBoolean("ArmsOpen", armsOpen);
        SmartDashboard.putBoolean("PunchOut", punchOut);
        SmartDashboard.putBoolean("TiltUp", tiltUp);
        SmartDashboard.putBoolean("CanHolderUp", canHolderUp);
    }

    @Override
    public String getLogNames() {
        return "ArmsOpen,PunchOut,TiltUp,CanHolderUp";
    }

    @Override
    public String getLogValues() {
        return armsOpen + "," + punchOut + "," + tiltUp + "," + canHolderUp + ",";
    }

}

