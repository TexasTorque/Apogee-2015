package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoPicker {

    public final static byte DEFAULT_AUTO = 0;
    public final static byte DRIVE_AUTO = 1;
    public final static byte CAN_AUTO = 4;
    public final static byte ONE_TOTE_AUTO = 88;
    public final static byte THREE_TOTE_AUTO = 99;

    public static void init() {
        SmartDashboard.putNumber("AutoMode", DEFAULT_AUTO);
    }

    public static AutoMode getAutonomous() {
        byte mode = (byte) SmartDashboard.getNumber("AutoMode", 0);

        if (mode == DEFAULT_AUTO) {
            return new DoNothingAuto();
        } else if (mode == DRIVE_AUTO) {
            return new DriveAuto();
        } else if (mode == CAN_AUTO) {
            return new TwoCanGrabber();
        } else if (mode == ONE_TOTE_AUTO) {
            return new OneToteAuto();
        } else if (mode == THREE_TOTE_AUTO) {
            return new ThreeToteAuto();
        }
        return new DoNothingAuto();
    }
}
