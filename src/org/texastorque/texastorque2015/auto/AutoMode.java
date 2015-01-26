package org.texastorque.texastorque2015.auto;

import org.texastorque.texastorque2015.input.Input;

public abstract class AutoMode extends Input {
    protected void wait(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException ex) {
        }
    }
}
