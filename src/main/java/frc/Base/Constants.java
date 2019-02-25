package frc.Base;

public class Constants{
    //Robot Attributes
    public static final double HEIGHT_TO_CAMERA = 5;
    public static final double HEIGHT_TO_TAPE = 10;
    public static final double CAMERA_ANGLE = 0;

    public static final double DEAD_BAND = .02;
    public static final double INITIAL_THROTTLE = .5;

    public static final double RISE_CLIMBER_SPEED = .75;
    public static final double RUN_CLIMBER_SPEED = .7;

    //Vacuum
    public static final double SPARK_SUCTION_SPEED = .75;

    //Arm
    public static final double ARM_SPEED_MULTIPLIER = .3;

    public static final double HOME_HEIGHT = -20;
    public static final double LEVEL_1_HEIGHT = -10;
    public static final double LEVEL_2_HEIGHT = 2;
    public static final double LEVEL_3_HEIGHT = 15;

    //Wrist
    public static final int WRIST_X_MOVER = 1;
    public static final int WRIST_Y_MOVER = 2;
    public static final int WRIST_X_LIMIT = 1;
    public static final int WRIST_Y_LIMIT = 2;

    //ID's
    public static final int FRONT_LEFT_TALON_ID = 1;
    public static final int FRONT_RIGHT_TALON_ID = 2;
    public static final int BACK_LEFT_TALON_ID = 3;
    public static final int BACK_RIGHT_TALON_ID = 4;
    public static final int ARM_TALON_ID = 0;

    public static final int LEFT_IR_SENSOR_ID = 1;
    public static final int RIGHT_IR_SENSOR_ID = 2;

    public static final int UP_CLIMBER_VICTOR_ID = 1;
    public static final int FORWARD_CLIMBER_VICTOR_ID = 2;

    public static final int VACUUM_SPARK_ID = 1;
<<<<<<< HEAD
    public static final int SOLENOID_CONTROL_RELAY_ID = 0;
=======
    public static final int SOLONOID_CONTROL_RELAY_ID = 0;
>>>>>>> 5cbcc7a49e6dec5874169351f24881592d694e9b

    //Auto
    public static final double FRONT_TO_SMALL_ELLIPSE_ENCODER = 10;
    public static final double FRONT_TO_BIG_ELLIPSE_ENCODER = 10;
    public static final double VOLTAGE_TO_FEET_FACTOR = 1;
}