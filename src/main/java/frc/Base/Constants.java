package frc.Base;

public class Constants{
    //Robot Attributes
    public static final double HIGHTTOCAMERA = 5;
    public static final double HIGHTTOTAPE = 10;
    public static final double CAMERAANGLE = 0;

    public static final double DEADBAND = .02;
    public static final double initialThrottle = .5;

    //Vacuum
    public static final double SparkSuctionSpeed = .75;

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

    public static final int DistanceToWallSensor = 1;

    //IR Sensor
    public static final int IRWallDistanceVoltage = 5;

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