package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoPicker {

    public final static byte DEFAULT_AUTO = 0;
    public final static byte DRIVE_AUTO = 1;
    public final static byte TURN_AUTO = 2;

    public static void init() {
        SmartDashboard.putNumber("AutoMode", DEFAULT_AUTO);
    }

    public static AutoMode getAutonomous() {
        byte mode = (byte) SmartDashboard.getNumber("AutoMode", DEFAULT_AUTO);

        if (mode == DEFAULT_AUTO) {
            return new DoNothingAuto();
        } else if (mode == DRIVE_AUTO) {
            return new DriveAuto();
        } else if (mode == TURN_AUTO) {
            return new TurnAuto();
        }

        return new DoNothingAuto();
    }
}
