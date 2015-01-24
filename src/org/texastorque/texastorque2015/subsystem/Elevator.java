package org.texastorque.texastorque2015.subsystem;

import org.TexasTorque.Torquelib.controlloop.TorquePV;
import org.TexasTorque.Torquelib.controlloop.TorqueTMP;

public class Elevator extends Subsystem {

    private TorqueTMP tmp;
    private TorquePV pv;

    private double setPointElevation;
    private double targetPosition;
    private double targetVelocity;
    private double targetAcceleration;

    private double currentPosition;
    private double currentVelocity;

    //up = positive, down = negative
    private double motorSpeed;

    public Elevator() {
        tmp = new TorqueTMP(0.0, 0.0);
        pv = new TorquePV();
    }

    @Override
    public void loadParams() {
    }

    @Override
    public void pushToDashboard() {
    }

    @Override
    public void run() {
        currentPosition = feedback.getElevatorHeight();
        currentVelocity = feedback.getElevatorVelocity();

        if (input.getElevatorPosition() != setPointElevation) {
            setPointElevation = input.getElevatorPosition();

            tmp.generateTrapezoid(setPointElevation, currentPosition, currentVelocity);
        }

        motorSpeed = pv.calculate(tmp, currentPosition, currentVelocity);

        if (outputEnabled) {
            output.setElevatorMotorSpeeds(motorSpeed);
        }
    }

}
