package org.texastorque.texastorque2015.output;

import edu.wpi.first.wpilibj.VictorSP;
import org.texastorque.torquelib.component.Motor;

public class RobotOutput extends Output {
    
    private Motor leftDriveAMotor;
    private Motor leftDriveBMotor;
    private Motor rightDriveAMotor;
    private Motor rightDriveBMotor;
    private Motor frontStrafeMotor;
    private Motor rearStrafeMotor;
    
    public RobotOutput() {
        leftDriveAMotor = new Motor(new VictorSP(0), false);
        leftDriveBMotor = new Motor(new VictorSP(1), false);
        rightDriveAMotor = new Motor(new VictorSP(2), false);
        rightDriveBMotor = new Motor(new VictorSP(3), false);
        frontStrafeMotor = new Motor(new VictorSP(4), false);
        rearStrafeMotor = new Motor(new VictorSP(5), false);
    }

    @Override
    public void setDriveSpeeds(double left, double right, double frontStrafe, double rearStrafe) {
        leftDriveAMotor.set(left);
        leftDriveBMotor.set(left);
        rightDriveAMotor.set(right);
        rightDriveBMotor.set(right);
        frontStrafeMotor.set(frontStrafe);
        rearStrafeMotor.set(rearStrafe);
    }

}
