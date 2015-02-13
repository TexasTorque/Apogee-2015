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

    private boolean isDone() {
        return Math.abs(currentPosition - feedback.getElevatorHeight()) < 0.25 && Math.abs(feedback.getElevatorVelocity()) < 0.25;
    }

    @Override
    public void run() {
        currentPosition = feedback.getElevatorHeight();
        currentVelocity = feedback.getElevatorVelocity();

        feedback.setElevatorDone(isDone());

        if (!input.isElevatorOverride()) {
            if (input.getElevatorPosition() != setPointElevation) {
                setPointElevation = input.getElevatorPosition();

                tmp.generateTrapezoid(setPointElevation, currentPosition, currentVelocity);
            }
            tmp.calculateNextSituation();

            targetPosition = tmp.getCurrentPosition();
            targetVelocity = tmp.getCurrentVelocity();
            targetAcceleration = tmp.getCurrentAcceleration();

            motorSpeed = pv.calculate(tmp, currentPosition, currentVelocity) + ffPosition;
        } else {
            motorSpeed = input.getOverrideElevatorMotorSpeed();
        }

        if (!input.isElevatorFFpOff()) {
            motorSpeed += ffPosition;
        }

        if (feedback.isElevatorAtTop()) {
            motorSpeed = Math.min(motorSpeed, 0.0);
        } else if (feedback.isElevatorAtBottom()) {
            motorSpeed = Math.max(motorSpeed, 0.0);
        }

        if (outputEnabled) {
            output.setElevatorMotorSpeeds(motorSpeed);
        }
    }

    @Override
    public void loadParams() {
        double p = 0.0, v = 0.0, ffV = 0.0, ffA = 0.0;
        if (numTotes == 0) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV0Tote.getDouble(), Constants.ElevatorMaxA0Tote.getDouble());
            p = Constants.ElevatorP0Tote.getDouble();
            v = Constants.ElevatorV0Tote.getDouble();
            ffV = Constants.ElevatorffV0Tote.getDouble();
            ffA = Constants.ElevatorffA0Tote.getDouble();

            ffPosition = Constants.ElevatorffP0Tote.getDouble();
        } else if (numTotes == 1) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV1Tote.getDouble(), Constants.ElevatorMaxA1Tote.getDouble());
            p = Constants.ElevatorP1Tote.getDouble();
            v = Constants.ElevatorV1Tote.getDouble();
            ffV = Constants.ElevatorffV1Tote.getDouble();
            ffA = Constants.ElevatorffA1Tote.getDouble();

            ffPosition = Constants.ElevatorffP1Tote.getDouble();
        } else if (numTotes == 2) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV2Tote.getDouble(), Constants.ElevatorMaxA2Tote.getDouble());
            p = Constants.ElevatorP2Tote.getDouble();
            v = Constants.ElevatorV2Tote.getDouble();
            ffV = Constants.ElevatorffV2Tote.getDouble();
            ffA = Constants.ElevatorffA2Tote.getDouble();

            ffPosition = Constants.ElevatorffP2Tote.getDouble();
        } else if (numTotes == 3) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV3Tote.getDouble(), Constants.ElevatorMaxA3Tote.getDouble());
            p = Constants.ElevatorP3Tote.getDouble();
            v = Constants.ElevatorV3Tote.getDouble();
            ffV = Constants.ElevatorffV3Tote.getDouble();
            ffA = Constants.ElevatorffA3Tote.getDouble();

            ffPosition = Constants.ElevatorffP3Tote.getDouble();
        } else if (numTotes == 4) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV4Tote.getDouble(), Constants.ElevatorMaxA4Tote.getDouble());
            p = Constants.ElevatorP4Tote.getDouble();
            v = Constants.ElevatorV4Tote.getDouble();
            ffV = Constants.ElevatorffV4Tote.getDouble();
            ffA = Constants.ElevatorffA4Tote.getDouble();

            ffPosition = Constants.ElevatorffP4Tote.getDouble();
        } else if (numTotes == 5) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV5Tote.getDouble(), Constants.ElevatorMaxA5Tote.getDouble());
            p = Constants.ElevatorP5Tote.getDouble();
            v = Constants.ElevatorV5Tote.getDouble();
            ffV = Constants.ElevatorffV5Tote.getDouble();
            ffA = Constants.ElevatorffA5Tote.getDouble();

            ffPosition = Constants.ElevatorffP5Tote.getDouble();
        } else if (numTotes == 6) {
            tmp = new TorqueTMP(Constants.ElevatorMaxV6Tote.getDouble(), Constants.ElevatorMaxA6Tote.getDouble());
            p = Constants.ElevatorP6Tote.getDouble();
            v = Constants.ElevatorV6Tote.getDouble();
            ffV = Constants.ElevatorffV6Tote.getDouble();
            ffA = Constants.ElevatorffA6Tote.getDouble();

            ffPosition = Constants.ElevatorffP6Tote.getDouble();
        } else if (numTotes == -1) {
            tmp = new TorqueTMP(Constants.ElevatorMaxARecyclingCan.getDouble(), Constants.ElevatorMaxARecyclingCan.getDouble());
            p = Constants.ElevatorPRecyclingCan.getDouble();
            v = Constants.ElevatorVRecyclingCan.getDouble();
            ffV = Constants.ElevatorffVRecyclingCan.getDouble();
            ffA = Constants.ElevatorffARecyclingCan.getDouble();

            ffPosition = Constants.ElevatorffPRecyclingCan.getDouble();
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
        SmartDashboard.putBoolean("ElevatorDone", isDone());
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
