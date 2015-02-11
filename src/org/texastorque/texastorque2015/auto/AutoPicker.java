package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoPicker {

    public final static byte TEST_AUTO = 100;
    public final static byte DEFAULT_AUTO = 0;
    public final static byte DRIVE_AUTO = 1;

    public static void init() {
        SmartDashboard.putNumber("AutoMode", DEFAULT_AUTO);
    }

    public static AutoMode getAutonomous() {
        byte mode = (byte) SmartDashboard.getNumber("AutoMode", DEFAULT_AUTO);

        if (mode == DEFAULT_AUTO) {
            return new DoNothingAuto();
        } else if (mode == DRIVE_AUTO) {
            return new DriveAuto();
        } else if (mode == TEST_AUTO) {
            return new TestAuto();
        }
        return new DoNothingAuto();
    }
}
