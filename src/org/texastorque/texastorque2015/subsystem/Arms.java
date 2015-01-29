package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arms extends Subsystem {

    private boolean armsOpen;
    private boolean punchOut;
    private boolean tiltUp;

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
    public void run() {
        armsOpen = input.isArmOpen();
        punchOut = input.isPunchOut();
        tiltUp = input.isTiltUp();
    }
}
