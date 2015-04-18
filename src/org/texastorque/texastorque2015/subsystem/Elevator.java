package org.texastorque.texastorque2015.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;

public class Elevator extends Subsystem {

    private TorqueTMP tmp;
    private TorquePV pv;

    double prevTime;

    private double setPointElevation;
    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double currentPosition;
    private double currentVelocity;

    private double ffPosition;

    //up = positive, down = negative
    private double motorSpeed;

    public Elevator() {
        tmp = new TorqueTMP(0.0, 0.0);
        pv = new TorquePV();
    }

    @Override
    public void init() {
        //Make the elevator hold its current position when we enable.
        //We do not want it flying off to its previous setpoint for safety reasons.
        setPointElevation = currentPosition = feedback.getElevatorHeight();
        currentVelocity = feedback.getElevatorVelocity();

        tmp.generateTrapezoid(setPointElevation, currentPosition, currentVelocity);

        prevTime = Timer.getFPGATimestamp();
    }

    /**
     * Check if the control loops have finished moving the elevator to its new
     * position.
     *
     * @return Is the elevator done moving.
     */
    private boolean isDone() {
        return Math.abs(setPointElevation - feedback.getElevatorHeight()) < Constants.ElevatorPDoneRange.getDouble()
                && Math.abs(feedback.getElevatorVelocity()) < Constants.ElevatorVDoneRange.getDouble();
    }

    @Override
    public void run() {
        currentPosition = feedback.getElevatorHeight();
        currentVelocity = feedback.getElevatorVelocity();

        //Control loop operation
        if (!input.isOverride()) {
            if (input.getElevatorPosition() != setPointElevation && input.newPosition()) {
                setPointElevation = input.getElevatorPosition();

                tmp.generateTrapezoid(setPointElevation, currentPosition, currentVelocity);
            }
            double dt = Timer.getFPGATimestamp() - prevTime;
            prevTime = Timer.getFPGATimestamp();
            tmp.calculateNextSituation(dt);

            targetPosition = tmp.getCurrentPosition();
            targetVelocity = tmp.getCurrentVelocity();
            targetAcceleration = tmp.getCurrentAcceleration();

            motorSpeed = pv.calculate(tmp, currentPosition, currentVelocity);
        } else { //Raw motor speeds for manual control
            motorSpeed = input.getOverrideElevatorMotorSpeed();
        }

        //Add in constant power to balance gravity.
        if (!input.isElevatorFFpOff()) {
            motorSpeed += ffPosition;
        }

        feedback.setElevatorDone(isDone());

        //Output to the robot if we want to.
        if (outputEnabled) {
            output.setElevatorMotorSpeeds(motorSpeed);
        } else {
            output.setElevatorMotorSpeeds(0.0);
        }
    }

    @Override
    public void loadParams() {
        tmp = new TorqueTMP(Constants.ElevatorMaxV0Tote.getDouble(), Constants.ElevatorMaxA0Tote.getDouble());
        double p = Constants.ElevatorP0Tote.getDouble();
        double v = Constants.ElevatorV0Tote.getDouble();
        double ffV = Constants.ElevatorffV0Tote.getDouble();
        double ffA = Constants.ElevatorffA0Tote.getDouble();

        ffPosition = Constants.ElevatorffP0Tote.getDouble();
        pv.setGains(p, v, ffV, ffA);
        pv.setTunedVoltage(12.5);
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("ElevatorSetPointElevation", setPointElevation);
        SmartDashboard.putNumber("ElevatorCurrentPosition", currentPosition);
        SmartDashboard.putNumber("ElevatorCurrentVelocity", currentVelocity);
        SmartDashboard.putNumber("ElevatorTargetPosition", targetPosition);
        SmartDashboard.putNumber("ElevatorTargetVelocity", targetVelocity);
        SmartDashboard.putNumber("ElevatorTargetAcceleration", targetAcceleration);
        SmartDashboard.putBoolean("ElevatorDone", isDone());
        SmartDashboard.putNumber("ElevatorMotorSpeed", motorSpeed);
    }

    @Override
    public String getLogNames() {
        return "SetPointElevation,E_CurrentPosition,E_CurrentVelocity,E_TargetPosition,E_TargetVelocity,E_MotorSpeed,";
    }

    @Override
    public String getLogValues() {
        return setPointElevation + "," + currentPosition + "," + currentVelocity + "," + targetPosition + "," + targetVelocity + "," + motorSpeed + ",";
    }
}
