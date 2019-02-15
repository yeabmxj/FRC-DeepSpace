package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class Controls{
    static Joystick driveJoy = new Joystick(0);
    static Joystick systemJoy = new Joystick(1);

    private static int xAxis;
    private static int yAxis;
    private static int zAxis;
    public static int tAxis;
    private static int axisDirection;
    private static int incThrotButton;
    private static int incThrotAxis;
    private static int decThrotButton;
    private static int decThrotAxis;
    private static int monoThrotAxis;

    private static double z;

    private static int visionButton;
    public static int CSVreadButton;
    public static int CSVwriteButton;
    private static int level1Button;
    private static int level2Button;
    private static int level3Button;
    private static int homeButton;
    private static int succBTN;
    private static int climbBTN;

    private static double throttle = Constants.INITIAL_THROTTLE;

    private static String ThrottleType = "";

    protected static void setThrottleType(String type) {ThrottleType = type;}
    private static String getThrottleType() { return ThrottleType;}

    protected static void setAxis(int x, int y, int z, int direction) {
        xAxis = x;
        yAxis = y;
        zAxis = z;
        axisDirection = direction;
    }

    public static void setMonoThrottleAxis(int tAxis) { monoThrotAxis = tAxis; }
    public static void setBiThrottleAxis(int tAxisUp, int tAxisDown) {
        incThrotAxis = tAxisUp;
        decThrotAxis = tAxisDown;
    }
    public static void setThrottleButtons(int tBTNUp, int tBTNDown) {
        incThrotButton = tBTNUp;
        decThrotButton = tBTNDown;
    }

    public static void setArmControls(int home, int l1, int l2, int l3) {
        homeButton = home;
        level1Button = l1;
        level2Button = l2;
        level3Button = l3;
    }
    public static void setVaccumControls(int succ) { succBTN = succ; }
    public static void setClimberControls(int climb) { Controls.climbBTN = climb; }
    public static void setLimeLightControls(int vision) { Controls.visionButton = vision; }

    public static double getX() { return axisDirection * driveJoy.getRawAxis(xAxis);}
    public static double getY() { return driveJoy.getRawAxis(yAxis);}
    public static double getZ() {
        if (!Robot.dLibrary.getDriveTrainType().equals("Mecanum")) {z = 0; }
        else { z = driveJoy.getRawAxis(zAxis);}
        return z;
    }
    public static double getT() {
        if(getThrottleType().equals("Button Based")){
            if (Controls.increaseThrottleBTN()) { throttle += .002;}
            if (Controls.decreaseThrottleBTN()) { throttle -= .002;}
            if (throttle > 1) { throttle = 1;}
            if (throttle < 0) { throttle = 0;}
        }
        else if(getThrottleType().equals("Two Axis Based")){
            throttle += .005 * (increaseThrottleAxis() - decreaseThrottleAxis());
            if (throttle > 1) { throttle = 1;}
            if (throttle < 0) { throttle = 0;}
        }
        else if (getThrottleType().equals("One Axis Based")) {
            throttle = (monoThrottleAxis() + 1) / 2;
        }
        return throttle;
    }
    public static boolean increaseThrottleBTN() { return driveJoy.getRawButton(incThrotButton);}
    public static double increaseThrottleAxis() { return driveJoy.getRawAxis(incThrotAxis);}
    public static boolean decreaseThrottleBTN() { return driveJoy.getRawButton(decThrotButton);}
    public static double decreaseThrottleAxis() { return driveJoy.getRawAxis(decThrotAxis);}
    public static double monoThrottleAxis() { return driveJoy.getRawAxis(monoThrotAxis);}

    public static boolean Vision() { return systemJoy.getRawButton(visionButton);}
    public static boolean home() { return systemJoy.getRawButton(homeButton);}
    public static boolean setArmLevel1() { return systemJoy.getRawButton(level1Button);}
    public static boolean setArmLevel2() { return systemJoy.getRawButton(level2Button);}
    public static boolean setArmLevel3() { return systemJoy.getRawButton(level3Button);}
    public static boolean suction() { return systemJoy.getRawButton(succBTN);}
    public static boolean climb() { return systemJoy.getRawButton(climbBTN);}
}