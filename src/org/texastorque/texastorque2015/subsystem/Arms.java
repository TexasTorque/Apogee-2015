package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePID;

public class Arms extends Subsystem {

    public static double UP = 0.0;
    public static double HORIZONTAL = 0.0;
    public static double DOWN = 0.0;

    //Solenoid states
    private boolean armsOpen;
    private boolean punchOut;

    //pid
    private double setPointAngle;
    private double currentAngle;
    private double tiltMotorSpeed;
    private TorquePID anglePID;

    public Arms() {
        anglePID = new TorquePID();
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        //if (feedback.isElevatorHere(input.getElevatorPosition()) || input.isOverride()) {
        armsOpen = input.isArmOpen();
        punchOut = input.isPunchOut();
        //} else {
        //    armsOpen = false;
        //    punchOut = false;
        //}

        setPointAngle = input.getTiltAngle();
        anglePID.setSetpoint(setPointAngle);
        tiltMotorSpeed = anglePID.calculate(currentAngle);

        if (outputEnabled) {
            output.setArmsOpen(armsOpen);
            output.setPunchOut(punchOut);
            output.setTiltMotorSpeed(tiltMotorSpeed);
        }
    }

    @Override
    public void loadParams() {
        UP = Constants.TiltUpAngle.getDouble();
        HORIZONTAL = Constants.TiltHorizontalAngle.getDouble();
        DOWN = Constants.TiltDownAngle.getDouble();
        anglePID.setPIDGains(Constants.TiltP.getDouble(),
                Constants.TiltI.getDouble(),
                Constants.TiltD.getDouble());
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putBoolean("ArmsOpen", armsOpen);
        SmartDashboard.putBoolean("PunchOut", punchOut);
        SmartDashboard.putNumber("TiltMotorSpeed", tiltMotorSpeed);
    }

    @Override
    public String getLogNames() {
        return "ArmsOpen, PunchOut, TiltMotorSpeed, ";
    }

    @Override
    public String getLogValues() {
        return armsOpen + ", " + punchOut + ", " + tiltMotorSpeed + ", ";
    }

}
