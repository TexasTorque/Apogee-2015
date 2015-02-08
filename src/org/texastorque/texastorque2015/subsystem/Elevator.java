package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;

public class Elevator extends Subsystem {

    private TorqueTMP tmp;
    private TorquePV pv;

    private double setPointElevation;
    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double currentPosition;
    private double currentVelocity;

    private double ffPosition;

    private int numTotes;

    //up = positive, down = negative
    private double motorSpeed;

    public Elevator() {
        tmp = new TorqueTMP(0.0, 0.0);
        pv = new TorquePV();
        numTotes = 0;
    }

    @Override
    public void enable() {
    }

    @Override
    public void run() {
        currentPosition = feedback.getElevatorHeight();
        currentVelocity = feedback.getElevatorVelocity();

        if (!input.isElevatorOverride()) {
            if (input.getElevatorPosition() != setPointElevation) {
                setPointElevation = input.getElevatorPosition();

                tmp.generateTrapezoid(setPointElevation, currentPosition, currentVelocity);
            }

            motorSpeed = pv.calculate(tmp, currentPosition, currentVelocity) + ffPosition;
        } else {
            motorSpeed = input.getOverrideElevatorMotorSpeed();
        }

        if (!input.isElevatorFFpOff()) {
            motorSpeed += ffPosition;
        }

        if (outputEnabled) {
            output.setElevatorMotorSpeeds(motorSpeed);
        }
    }

    @Override
    public void loadParams() {
        tmp = new TorqueTMP(Constants.ElevatorMaxV.getDouble(), Constants.ElevatorMaxA.getDouble());
        double p = 0.0, v = 0.0, ffV = 0.0, ffA = 0.0;
        if (numTotes == 1) {
            p = Constants.ElevatorP1Tote.getDouble();
            v = Constants.ElevatorV1Tote.getDouble();
            ffV = Constants.ElevatorffV1Tote.getDouble();
            ffA = Constants.ElevatorffA1Tote.getDouble();

            ffPosition = Constants.ElevatorffP1Tote.getDouble();
        } else if (numTotes == 2) {
            p = Constants.ElevatorP2Tote.getDouble();
            v = Constants.ElevatorV2Tote.getDouble();
            ffV = Constants.ElevatorffV2Tote.getDouble();
            ffA = Constants.ElevatorffA2Tote.getDouble();

            ffPosition = Constants.ElevatorffP2Tote.getDouble();
        } else if (numTotes == 3) {
            p = Constants.ElevatorP3Tote.getDouble();
            v = Constants.ElevatorV3Tote.getDouble();
            ffV = Constants.ElevatorffV3Tote.getDouble();
            ffA = Constants.ElevatorffA3Tote.getDouble();

            ffPosition = Constants.ElevatorffP3Tote.getDouble();
        } else if (numTotes == 4) {
            p = Constants.ElevatorP4Tote.getDouble();
            v = Constants.ElevatorV4Tote.getDouble();
            ffV = Constants.ElevatorffV4Tote.getDouble();
            ffA = Constants.ElevatorffA4Tote.getDouble();

            ffPosition = Constants.ElevatorffP4Tote.getDouble();
        } else if (numTotes == 5) {
            p = Constants.ElevatorP5Tote.getDouble();
            v = Constants.ElevatorV5Tote.getDouble();
            ffV = Constants.ElevatorffV5Tote.getDouble();
            ffA = Constants.ElevatorffA5Tote.getDouble();

            ffPosition = Constants.ElevatorffP5Tote.getDouble();
        } else if (numTotes == 6) {
            p = Constants.ElevatorP6Tote.getDouble();
            v = Constants.ElevatorV6Tote.getDouble();
            ffV = Constants.ElevatorffV6Tote.getDouble();
            ffA = Constants.ElevatorffA6Tote.getDouble();

            ffPosition = Constants.ElevatorffP6Tote.getDouble();
        }
        pv.setGains(p, v, ffV, ffA);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("ElevatorSetPointElevation", setPointElevation);
        SmartDashboard.putNumber("ElevatorCurrentPosition", currentPosition);
        SmartDashboard.putNumber("ElevatorCurrentVelocity", currentVelocity);
        SmartDashboard.putNumber("ElevatorTargetPosition", targetPosition);
        SmartDashboard.putNumber("ElevatorTargetVelocity", targetVelocity);
        SmartDashboard.putNumber("ElevatorTargetAcceleration", targetAcceleration);
        SmartDashboard.putNumber("NumTotes", numTotes);
    }

    @Override
    public String getLogNames() {
        return "SetPointElevation, E_CurrentPosition, E_CurrentVelocity, E_TargetPosition, E_TargetVelocity, ";
    }

    @Override
    public String getLogValues() {
        return setPointElevation + ", " + currentPosition + ", " + currentVelocity + ", " + targetPosition + ", " + targetVelocity + ", ";
    }
}
