package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;
import org.texastorque.torquelib.component.TorqueGyro;

public class SensorFeedback extends Feedback {

    //Drivebase
    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;

    //Elevator
    private TorqueEncoder elevatorEncoder;
    private DigitalInput topLimit;
    private DigitalInput bottomLimit;

    //Sluice
    private DigitalInput sluiceLimitSwitch;

    //Angle
    private TorqueGyro gyro;

    public SensorFeedback() {
        leftDriveEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k2X);
        rightDriveEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);

        elevatorEncoder = new TorqueEncoder(Ports.ELEVATOR_ENCODER_PORT_A, Ports.ELEVATOR_ENCODER_PORT_B, false, CounterBase.EncodingType.k4X);
        topLimit = new DigitalInput(Ports.ELEVATOR_TOP_LIMIT);
        bottomLimit = new DigitalInput(Ports.ELEVATOR_BOTTOM_LIMIT);

        sluiceLimitSwitch = new DigitalInput(Ports.SLUICE_BUTTON);

        gyro = new TorqueGyro(Ports.GYRO_PORT_A, Ports.GYRO_PORT_B);
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
        elevatorVelocity = elevatorEncoder.getRate() * 0.0225; //Put in conversion

        elevatorAtTop = topLimit.get();
        elevatorAtBottom = bottomLimit.get();

        //Sluice
        toteInSluice = topLimit.get();

        //angle (radians)
        angle = gyro.getAngle();
        angularVelocity = gyro.getRate();
    }

    @Override
    public void resetDriveEncoders() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }

    @Override
    public void resetGyro() {
        gyro.reset();
    }
}
