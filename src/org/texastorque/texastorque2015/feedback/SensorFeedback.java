package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;

public class SensorFeedback extends Feedback {

    //Drivebase
    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;

    public SensorFeedback() {
        rightDriveEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k2X);
        leftDriveEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);
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
    }

    @Override
    public void resetDriveEncoders() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }
}
