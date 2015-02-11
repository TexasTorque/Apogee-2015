package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.torquelib.arduino.I2CArduinoLights;

public class Lights {

    private Input input;
    private Feedback feedback;

    private I2CArduinoLights lightStrip;
    private DriverStation ds;

    private double elevatorStillTime;
    private boolean flashedGreen;

    public Lights() {
        lightStrip = new I2CArduinoLights(84);
        ds = DriverStation.getInstance();
    }

    public void calcLightState() {
        if (ds.isEnabled()) {
            if (ds.getAlliance() == DriverStation.Alliance.Blue) {
                if (feedback.getElevatorVelocity() > 0.1) {
                    lightStrip.set(I2CArduinoLights.LightState.BLUE_GREEN);
                    flashedGreen = false;
                } else if (feedback.getElevatorVelocity() < 0.1) {
                    lightStrip.set(I2CArduinoLights.LightState.BLUE_GREEN_REVERSE);
                    flashedGreen = false;
                } else {
                    if (!flashedGreen) {
                        lightStrip.set(I2CArduinoLights.LightState.GREEN);
                        elevatorStillTime = Timer.getFPGATimestamp();
                        flashedGreen = true;
                    }
                    if (Timer.getFPGATimestamp() - 0.25 > elevatorStillTime) {
                        lightStrip.set(I2CArduinoLights.LightState.ENABLED_BLUE);
                    }
                }
            } else if (ds.getAlliance() == DriverStation.Alliance.Red) {
                if (feedback.getElevatorVelocity() > 0.1) {
                    lightStrip.set(I2CArduinoLights.LightState.RED_GREEN);
                    flashedGreen = false;
                } else if (feedback.getElevatorVelocity() < 0.1) {
                    lightStrip.set(I2CArduinoLights.LightState.RED_GREEN_REVERSE);
                    flashedGreen = false;
                } else {
                    if (!flashedGreen) {
                        lightStrip.set(I2CArduinoLights.LightState.GREEN);
                        elevatorStillTime = Timer.getFPGATimestamp();
                        flashedGreen = true;
                    }
                    if (Timer.getFPGATimestamp() - 0.25 > elevatorStillTime) {
                        lightStrip.set(I2CArduinoLights.LightState.ENABLED_RED);
                    }
                }
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
