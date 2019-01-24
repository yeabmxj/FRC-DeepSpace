package frc.Base;

public class Constants{
    //Robot Attributes
    public static final double HIGHTTOCAMERA = 5;
    public static final double HIGHTTOTAPE = 10;
    public static final double CAMERAANGLE = 0;

    //Joystick id's for
    public static final int ZAxisT = 2;
    public static final int YAxisT = 1;
    public static final int XAxisT = 0;

    public static final int IncreaseThrottleButtonT = 5;
    public static final int DecreaseThrottleButtonT = 6;
    public static final int ActivateVisionT = 1;
    public static final int StartReadingT = 2;
    public static final int StartWritingT = 3;
    public static final int SetArmLevel1T = 4;
    public static final int SetArmLevel2T = 5;
    public static final int SetArmLevel3T = 6;
    public static final int HomeT = 7;

    //Joystick id's for
    public static final int ZAxisJ = 2;
    public static final int YAxisJ = 1;
    public static final int XAxisJ = 0;
    public static final int ThrottleAxisJ = 3;

    public static final int IncreaseThrottleButtonJ = 5;
    public static final int DecreaseThrottleButtonJ = 6;
    public static final int ActivateVisionJ = 1;
    public static final int StartReadingJ = 2;
    public static final int StartWritingJ = 3;
    public static final int SetArmLevel1J = 4;
    public static final int SetArmLevel2J = 5;
    public static final int SetArmLevel3J = 6;
    public static final int HomeJ = 7;

    public static final double DEADBAND = .02;

    //ID's
    public static final int FrontLeftTalonID = 1;
    public static final int FrontRightTalonID = 2;
    public static final int BackLeftTalonID = 3;
    public static final int BackRightTalonID = 4;
    public static final int ArmTalonID = 5;

    public static final int HomeLimitSwitchID = 0;
    public static final int Level1LimitSwitchID = 1;
    public static final int Level2LimitSwitchID = 2;
    public static final int Level3LimitSwitchID = 3;

    public static final int PotentiometerID = 0;

    public static final int DistanceToWallSensor = 1;

    //Heights
    public static final int BayEntryLevel1 = 0;
    public static final int BayEntryLevel2 = 0;
    public static final int BayEntryLevel3 = 0;

    public static final int HatchLevel1 = 0;
    public static final int HatchLevel2 = 0;
    public static final int HatchLevel3 = 0;

    //Distance
    public static final int DistanceToTape = 5;

    //IR Sensor
    public static final int IRWallDistanceVoltage = 5;

    //Auto
    public static final double AUTOSPEED = .25;

    //CSV
    public static final String COMMA = ",";
    public static final String NEWLINE = "\n";
    public static final String FILEHEADER = "X,Y,Z,GyroAngle";

    public static final int XVALUEINDEXID = 0;
    public static final int YVALUEINDEXID = 1;
    public static final int ZVALUEINDEXID = 2;
    public static final int GYROANGLEVALUEINDEXID = 3;
    public static final int THROTTLEVALUEINDEXID = 4;

}