package frc.robot;

public class Constants{
    //Robot Attributes
    public static final double HIGHTTOCAMERA = 5;
    public static final double HIGHTTOTAPE = 10;
    public static final double CAMERAANGLE = 0;

    //Joystick
    public static final int ZAxis = 1;
    public static final int YAxis = 4;
    public static final int XAxis = 0;

    public static final int IncreaseThrottleButton = 5;
    public static final int DecreaseThrottleButton = 6;
    public static final int ActivateVision = 1;
    public static final int StartReading = 2;
    public static final int StartWriting = 3;

    public static final double DEADBAND = .02;

    //ID's
    public static final int FrontLeftTalonID = 1;
    public static final int FrontRightTalonID = 2;
    public static final int BackLeftTalonID = 3;
    public static final int BackRightTalonID = 4;

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
}