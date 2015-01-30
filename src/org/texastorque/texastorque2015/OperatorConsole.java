package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorConsole {

    private Joystick joystick = new Joystick(-1);

    public boolean getTiltButton() {
        return joystick.getRawButton(-1);
    }

    public boolean getPunchButton() {
        return joystick.getRawButton(-1);
    }

    public boolean getArmOpenButton() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel1Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel2Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel3Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel4Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel5Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getLevel6Button() {
        return joystick.getRawButton(-1);
    }

    public boolean getElevatorOverrideSwitch() {
        return joystick.getRawButton(-1);
    }

    public boolean getElevatorFFOspSwitch() {
        return joystick.getRawButton(-1);
    }

    public boolean getElevatorUpButton() {
        return joystick.getRawButton(-1);
    }

    public boolean getElevatorDownButton() {
        return joystick.getRawButton(-1);
    }

}
