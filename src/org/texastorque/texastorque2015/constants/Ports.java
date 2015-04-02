package org.texastorque.texastorque2015.constants;

public class Ports {

    //Drivebase motor ports
    public final static int LEFT_A_DRIVE_PORT = 11;//unused
    public final static int LEFT_B_DRIVE_PORT = 3;
    public final static int RIGHT_A_DRIVE_PORT = 4;
    public final static int RIGHT_B_DRIVE_PORT = 12;//unused
    public final static int STRAFE_PORT = 2;

    //Elevator
    public final static int LEFT_ELEVATOR = 5;
    public final static int RIGHT_ELEVATOR = 8;

    //encoders
    public final static int RIGHT_ENCODER_PORT_A = 3;
    public final static int RIGHT_ENCODER_PORT_B = 2;
    public final static int LEFT_ENCODER_PORT_A = 0;
    public final static int LEFT_ENCODER_PORT_B = 1;
    public final static int LEFT_ELEVATOR_ENCODER_PORT_A = 6;
    public final static int LEFT_ELEVATOR_ENCODER_PORT_B = 7;
    public final static int RIGHT_ELEVATOR_ENCODER_PORT_A = 8;
    public final static int RIGHT_ELEVATOR_ENCODER_PORT_B = 9;

    //Limit switches
    public final static int ELEVATOR_TOP_LIMIT = 19;//?
    public final static int ELEVATOR_BOTTOM_LIMIT = 18;//?

    //crazy arms
    public final static int ARM_A = 0;
    public final static int ARM_B = 7;
    public final static int PUNCH_SOLENOID = 6;
    public final static int INTAKE_SOLENOID = 4;
    public final static int TILT_SOLENOID_A_PORT = 5;
    public final static int TILT_SOLENOID_B_PORT = 3;

    //intake
    public final static int LEFT_INTAKE_PORT = 7;
    public final static int RIGHT_INTAKE_PORT = 6;//?

    //gyro
    public final static int GYRO_PORT_A = 1;
    public final static int GYRO_PORT_B = 0;
    
    //Stingers
    public final static int STINGER_RETRACT_MOTOR = 17;
    public final static int leftStingerMotor = 1;
    public final static int rightStingerMotor = 0;
}
