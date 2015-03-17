package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.texastorque.texastorque2015.constants.Constants;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;
import org.texastorque.torquelib.component.TorqueGyro;
import org.texastorque.torquelib.component.TorquePotentiometer;

public class SensorFeedback extends Feedback {
    
    //PD Panel
    PowerDistributionPanel pdp;

    //Drivebase
    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;
    private TorqueGyro gyro;

    //Elevator
    private TorqueEncoder elevatorEncoder;
    private DigitalInput topLimit;
    private DigitalInput bottomLimit;

    //Sluice
    private DigitalInput sluiceLimitSwitch;

    //Arms
    private TorquePotentiometer leftTiltPot;
    private TorquePotentiometer rightTiltPot;

    public SensorFeedback() {
        pdp = new PowerDistributionPanel();
        
        leftDriveEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k2X);
        rightDriveEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);
        gyro = new TorqueGyro(Ports.GYRO_PORT_A, Ports.GYRO_PORT_B);

        elevatorEncoder = new TorqueEncoder(Ports.LEFT_ELEVATOR_ENCODER_PORT_A, Ports.LEFT_ELEVATOR_ENCODER_PORT_B, false, CounterBase.EncodingType.k4X);
        topLimit = new DigitalInput(Ports.ELEVATOR_TOP_LIMIT);
        bottomLimit = new DigitalInput(Ports.ELEVATOR_BOTTOM_LIMIT);

        sluiceLimitSwitch = new DigitalInput(Ports.SLUICE_BUTTON);
        
        leftTiltPot = new TorquePotentiometer(Ports.LEFT_TILT_POT);
        rightTiltPot = new TorquePotentiometer(Ports.RIGHT_TILT_POT);
    }

    @Override
    public void loadParams() {
        leftTiltPot.setInputRange(Constants.leftBottomVoltage.getDouble(), Constants.leftTopVoltage.getDouble());
        rightTiltPot.setInputRange(Constants.rightBottomVoltage.getDouble(), Constants.rightTopVoltage.getDouble());
        
        leftTiltPot.setPositionRange(Constants.tiltMaxAngle.getDouble(), Constants.tiltMinAngle.getDouble());
        rightTiltPot.setPositionRange(Constants.tiltMaxAngle.getDouble(), Constants.tiltMinAngle.getDouble());
    }

    @Override
    public void run() {
        //Drivebase
        //Units use feet and seconds.
        leftDriveEncoder.calc();
        rightDriveEncoder.calc();

        leftDrivePosition = leftDriveEncoder.get() * 0.0062831853;//250 clicks/rot & 6 in diam
        rightDrivePosition = rightDriveEncoder.get() * 0.0062831853;

        leftDriveVelocity = leftDriveEncoder.getRate() * 0.0062831853;
        rightDriveVelocity = rightDriveEncoder.getRate() * 0.0062831853;

        leftDriveAcceleration = leftDriveEncoder.getAcceleration() * 0.0062831853;
        rightDriveAcceleration = rightDriveEncoder.getAcceleration() * 0.0062831853;

        //Elevator
        elevatorEncoder.calc();

        elevatorHeight = elevatorEncoder.get() * 0.0225; //Put in conversion
        elevatorVelocity = elevatorEncoder.getAverageRate() * 0.0225; //Put in conversion

        elevatorAtTop = topLimit.get();
        elevatorAtBottom = bottomLimit.get();

        //Sluice
        if (!toteInSluice && !sluiceLimitSwitch.get()) {
            toteSlideTime = Timer.getFPGATimestamp();
        }
        toteInSluice = !sluiceLimitSwitch.get();

        //angle
        angle = gyro.getAngle();
        angularVelocity = gyro.getRate();
        
        leftTiltAngle = leftTiltPot.getPosition();
        rightTiltAngle = rightTiltPot.getPosition();
        
        SmartDashboard.putNumber("leftTiltVoltage", leftTiltPot.getRawVoltage());
        SmartDashboard.putNumber("rightTiltVoltage", rightTiltPot.getRawVoltage());
    }

    @Override
    public void resetDriveEncoders() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }
    
    @Override
    public void resetElevatorEncoders() {
        elevatorEncoder.reset();
    }

    @Override
    public void resetGyro() {
        gyro.reset();
    }
}
