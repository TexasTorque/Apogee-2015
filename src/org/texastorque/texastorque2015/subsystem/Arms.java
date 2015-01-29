package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Ports;

public class Arms extends Subsystem {

    private Solenoid openSolenoid;
    private Solenoid punchSolenoid;
    private DoubleSolenoid tiltSolenoid;

    public Arms() {
        openSolenoid = new Solenoid(Ports.OPEN_SOLENOID_PORT);
        punchSolenoid = new Solenoid(Ports.PUNCH_SOLENOID);
        tiltSolenoid = new DoubleSolenoid(Ports.TILT_SOLENOID_FORWARD_PORT, Ports.TILT_SOLENOID_BACKWARD_PORT);
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("OpenSolenoid", openSolenoid.get());
        SmartDashboard.putBoolean("PunchSolenoid", punchSolenoid.get());
        switch (tiltSolenoid.get().value) {
            case Value.kForward_val:
                SmartDashboard.putString("TiltSolenoid", "Forward");
                break;
            case Value.kOff_val:
                SmartDashboard.putString("TiltSolenoid", "Off");
                break;
            case Value.kReverse_val:
                SmartDashboard.putString("TiltSolenoid", "Reverse");
                break;
            default:
                SmartDashboard.putString("TiltSolenoid", "Invalid");
        }
    }

    @Override
    public void run() {
        openSolenoid.set(input.isArmOpen());
        punchSolenoid.set(input.isPunchOn());
        switch (input.getTiltState()) {
            case Value.kForward_val:
                tiltSolenoid.set(Value.kForward);
                break;
            case Value.kOff_val:
                tiltSolenoid.set(Value.kOff);
                break;
            case Value.kReverse_val:
                tiltSolenoid.set(Value.kReverse);
                break;
            default:
                System.out.println("Invalid arm state.");
        }
    }
}
