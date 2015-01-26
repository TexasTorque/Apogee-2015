package org.texastorque.texastorque2015.auto;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoCommand {

    protected double startTime;
    protected double timeOut;
    protected boolean timedOut;

    protected boolean firstCycle;
    protected String name;

    public AutoCommand(String nm) {
        this.name = nm;
    }

    public boolean isTimedOut() {
        timedOut = Timer.getFPGATimestamp() - startTime > timeOut;
        return timedOut;
    }

    public abstract void run();

    public abstract void isDone();

    public abstract void reset();
}
