package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;

public abstract class AutoMode extends Input {

    public class DriveDistance extends AutoCommand {

        private double distance;
        private double doneRange;

        private TorqueTMP profile;
        private TorquePV pv;

        public DriveDistance(String name, double distance, double tolerance, double doneCycles) {
            super(name, doneCycles);
            this.distance = distance;
            this.doneRange = tolerance;

            profile = new TorqueTMP(8, 3);
            pv = new TorquePV();
            pv.setGains(Constants.DrivebaseP.getDouble(), Constants.DrivebaseV.getDouble(),
                    Constants.ElevatorffV.getDouble(), Constants.DrivebaseffA.getDouble());
            pv.setTunedVoltage(12.5);

            reset();

            profile.generateTrapezoid(10, 0.0, 0.0);
        }

        @Override
        public void run() {
            double currentVelocity = (feedback.getLeftDriveVelocity() + feedback.getElevatorVelocity()) / 2;
            double currentPosition = (feedback.getLeftDrivePosition() + feedback.getRightDrivePosition()) / 2;
            
            SmartDashboard.putNumber("currentV", currentVelocity);
            SmartDashboard.putNumber("currentP", currentPosition);
            
            profile.calculateNextSituation();
            
            SmartDashboard.putNumber("targetV", profile.getCurrentVelocity());
            SmartDashboard.putNumber("targetP", profile.getCurrentPosition());
            
            double output = pv.calculate(profile,
                    (feedback.getLeftDrivePosition() + feedback.getRightDrivePosition()) / 2,
                    (feedback.getLeftDriveVelocity() + feedback.getRightDriveVelocity()) / 2);

            leftSpeed = output;
            rightSpeed = output;
        }

        @Override
        public boolean isDone() {
            if (Math.abs((feedback.getLeftDrivePosition() + feedback.getRightDrivePosition() / 2) - distance) <= doneRange) {
                doneCycles++;
            }
            return doneCycles >= minDoneCycles;
        }

        @Override
        public final void reset() {
            feedback.resetDriveEncoders();
        }
    }
    
    protected void runCommand(AutoCommand command) {
        while (!command.isDone()) {
            command.run();
            wait(0.01);
        }
    }

    protected void wait(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException ex) {
        }
    }
}
