package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoCommand {

    protected double startTime;
    protected double timeOut;
    protected boolean timedOut;
    
    protected double doneCycles;
    protected double minDoneCycles;
    
    protected boolean firstCycle;
    protected String name;

    public AutoCommand(String nm, double minDoneCycles) {
        this.name = nm;
        this.minDoneCycles = minDoneCycles;
    }

    public boolean isTimedOut() {
        timedOut = Timer.getFPGATimestamp() - startTime > timeOut;
        return timedOut;
    }

    public abstract void run();

    public abstract boolean isDone();

    public abstract void reset();
    
    public abstract void stop();
}
