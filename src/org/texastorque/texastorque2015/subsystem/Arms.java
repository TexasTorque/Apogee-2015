package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arms extends Subsystem {

    //Solenoid states
    private boolean armsOpen;
    private boolean punchOut;
    private boolean tiltUp;

    @Override
    public void init() {
    }

    @Override
    public void run() {
        if (feedback.isElevatorHere(input.getElevatorPosition())) {
            armsOpen = input.isArmOpen();
            punchOut = input.isPunchOut();
        } else {
            armsOpen = false;
            punchOut = false;
        }

        tiltUp = input.isTiltUp();

        if (outputEnabled) {
            output.setArmsOpen(armsOpen);
            output.setPunchOut(punchOut);
            output.setTiltUp(tiltUp);
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
    }

    @Override
    public String getLogNames() {
        return "ArmsOpen, PunchOut, TiltUp, ";
    }

    @Override
    public String getLogValues() {
        return armsOpen + ", " + punchOut + ", " + tiltUp + ", ";
    }

}
