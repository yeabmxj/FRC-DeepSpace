package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class Controls{
    static Joystick driveJoy = new Joystick(0);
    static Joystick systemJoy = new Joystick(1);

    protected static int xAxis;
    protected static int yAxis;
    protected static int zAxis;
    protected static int axisDirection;
    protected static int increaseThrottleButton;
    protected static int increaseThrottleAxis;
    protected static int decreaseThrottleButton;
    protected static int decreaseThrottleAxis;
    protected static int oneAxisThrottleAxis;

    //Subsystem Joystick
    protected static int autonomousButton;
    protected static int level1Button;
    protected static int level2Button;
    protected static int level3Button;
    protected static int homeButton;
    protected static int suctionButton;
    protected static int climbButton;

    private static double throttle = Constants.INITIAL_THROTTLE;

    public static String ThrottleType = "";

    public static double getX() { return axisDirection * driveJoy.getRawAxis(xAxis);}
    public static double getY() { return driveJoy.getRawAxis(yAxis);}
    public static double getZ() {
        return Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? driveJoy.getRawAxis(zAxis) : 0;
    }
    public static double getT() {
        switch (ThrottleType) {
            case "Button Based":
                throttle =
                        Controls.increaseThrottleBTN() ? throttle +=.002 :
                        Controls.decreaseThrottleBTN() ? throttle -=.002 :
                        throttle > 1 ? throttle = 1 :
                        throttle < 0 ? 0 : throttle;
                break;
            case "One Axis Based":
                throttle =
                        throttle > 1 ? 1 :
                        throttle < 0 ? 0 :
                        throttle < 1 && throttle > 0 ?
                                throttle += .005 * (increaseThrottleAxis() - decreaseThrottleAxis()) : throttle;
                break;
            case "Two Axis Based":
                throttle = (monoThrottleAxis() + 1) / 2;
                break;
        }
        return throttle;
    }
    public static boolean increaseThrottleBTN() { return driveJoy.getRawButton(increaseThrottleButton);}
    public static double increaseThrottleAxis() { return driveJoy.getRawAxis(increaseThrottleAxis);}
    public static boolean decreaseThrottleBTN() { return driveJoy.getRawButton(decreaseThrottleButton);}
    public static double decreaseThrottleAxis() { return driveJoy.getRawAxis(decreaseThrottleAxis);}
    public static double monoThrottleAxis() { return driveJoy.getRawAxis(oneAxisThrottleAxis);}

    public static boolean Autonomous() { return systemJoy.getRawButton(autonomousButton);}
    public static boolean home() { return systemJoy.getRawButton(homeButton);}
    public static boolean setArmLevel1() { return systemJoy.getRawButton(level1Button);}
    public static boolean setArmLevel2() { return systemJoy.getRawButton(level2Button);}
    public static boolean setArmLevel3() { return systemJoy.getRawButton(level3Button);}
    public static boolean suction() { return systemJoy.getRawButton(suctionButton);}
    public static boolean climb() { return systemJoy.getRawButton(climbButton);}
}