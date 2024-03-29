package org.texastorque.texastorque2015;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.auto.AutoPicker;
import org.texastorque.texastorque2015.feedback.DashboardFeedback;
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
import org.texastorque.texastorque2015.subsystem.Stingers;
import org.texastorque.torquelib.base.TorqueIterative;
import org.texastorque.torquelib.util.Parameters;
import org.texastorque.torquelib.util.TorqueLogging;

public class Robot extends TorqueIterative {

    //Subsystems
    Drivebase drivebase;
    Elevator elevator;
    Arms arms;
    Intake intake;
    Stingers stingers;

    //Input
    Input activeInput;
    DriverInput driverInput;

    //Output
    Output activeOutput;
    RobotOutput robotOutput;

    //Feedback
    Feedback activeFeedback;
    SensorFeedback sensorFeedback;
    DashboardFeedback dashFeedback;

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
        stingers = new Stingers();

        lights = new Lights();

        driverInput = new DriverInput();
        robotOutput = new RobotOutput();
        sensorFeedback = new SensorFeedback();
        dashFeedback = new DashboardFeedback();

        AutoPicker.init();

        logger = new TorqueLogging();
        logger.addLoggable(drivebase);
        logger.addLoggable(elevator);
        logger.addLoggable(arms);
        logger.addLoggable(intake);
        logger.addLoggable(stingers);
        logger.addLoggable(new PDPLogger());

        numcycles = 0;
    }

    //Update Input, Output, Feedback for all subsystems.
    private void updateIO() {
        driverInput.setFeedback(activeFeedback);

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
        
        stingers.setInput(activeInput);
        stingers.setOutput(activeOutput);
        stingers.setFeedback(activeFeedback);

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
        stingers.loadParams();
    }

    //Push all sybsystems to dashboard.
    private void pushToDashboard() {
        activeInput.pushToDashboard();
        drivebase.pushToDashboard();
        elevator.pushToDashboard();
        arms.pushToDashboard();
        intake.pushToDashboard();
        stingers.pushToDashboard();
    }

    private void initSubsystems() {
        drivebase.init();
        elevator.init();
        stingers.init();
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

        drivebase.setOutputEnabled(true);
        elevator.setOutputEnabled(true);
        arms.setOutputEnabled(true);
        intake.setOutputEnabled(true);
        stingers.setOutputEnabled(true);

        logger.reset();
        logger.enable();

        numcycles = 0;
    }

    @Override
    public void teleopPeriodic() {
        activeInput.run();
        lights.calcLightState();
        logger.log();
        pushToDashboard();
    }

    @Override
    public void teleopContinuous() {
        activeFeedback.run();

        drivebase.run();
        elevator.run();
        arms.run();
        intake.run();
        stingers.run();

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
        elevator.setOutputEnabled(true);
        intake.setOutputEnabled(true);
        arms.setOutputEnabled(true);
        stingers.setOutputEnabled(true);

        activeInput.setFeedback(activeFeedback);

        Thread autoThread = new Thread(activeInput);
        autoThread.start();

        numcycles = 0;
        
        logger.reset();
        logger.enable();
    }

    @Override
    public void autonomousPeriodic() {
        pushToDashboard();
        logger.log();
        stingers.run();
    }

    @Override
    public void autonomousContinuous() {
        activeFeedback.run();

        drivebase.run();
        elevator.run();
        arms.run();
        intake.run();
        

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
        elevator.setOutputEnabled(false);
        arms.setOutputEnabled(false);
        intake.setOutputEnabled(false);

        initSubsystems();

        logger.close();
    }

    @Override
    public void disabledContinuous() {
        drivebase.run();
        elevator.run();
        arms.run();
        intake.run();
        
        activeFeedback.run();
    }

    @Override
    public void disabledPeriodic() {
        lights.calcLightState();
        pushToDashboard();
    }
}
