package org.texastorque.texastorque2015;

import org.texastorque.texastorque2015.feedback.Feedback;
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

    //Parameters
    Parameters params;

    @Override
    public void robotInit() {
        drivebase = new Drivebase();
        elevator = new Elevator();
        driverInput = new DriverInput();
        robotOutput = new RobotOutput();
        params = new Parameters();
    }

    @Override
    public void teleopInit() {
        params.load();

        activeInput = driverInput;
        activeOutput = robotOutput;

        drivebase.setInput(activeInput);
        drivebase.setOutput(activeOutput);
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
        drivebase.run();
        elevator.run();
    }

    @Override
    public void autonomousInit() {
        params.load();
    }
}
