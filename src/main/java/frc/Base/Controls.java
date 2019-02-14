package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class Controls{
    public static Joystick driveJoy = new Joystick(0);
    public static Joystick systemJoy = new Joystick(1);

    public static int xAxis;
    public static int yAxis;
    public static int zAxis;
    public static int tAxis;
    public static int incThrotButton;
    public static int incThrotAxis;
    public static int decThrotButton;
    public static int decThrotAxis;
    public static int monoThrotAxis;

    public static int visionButton;
    public static int CSVreadButton;
    public static int CSVwriteButton;
    public static int level1Button;
    public static int level2Button;
    public static int level3Button;
    public static int homeButton;
    public static int succBTN;
    public static int climbBTN;

    static double z;

    private static double throttle = Constants.INITIAL_THROTTLE;


    public static String ThrottleType = "";

    public static void setThrottleType(String type) {ThrottleType = type;}
    public static String getThrottleType() { return ThrottleType;}

    public static void setAxis(int x, int y, int z) {
        Controls.xAxis = x;
        Controls.yAxis = y;
        Controls.zAxis = z;
    }

    public static void setMonoThrottleAxis(int tAxis) { Controls.monoThrotAxis = tAxis; }
    public static void setBiThrottleAxis(int tAxisUp, int tAxisDown) {
        Controls.incThrotAxis = tAxisUp;
        Controls.decThrotAxis = tAxisDown;
    }
    public static void setThrottleButtons(int tBTNUp, int tBTNDown) {
        Controls.incThrotButton = tBTNUp;
        Controls.decThrotButton = tBTNDown;
    }

    public static void setArmControls(int home, int l1, int l2, int l3) {
        Controls.homeButton = home;
        Controls.level1Button = l1;
        Controls.level2Button = l2;
        Controls.level3Button = l3;
    }
    public static void setVaccumControls(int succ) { Controls.succBTN = succ; }
    public static void setClimberControls(int climb) { Controls.climbBTN = climb; }
    public static void setLimeLightControls(int vision) { Controls.visionButton = vision; }

    public static double getX() { return -driveJoy.getRawAxis(xAxis);}
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
    public static boolean CSVREAD() { return systemJoy.getRawButton(CSVreadButton);}
    public static boolean CSVWRITE() { return systemJoy.getRawButton(CSVwriteButton);}
    public static boolean home() { return systemJoy.getRawButton(homeButton);}
    public static boolean setArmLevel1() { return systemJoy.getRawButton(level1Button);}
    public static boolean setArmLevel2() { return systemJoy.getRawButton(level2Button);}
    public static boolean setArmLevel3() { return systemJoy.getRawButton(level3Button);}
    public static boolean suction() { return systemJoy.getRawButton(succBTN);}
    public static boolean climb() { return systemJoy.getRawButton(climbBTN);}
}