package org.texastorque.texastorque2015.input;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Wraps a Joystick for the custom control panel we're using for this robot.
 *
 * @author Gijs
 */
public class OperatorConsole {

    private Joystick joystick;

    public OperatorConsole(int port) {
        joystick = new Joystick(port);
    }

    public boolean getTiltButton() {
        return joystick.getRawButton(9);
    }

    public boolean getScoreButton() {
        return joystick.getRawButton(13);
    }

    public boolean getLevel1Button() {
        return joystick.getRawButton(5);
    }

    public boolean getLevel2Button() {
        return joystick.getRawButton(6);
    }

    public boolean getLevel3Button() {
        return joystick.getRawButton(4);
    }

    public boolean getLevel4Button() {
        return joystick.getRawButton(3);
    }

    public boolean getLevel5Button() {
        return joystick.getRawButton(1);
    }

    public boolean getLevel6Button() {
        return joystick.getRawButton(2);
    }

    public boolean getElevatorUpButton() {
        return joystick.getRawButton(12);
    }

    public boolean getElevatorDownButton() {
        return joystick.getRawButton(15);
    }

    public boolean getIntakeButton() {
        return joystick.getRawButton(7);
    }

    public boolean getAutoStackButton() {
        return joystick.getRawButton(16);
    }

    public boolean getFeederStackButton() {
        return joystick.getRawButton(11);
    }

    public boolean getCoopStackButton() {
        return joystick.getRawButton(14);
    }

    public boolean getNumTotesButton() {
        return joystick.getRawButton(8);
    }

    public boolean getNumTotesResetButton() {
        return joystick.getRawButton(10);
    }
}
