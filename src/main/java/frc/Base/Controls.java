package frc.Base;

import edu.wpi.first.wpilibj.Joystick;

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

    private static double throttle = Constants.initialThrottle;

    public static double getX() { return driveJoy.getRawAxis(xAxis);}
    public static double getY() { return driveJoy.getRawAxis(yAxis);}
    public static double getZ() { return driveJoy.getRawAxis(zAxis);}
    public static double getT() {
        if(Operator.getThrottleType().equals("ButtonBased")){
            if (Controls.increaseThrottleBTN()) { throttle += .002;}
            if (Controls.decreaseThrottleBTN()) { throttle -= .002;}
            if (throttle > 1) { throttle = 1;}
            if (throttle < 0) { throttle = 0;}
        }
        else if(Operator.getThrottleType().equals("TwoAxisBased")){
            throttle += .005 * (increaseThrottleAxis() - decreaseThrottleAxis());
            if (throttle > 1) { throttle = 1;}
            if (throttle < 0) { throttle = 0;}
        }
        else if (Operator.getThrottleType().equals("OneAxisBased")) {
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
}