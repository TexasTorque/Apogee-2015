package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.auto.AutoPicker;
import org.texastorque.texastorque2015.feedback.Feedback;
import org.texastorque.texastorque2015.feedback.SensorFeedback;
import org.texastorque.texastorque2015.input.DriverInput;
import org.texastorque.texastorque2015.input.Input;
import org.texastorque.texastorque2015.output.Output;
import org.texastorque.texastorque2015.output.RobotOutput;
import org.texastorque.texastorque2015.subsystem.Arms;
import org.texastorque.texastorque2015.subsystem.Drivebase;
import org.texastorque.texastorque2015.subsystem.Elevator;
import org.texastorque.texastorque2015.subsystem.Intake;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;
import org.texastorque.torquelib.util.TorqueLogging;

public class Robot extends TorqueIterative {

    //Subsystems
    Drivebase drivebase;
    Elevator elevator;
    Arms arms;
    Intake intake;

    //Input
    Input activeInput;
    DriverInput driverInput;

    //Output
    Output activeOutput;
    RobotOutput robotOutput;

    //Feedback
    Feedback activeFeedback;
    SensorFeedback sensorFeedback;

    //Other
    TorqueLogging logger;
    Lights lights;

    private volatile int numcycles;

    @Override
    public void robotInit() {
        Parameters.makeFile();
        Parameters.load();

        drivebase = new Drivebase();
        elevator = new Elevator();
        arms = new Arms();
        intake = new Intake();
        
        lights = new Lights();

        driverInput = new DriverInput();
        robotOutput = new RobotOutput();
        sensorFeedback = new SensorFeedback();

        AutoPicker.init();

        logger = new TorqueLogging();
        logger.addLoggable(drivebase);
        logger.addLoggable(elevator);
        logger.addLoggable(arms);
        logger.addLoggable(intake);

        numcycles = 0;
    }

    //Update Input, Output, Feedback for all subsystems.
    private void updateIO() {
        drivebase.setInput(activeInput);
        drivebase.setOutput(activeOutput);
        drivebase.setFeedback(activeFeedback);

        elevator.setInput(activeInput);
        elevator.setOutput(activeOutput);
        elevator.setFeedback(activeFeedback);

        arms.setInput(activeInput);
        arms.setOutput(activeOutput);
        arms.setFeedback(activeFeedback);

        intake.setInput(activeInput);
        intake.setOutput(activeOutput);
        intake.setFeedback(activeFeedback);
        
        lights.setInput(activeInput);
        lights.setFeedback(activeFeedback);
    }

    //Load params for all subsystems.
    private void loadParams() {
        Parameters.load();

        drivebase.loadParams();
        elevator.loadParams();
        arms.loadParams();
        intake.loadParams();
    }

    //Push all sybsystems to dashboard.
    private void pushToDashboard() {
        drivebase.pushToDashboard();
        elevator.pushToDashboard();
        arms.pushToDashboard();
        intake.pushToDashboard();
    }

    private void initSubsystems() {
        drivebase.enable();
    }

    // ----- Teleop -----
    @Override
    public void teleopInit() {
        activeInput = driverInput;
        activeOutput = robotOutput;
        activeFeedback = sensorFeedback;

        updateIO();
        loadParams();
        initSubsystems();

        drivebase.setOutputEnabled(false);

        logger.reset();
        logger.enable();

        numcycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        activeInput.run();

        pushToDashboard();
    }

    @Override
    public void teleopContinuous() {
        activeFeedback.run();

        drivebase.run();

        logger.log();

        SmartDashboard.putNumber("NumCycles", numcycles++);
    }

    // ----- Autonomous -----
    @Override
    public void autonomousInit() {
        activeInput = AutoPicker.getAutonomous();
        activeOutput = robotOutput;
        activeFeedback = sensorFeedback;

        updateIO();
        loadParams();
        initSubsystems();

        drivebase.setOutputEnabled(true);

        activeInput.setFeedback(activeFeedback);

        Thread autoThread = new Thread(activeInput);
        autoThread.start();

        numcycles = 0;
    }

    @Override
    public void autonomousPeriodic() {
        pushToDashboard();
    }

    @Override
    public void autonomousContinuous() {
        activeFeedback.run();

        drivebase.run();

        SmartDashboard.putNumber("NumCycles", numcycles++);
    }

    // ----- Disabled -----
    @Override
    public void disabledInit() {
        activeInput = driverInput;
        activeOutput = robotOutput;
        activeFeedback = sensorFeedback;

        updateIO();

        drivebase.setOutputEnabled(false);

        initSubsystems();

        logger.close();
    }

    @Override
    public void disabledContinuous() {
        drivebase.run();
    }

    @Override
    public void disabledPeriodic() {
        activeInput.run();
    }
}
