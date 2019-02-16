package frc.Base;

public class Constants{
    //Robot Attributes
    public static final double HEIGHT_TO_CAMERA = 5;
    public static final double HEIGHT_TO_TAPE = 10;
    public static final double CAMERA_ANGLE = 0;

    public static final double DEAD_BAND = .02;
    public static final double INITIAL_THROTTLE = .5;

    public static final double CLIMB_UP_HEIGHT = 10;
    public static final double CLIMB_FORWARD_HEIGHT = 10;

    //Vacuum
    public static final double SPARK_SUCTION_SPEED = .75;

    public static final double ARM_SPEED_MULTIPLIER = .7;

    public static final double HOME_HEIGHT = -50;
    public static final double LEVEL_1_HEIGHT = 200;
    public static final double LEVEL_2_HEIGHT = 300;
    public static final double LEVEL_3_HEIGHT = 400;

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
    public static final int ARM_TALON_ID = 5;

    public static final int LEFT_IR_SENSOR_ID = 1;
    public static final int RIGHT_IR_SENSOR_ID = 2;

    public static final int UP_CLIMBER_VICTOR_ID = 1;
    public static final int FORWARD_CLIMBER_VICTOR_ID = 2;

    public static final int VACUUM_SPARK_ID = 1;

    //Tolerances
    public static final int FORWARD_TOLERANCE = 1;
    public static final int TURN_TOLERANCE = 1;
    public static final int ACTUATION_TOLERANCE = 1;
    public static final int CLIMB_TOLERANCE = 1;

}