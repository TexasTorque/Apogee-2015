package org.texastorque.texastorque2015.constants;

import org.texastorque.torquelib.util.Parameters;
import org.texastorque.torquelib.util.Parameters.Constant;

public class Constants extends Parameters {

    //Elevator
    public final static Constant ElevatorMaxV = new Constant("E_MaxV", 0.0);
    public final static Constant ElevatorMaxA = new Constant("E_MaxA", 0.0);
    public final static Constant ElevatorP = new Constant("E_P", 0.0);
    public final static Constant ElevatorV = new Constant("E_V", 0.0);
    public final static Constant ElevatorffV = new Constant("E_ffV", 0.0);
    public final static Constant ElevatorffA = new Constant("E_ffA", 0.0);
    public final static Constant ElevatorffP = new Constant("E_ffP", 0.0);

    //Drivebase
    public final static Constant DrivebaseMaxV = new Constant("D_MaxV", 0.0);
    public final static Constant DrivebaseMaxA = new Constant("D_MaxA", 0.0);
    public final static Constant DrivebaseLeftP = new Constant("D_LeftP", 0.0);
    public final static Constant DrivebaseLeftV = new Constant("D_LeftV", 0.0);
    public final static Constant DrivebaseLeftffV = new Constant("D_LeftffV", 0.0);
    public final static Constant DrivebaseLeftffA = new Constant("D_LeftffA", 0.0);
    public final static Constant DrivebaseRightP = new Constant("D_RightP", 0.0);
    public final static Constant DrivebaseRightV = new Constant("D_RightV", 0.0);
    public final static Constant DrivebaseRightffV = new Constant("D_RightffV", 0.0);
    public final static Constant DrivebaseRightffA = new Constant("D_RightffA", 0.0);
    public final static Constant DrivebaseTunedVoltage = new Constant("D_TunedVoltage", 12.5);

    static {
        load();
    }
}
