package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.texastorque2015.constants.Constants;

public abstract class AutoMode extends Input {

    public class DriveDistance extends AutoCommand {

        private double distance;
        private double doneRange;

        public DriveDistance(String name, double distance, double tolerance, double doneCycles, double timeOut) {
            super(name, doneCycles, timeOut);

            this.distance = distance;
            this.doneRange = tolerance;

            drivebaseControlled = true;
            driveDistance = distance;
            driveAngle = 0.0;

            feedback.resetDriveEncoders();
            
            startTime = Timer.getFPGATimestamp();
        }

        @Override
        public boolean isDone() {
            if (Math.abs((feedback.getLeftDrivePosition() + feedback.getRightDrivePosition()) / 2 - distance) <= doneRange) {
                doneCycles++;
            }
            return doneCycles >= minDoneCycles;
        }

        @Override
        public void stop() {
            drivebaseControlled = false;
            leftSpeed = 0.0;
            rightSpeed = 0.0;
            feedback.resetDriveEncoders();
        }
    }

    public class TurnAngle extends AutoCommand {

        private double angle;
        private double doneRange;

        public TurnAngle(String name, double angle, double tolerance, double doneCycles, double timeOut) {
            super(name, doneCycles, timeOut);

            this.angle = angle;
            this.doneRange = tolerance;

            drivebaseControlled = true;
            driveAngle = angle;
            driveDistance = 0.0;

            feedback.resetGyro();
        }

        @Override
        public boolean isDone() {
            if (Math.abs(feedback.getAngle() - angle) <= doneRange) {
                doneCycles++;
            }
            return doneCycles > minDoneCycles;
        }

        @Override
        public void stop() {
            drivebaseControlled = false;
            leftSpeed = 0.0;
            rightSpeed = 0.0;
            feedback.resetGyro();
        }
    }

    public class ToteStack extends AutoCommand {

        private boolean wentDown;

        public ToteStack(String name, double doneCycles, double timeOut) {
            super(name, doneCycles, timeOut);
            wentDown = false;
        }

        @Override
        public void run() {
            if (!wentDown) {
                elevatorPosition = Constants.FloorElevatorLevel1.getDouble();
                if (feedback.isElevatorDone()) {
                    wentDown = true;
                }
            } else {
                elevatorPosition = Constants.FloorElevatorLevel2.getDouble();
                numTotes++;
            }
        }

        @Override
        public boolean isDone() {
            if (elevatorPosition == Constants.FloorElevatorLevel2.getDouble() && feedback.isElevatorDone() && wentDown) {
                doneCycles++;
            }
            return doneCycles > minDoneCycles;
        }
    }

    protected void runCommand(AutoCommand command) {
        command.reset();
        System.out.println("Reset" + command.name);
        while (!command.isDone() && !command.isTimedOut()) {
            command.run();
            wait(0.01);
        }
        command.stop();
        System.out.println("stop" + command.name);
    }

    protected void wait(double time) {
        double startTime = Timer.getFPGATimestamp();

        while (Timer.getFPGATimestamp() < startTime + time) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
}
