package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.feedback.SensorFeedback;
import org.texastorque.texastorque2015.input.DriverInput;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.texastorque2015.output.Output;
import org.texastorque.texastorque2015.output.RobotOutput;
import org.texastorque.texastorque2015.subsystem.Drivebase;
import org.texastorque.torquelib.base.TorqueIterative;

public class Robot extends TorqueIterative {

    //Subsystems
    Drivebase drivebase;

    //Input
    Input activeInput;
    DriverInput driverInput;

    //Output
    Output activeOutput;
    RobotOutput robotOutput;

    //Feedback
    Feedback activeFeedback;
    SensorFeedback sensorFeedback;

    @Override
    public void robotInit() {
        drivebase = new Drivebase();
        
        driverInput = new DriverInput();
        robotOutput = new RobotOutput();
        sensorFeedback = new SensorFeedback();
    }

    @Override
    public void teleopInit() {
        activeInput = driverInput;
        activeOutput = robotOutput;
        activeFeedback = sensorFeedback;

        drivebase.setInput(activeInput);
        drivebase.setOutput(activeOutput);
        drivebase.setFeedback(activeFeedback);
        drivebase.setOutputEnabled(true);

        drivebase.loadParams();
    }

    @Override
    public void teleopPeriodic() {
        activeInput.run();
        drivebase.pushToDashboard();
    }

    @Override
    public void teleopContinuous() {
        activeFeedback.run();
        
        drivebase.run();
    }

}
