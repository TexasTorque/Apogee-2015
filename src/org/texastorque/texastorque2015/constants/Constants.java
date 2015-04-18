package org.texastorque.texastorque2015.constants;

import org.texastorque.torquelib.util.Parameters;
import org.texastorque.torquelib.util.Parameters.Constant;

public class Constants extends Parameters {

    //Auto
    public final static Constant distanceDoneRange = new Constant("A_DistanceDoneRange", 0.25);
    public final static Constant turnDoneRange = new Constant("A_TurnDoneRange", 2.5);
    
    //Yellow Tote Auto
    public final static Constant autoMode = new Constant("Auto", 0);
    public final static Constant FirstLeftTurnDegrees = new Constant("A_FirstTurnLeftDegrees", 0.0);
    public final static Constant DriveWithFirstToteFeet = new Constant("A_DriveWithFirstToteFeet", 6.0);
    public final static Constant TurnToSecondToteDegrees = new Constant("A_TurnToSecondToteDegrees", 50.0);
    public final static Constant DriveToSecondToteFeet = new Constant("A_DriveToSecondToteFeet", 0.0);
    public final static Constant DriveWithSecondToteFeet = new Constant("A_DriveWithSecondToteFeet", 0.0);
    public final static Constant TurnToThirdToteDegrees = new Constant("A_TurnToThirdToteDegrees", 0.0);
    public final static Constant DriveToThirdToteFeet = new Constant("A_DriveToThirdToteFeet", 0.0);
    public final static Constant TurnWithThirdToteDegrees = new Constant("A_TurnWithThirdToteDegrees", 0.0);
    public final static Constant DriveTotesToZoneFeet = new Constant("A_DriveTotesToZoneFeet", 0.0);
    public final static Constant BackAwayFromTotesFeet = new Constant("A_BackAwayFromTotesFeet", 0.0);

    //Drive Auto
    public final static Constant DriveForwardFeet = new Constant("A_DriveForwardFeet", 0.0);
    
    //Take Something Auto
    public final static Constant TakeSomethingFeet = new Constant("A_TakeSomethingFeet", 0.0);
    public final static Constant turnRightDegrees = new Constant("A_TurnRightDegrees", 90.0);
    
    //Elevator
    public final static Constant ElevatorVDoneRange = new Constant("E_VDoneRange", 0.0);
    public final static Constant ElevatorPDoneRange = new Constant("E_PDoneRange", 0.0);

    public final static Constant ElevatorP0Tote = new Constant("E_P_0Tote", 0.0);
    public final static Constant ElevatorV0Tote = new Constant("E_V_0Tote", 0.0);
    public final static Constant ElevatorffV0Tote = new Constant("E_ffV_0Tote", 0.0);
    public final static Constant ElevatorffA0Tote = new Constant("E_ffA_0Tote", 0.0);
    public final static Constant ElevatorffP0Tote = new Constant("E_ffP_0Tote", 0.0);
    public final static Constant ElevatorMaxV0Tote = new Constant("E_MaxV_0Tote", 0.0);
    public final static Constant ElevatorMaxA0Tote = new Constant("E_MaxA_0Tote", 0.0);
    
    public final static Constant Carry4ToteLevel = new Constant("E_Carry4ToteLevel", 0.0);

    //Regular stacking levels
    public final static Constant FloorElevatorLevel1 = new Constant("E_FloorL1", 100.0);
    public final static Constant FloorElevatorLevel2 = new Constant("E_FloorL2", 200.0);
    public final static Constant FloorElevatorLevel3 = new Constant("E_FloorL3", 300.0);
    public final static Constant FloorElevatorLevel4 = new Constant("E_FloorL4", 400.0);
    public final static Constant FloorElevatorLevel5 = new Constant("E_FloorL5", 500.0);
    public final static Constant FloorElevatorLevel6 = new Constant("E_FloorL6", 600.0);
    public final static Constant PlaceLevel1 = new Constant("E_PlaceLevel1", 50.0);
    public final static Constant PlaceLevel2 = new Constant("E_PlaceLevel2", 50.0);
    public final static Constant PlaceLevel3 = new Constant("E_PlaceLevel3", 50.0);
    public final static Constant PlaceLevel4 = new Constant("E_PlaceLevel4", 50.0);
    public final static Constant PlaceLevel5 = new Constant("E_PlaceLevel5", 50.0);
    public final static Constant PlaceLevel6 = new Constant("E_PlaceLevel6", 50.0);

    //Step
    public final static Constant StepElevatorLevel1 = new Constant("E_StepL1", 150.0);
    public final static Constant StepElevatorLevel2 = new Constant("E_StepL2", 250.0);
    public final static Constant StepElevatorLevel3 = new Constant("E_StepL3", 350.0);
    public final static Constant StepElevatorLevel4 = new Constant("E_StepL4", 450.0);
    public final static Constant StepPlaceLevel1 = new Constant("E_StepPlaceLevel1", 125.0);
    public final static Constant StepPlaceLevel2 = new Constant("E_StepPlaceLevel2", 225.0);
    public final static Constant StepPlaceLevel3 = new Constant("E_StepPlaceLevel3", 325.0);
    public final static Constant StepPlaceLevel4 = new Constant("E_StepPlaceLevel4", 425.0);

    //Autostack
    public final static Constant autoStackLevel = new Constant("E_AutoStackLevel", 0.0);
    public final static Constant autoStackArmOpenLevel = new Constant("E_AutoStackArmOpenLevel", 0.0);

    //Recycling can
    public final static Constant RCElevatorLevel1 = new Constant("E_RCL1", 0.0);
    public final static Constant RCElevatorLevel2 = new Constant("E_RCL2", 0.0);
    public final static Constant RCElevatorLevel3 = new Constant("E_RCL3", 0.0);
    public final static Constant RCElevatorLevel4 = new Constant("E_RCL4", 0.0);
    public final static Constant RCElevatorLevel5 = new Constant("E_RCL5", 0.0);
    public final static Constant RCElevatorLevel6 = new Constant("E_RCL6", 0.0);

    //Drivebase linear
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

    //Drivebase angular
    public final static Constant DrivebaseTurnP = new Constant("D_TurnP", 0.0);
    public final static Constant DrivebaseTurnI = new Constant("D_TurnI", 0.0);
    public final static Constant DrivebaseTurnD = new Constant("D_TurnP", 0.0);
    public final static Constant DrivebaseTurnDoneRange = new Constant("D_TurnDoneRange", 0.0);

    //Manipulator
    public final static Constant TotePullBAckTime = new Constant("I_TotePullBAckTime", 1.0);
    
    //Stingers
    public final static Constant leftStingerP = new Constant("S_LeftP", 0.0);
    public final static Constant leftStingerI = new Constant("S_LeftI", 0.0);
    public final static Constant leftStingerD = new Constant("S_LeftD", 0.0);
    public final static Constant rightStingerP = new Constant("S_RightP", 0.0);
    public final static Constant rightStingerI = new Constant("S_RightI", 0.0);
    public final static Constant rightStingerD = new Constant("S_RightD", 0.0);
    
    public final static Constant LEFT_STINGER_VOLTAGE_UP = new Constant("S_LeftUpVoltage", 0);
    public final static Constant LEFT_STINGER_VOLTAGE_DOWN = new Constant("S_LeftDownVoltage", 0);
    public final static Constant RIGHT_STINGER_VOLTAGE_UP = new Constant("S_RightUpVoltage", 0);
    public final static Constant RIGHT_STINGER_VOLTAGE_DOWN = new Constant("S_RightDownVoltage", 0);
    public final static Constant transportLevel = new Constant("E_TransportLevel", 8.5);

    static {
        load();
    }
}
