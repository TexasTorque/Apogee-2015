package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;

public class SensorFeedback extends Feedback {

    private TorqueEncoder leftDriveEncoder;
    private TorqueEncoder rightDriveEncoder;

    public SensorFeedback() {
        rightDriveEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k2X);
        leftDriveEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);
    }

    @Override
    public void run() {
        leftDriveEncoder.calc();
        rightDriveEncoder.calc();
        
        leftDrivePosition = leftDriveEncoder.get() * 0.07539822368;//250 clicks/rot & 6 in diam
        rightDrivePosition = rightDriveEncoder.get() * 0.07539822368;

        leftDriveVelocity = leftDriveEncoder.getAverageRate()  * 0.07539822368;
        rightDriveVelocity = rightDriveEncoder.getAverageRate() * 0.07539822368;
    }
}
