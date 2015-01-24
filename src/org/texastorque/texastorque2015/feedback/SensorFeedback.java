package org.texastorque.texastorque2015.feedback;

import edu.wpi.first.wpilibj.CounterBase;
import org.texastorque.texastorque2015.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;

public class SensorFeedback extends Feedback {

    private TorqueEncoder leftEncoder;
    private TorqueEncoder rightEncoder;

    public SensorFeedback() {
        rightEncoder = new TorqueEncoder(Ports.LEFT_ENCODER_PORT_A, Ports.LEFT_ENCODER_PORT_B, true, CounterBase.EncodingType.k2X);
        leftEncoder = new TorqueEncoder(Ports.RIGHT_ENCODER_PORT_A, Ports.RIGHT_ENCODER_PORT_B, false, CounterBase.EncodingType.k2X);
    }

    @Override
    public void run() {
        leftDrivePosition = leftEncoder.getDistance() * 0.07539822368;//250 clicks/rot & 6 in diam
        rightDrivePosition = rightEncoder.getDistance() * 0.07539822368;
    }
}
