package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.input.Input;

public abstract class AutoMode extends Input {

    public class DriveDistance extends AutoCommand {

        private double distance;
        private double doneRange;
        
        public DriveDistance(String name, double distance, double tolerance, double doneCycles) {
            super(name, doneCycles);
            this.distance = distance;
            this.doneRange = tolerance;
        }

        @Override
        public void run() {
            //Implement TMP
        }

        @Override
        public boolean isDone() {
            if (Math.abs((feedback.getLeftDrivePosition() + feedback.getRightDrivePosition() / 2) - distance) <= doneRange) {
                doneCycles++;
            }
            return doneCycles >= minDoneCycles;
        }

        @Override
        public void reset() {
        }

    }

    protected void wait(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException ex) {
        }
    }
}
