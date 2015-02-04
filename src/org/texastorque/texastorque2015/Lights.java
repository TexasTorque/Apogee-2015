package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.DriverStation;
import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.torquelib.arduino.I2CArduinoLights;

public class Lights {
    
    private Input input;
    private Feedback feedback;
    
    private I2CArduinoLights lightStrip;
    private DriverStation ds;

    public Lights() {
        lightStrip = new I2CArduinoLights(1);
        ds = DriverStation.getInstance();
    }
    
    public void calcLightState() {
        if (ds.isEnabled()) {
            if (ds.getAlliance() == DriverStation.Alliance.Blue) {
                lightStrip.set(I2CArduinoLights.LightState.ENABLED_BLUE);
            } else if (ds.getAlliance() == DriverStation.Alliance.Red) {
                lightStrip.set(I2CArduinoLights.LightState.ENABLED_RED);
            } else {
                lightStrip.set(I2CArduinoLights.LightState.WHITE);
            }
        } else {
            lightStrip.set(I2CArduinoLights.LightState.DISABLED);
        }
    }
    
    public void setInput(Input input) {
        this.input = input;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
    
}
