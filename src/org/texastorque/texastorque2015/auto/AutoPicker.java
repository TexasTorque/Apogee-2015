package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoPicker {

    public final static byte TEST_AUTO = 100;
    public final static byte DEFAULT_AUTO = 0;
    public final static byte DRIVE_AUTO = 1;
    public final static byte TAKE_AUTO = 2;

    public static void init() {
        SmartDashboard.putNumber("AutoMode", DEFAULT_AUTO);
    }

    public static AutoMode getAutonomous() {
        byte mode = (byte) SmartDashboard.getNumber("AutoMode", 0);

        if (mode == DEFAULT_AUTO) {
            return new DoNothingAuto();
        } else if (mode == DRIVE_AUTO) {
            return new DriveAuto();
        } else if (mode == TEST_AUTO) {
            return new TestAuto();
        } else if (mode == TAKE_AUTO) {
            return new TakeSomethingAuto();
        } else if (mode == 3) {
            return new YellowToteAuto();
        } else if (mode == 4) {
            return new TwoCanGrabber();
        }
        return new DoNothingAuto();
    }
}
