package org.texastorque.texastorque2015;

import org.texastorque.texastorque2015.auto.AutoPicker;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.feedback.SensorFeedback;
import org.texastorque.texastorque2015.input.DriverInput;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.texastorque2015.output.Output;
import org.texastorque.texastorque2015.output.RobotOutput;
import org.texastorque.texastorque2015.subsystem.Drivebase;
import org.texastorque.texastorque2015.subsystem.Elevator;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;

public class Robot extends TorqueIterative {

    //Subsystems
    Drivebase drivebase;
    Elevator elevator;

    //Input
    Input activeInput;
    DriverInput driverInput;

    //Output
    Output activeOutput;
    RobotOutput robotOutput;

    //Feedback
    Feedback activeFeedback;
    SensorFeedback sensorFeedback;

    //Parameters
    Parameters params;

    @Override
    public void robotInit() {
        params = new Parameters();
        double temp = Constants.ignoreThis.getDouble();
        params.load();
        
        drivebase = new Drivebase();
        elevator = new Elevator();

        driverInput = new DriverInput();
        robotOutput = new RobotOutput();
        sensorFeedback = new SensorFeedback();
        
        AutoPicker.init();
    }

    // ----- Teleop -----
    @Override
    public void teleopInit() {
        params.load();

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

    // ----- Autonomous -----
    @Override
    public void autonomousInit() {
        params.load();
        
        activeInput = AutoPicker.getAutonomous();
        activeOutput = robotOutput;
        activeFeedback = sensorFeedback;
        
        drivebase.setInput(activeInput);
        drivebase.setOutput(activeOutput);
        drivebase.setFeedback(activeFeedback);
        drivebase.setOutputEnabled(true);
        
        elevator.setInput(activeInput);
        elevator.setOutput(activeOutput);
        elevator.setFeedback(activeFeedback);

        activeInput.setFeedback(activeFeedback);
        
        Thread autoThread = new Thread(activeInput);
        autoThread.start();
    }

    @Override
    public void autonomousPeriodic() {
        drivebase.pushToDashboard();
    }

    @Override
    public void autonomousContinuous() {
        activeFeedback.run();
        
        drivebase.run();
        elevator.run();
    }
}
