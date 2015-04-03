package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
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

    //Elevator
    private TorqueEncoder elevatorEncoder;
    //private DigitalInput topLimit;
    //private DigitalInput bottomLimit;

    //Angle
    private TorqueGyro gyro;
    private double angleOffset;
    private double prevTime;

    //Stingers
    private TorquePotentiometer leftStingerPot;
    private TorquePotentiometer rightStingerPot;

    public SensorFeedback() {
        pdp = new PowerDistributionPanel();

        leftDriveEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k1X);
        rightDriveEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k1X);

        elevatorEncoder = new TorqueEncoder(Ports.LEFT_ELEVATOR_ENCODER_PORT_A, Ports.LEFT_ELEVATOR_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);
        //topLimit = new DigitalInput(Ports.ELEVATOR_TOP_LIMIT);
        //bottomLimit = new DigitalInput(Ports.ELEVATOR_BOTTOM_LIMIT);

        gyro = new TorqueGyro(Ports.GYRO_PORT_A, Ports.GYRO_PORT_B);
        angleOffset = gyro.getAngle();

        leftStingerPot = new TorquePotentiometer(Ports.LEFT_STINGER_POT_PORT);
        leftStingerPot.setInputRange(Constants.LEFT_STINGER_VOLTAGE_UP.getDouble(), Constants.LEFT_STINGER_VOLTAGE_DOWN.getDouble());
        leftStingerPot.setPositionRange(90, 0);
        rightStingerPot = new TorquePotentiometer(Ports.RIGHT_STINGER_POT_PORT);
        rightStingerPot.setInputRange(Constants.RIGHT_STINGER_VOLTAGE_UP.getDouble(), Constants.RIGHT_STINGER_VOLTAGE_DOWN.getDouble());
        rightStingerPot.setPositionRange(90, 0);
    }

    @Override
    public void run() {
        SmartDashboard.putNumber("leftstingervalue", leftStingerPot.getRaw());
        SmartDashboard.putNumber("rightstingervalue", rightStingerPot.getRaw());
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

        //elevatorAtTop = topLimit.get();
        //elevatorAtBottom = bottomLimit.get();
        //angle
        //angularVelocity = (gyro.getAngle() - angle) / (Timer.getFPGATimestamp() - prevTime);
        prevTime = Timer.getFPGATimestamp();
        angle = gyro.getAngle() - angleOffset;

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
        angleOffset = gyro.getAngle();
    }
}
