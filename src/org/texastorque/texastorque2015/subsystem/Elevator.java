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

    //up = positive, down = negative
    private double motorSpeed;

    public Elevator() {
        tmp = new TorqueTMP(0.0, 0.0);
        pv = new TorquePV();
    }

    @Override
    public void loadParams() {
        tmp = new TorqueTMP(Constants.ElevatorMaxV.getDouble(), Constants.ElevatorMaxA.getDouble());

        double p = Constants.ElevatorP.getDouble();
        double v = Constants.ElevatorV.getDouble();
        double ffV = Constants.ElevatorffV.getDouble();
        double ffA = Constants.ElevatorffA.getDouble();

        pv.setGains(p, v, ffV, ffA);

        ffPosition = Constants.ElevatorffP.getDouble();
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("ElevatorSetPointElevation", setPointElevation);
        SmartDashboard.putNumber("ElevatorCurrentPosition", currentPosition);
        SmartDashboard.putNumber("ElevatorCurrentVelocity", currentVelocity);
        SmartDashboard.putNumber("ElevatorTargetPosition", targetPosition);
        SmartDashboard.putNumber("ElevatorTargetVelocity", targetVelocity);
        SmartDashboard.putNumber("ElevatorTargetAcceleration", targetAcceleration);
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

        if (outputEnabled) {
            output.setElevatorMotorSpeeds(motorSpeed);
        }
    }

}
