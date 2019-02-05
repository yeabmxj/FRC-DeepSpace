package frc.Base;

public class Constants{
    //Robot Attributes
    public static final double HEIGHT_TO_CAMERA = 5;
    public static final double HEIGHT_TO_TAPE = 10;
    public static final double CAMERA_ANGLE = 0;

    public static final double DEAD_BAND = .02;
    public static final double INITIAL_THROTTLE = .5;

    public static final double ClimbUpFactor = 10;
    public static final double ClimbForwardFactor = 10;

    //Shuffleboard
    public static boolean RECORDING_IN_PROGRESS = false;

    //Vacuum
    public static final double SPARK_SUCTION_SPEED = .75;

    //ID's
    public static final int FRONT_LEFT_TALON_ID = 1;
    public static final int FRONT_RIGHT_TALON_ID = 2;
    public static final int BACK_LEFT_TALON_ID = 3;
    public static final int BACK_RIGHT_TALON_ID = 4;
    public static final int ARM_TALON_ID = 5;

    public static final int HOME_LIMIT_SWITCH_ID = 0;
    public static final int LEVEL_1_LIMIT_SWITCH_ID = 1;
    public static final int LEVEL_2_LIMIT_SWITCH_ID = 2;
    public static final int LEVEL_3_LIMIT_SWITCH_ID = 3;

    public static final int DISTANCE_TO_WALL_SENSOR = 1;

    //IR Sensor
    public static final int IR_WALL_DISTANCE_VOLTAGE = 5;

    //CSV
    public static final String COMMA = ",";
    public static final String NEWLINE = "\n";
    public static final String FILE_HEADER = "X,Y,Z,GyroAngle";

    public static final int X_VALUE_INDEX_ID = 0;
    public static final int Y_VALUE_INDEX_ID = 1;
    public static final int Z_VALUE_INDEX_ID = 2;
    public static final int GYRO_ANGLE_VALUE_INDEX_ID = 3;
    public static final int THROTTLE_VALUE_INDEX_ID = 4;

}