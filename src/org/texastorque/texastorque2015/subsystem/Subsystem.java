package org.texastorque.texastorque2015.subsystem;

import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.texastorque2015.output.Output;
import org.texastorque.torquelib.util.Loggable;

public abstract class Subsystem implements Runnable, Loggable {

    protected Input input;
    protected Output output;
    protected Feedback feedback;

    public void setInput(Input input) {
        this.input = input;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    protected boolean outputEnabled;

    public void setOutputEnabled(boolean outputEnabled) {
        this.outputEnabled = outputEnabled;
    }

    public abstract void loadParams();

    public abstract void pushToDashboard();

    public abstract void init();

}
