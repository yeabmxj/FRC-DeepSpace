package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class Controls{

    private static int mainPort;
    private static int secondaryPort;
    private static int i;

    static Joystick mainJoystick = new Joystick(mainPort);
    static Joystick secondaryJoystick = new Joystick(secondaryPort);

    //Main Joystick
    public static int xAxis;
    public static int yAxis;
    public static int zAxis;
    public static int axisDirection;
    public static int increaseThrottleButton;
    public static int increaseThrottleAxis;
    public static int decreaseThrottleButton;
    public static int decreaseThrottleAxis;
    public static int oneAxisThrottleAxis;

    //Secondary Joystick
    public static int autonomousButton;
    public static int levelUpButton;
    public static int levelDownButton;
    public static int suctionButton;
    public static int climbButton;
    public static int solenoidButton;

    private static double throttle = Constants.INITIAL_THROTTLE;

    protected static String ThrottleType = "";

    public static void updatePorts() {
        i = 0;
        if (new Joystick(i).getButtonCount() == 0) {
            i+=1;
        }
        else if ((new Joystick(i).getButtonCount() != 0) && !(new Joystick(i).getName().equals("MAYFLASH"))) {
            mainPort = i;
            i = 0;
            if (new Joystick(i + mainPort).getButtonCount() == 0) {
                i+=1;
            }
            else if ((new Joystick(i + mainPort).getButtonCount() != 0)) {
                secondaryPort = i;
            }
        }
        else if ((new Joystick(i).getButtonCount() != 0) && (new Joystick(i).getName().equals("MAYFLASH"))) {
            mainPort = i;
            i = 0;
            if (new Joystick(i + mainPort + 4).getButtonCount() == 0) {
                i+=1;
            }
            else if (new Joystick(i + mainPort + 4).getButtonCount() != 0) {
                secondaryPort = i;
            }
        }
    }

    public static double getAxis(int AxisID) {
        double axisValue = Operator.getControllerAvailability().equals("Drive Joystick Not Available") ?
                secondaryJoystick.getRawAxis(AxisID) : mainJoystick.getRawAxis(AxisID);
        return axisDirection * axisValue;
    }

    public static boolean getButton(int buttonID) {
        /*
        * T = Throttle
        * NT = Not Throttle
        * */
        boolean t = buttonID == increaseThrottleButton || buttonID == decreaseThrottleButton;
        return
				Operator.getControllerAvailability().equals("Drive Joystick Not Available") ? secondaryJoystick.getRawButton(buttonID) :
				Operator.getControllerAvailability().equals("System Joystick Not Available") ? mainJoystick.getRawButton(buttonID) :
				t ? mainJoystick.getRawButton(buttonID) : secondaryJoystick.getRawButton(buttonID);
    }
    public static double getPOV() {
    	return
				Operator.getControllerAvailability().equals("Drive Joystick Not Available") ?
						secondaryJoystick.getPOV() : mainJoystick.getPOV();
	}
    public static double getT() {
        switch (ThrottleType) {
            case "Button Based":
                throttle =
                        getButton(increaseThrottleButton) ? throttle +=.002 :
                        getButton(increaseThrottleButton) ? throttle -=.002 :
                        throttle > 1 ? throttle = 1 :
                        throttle < 0 ? 0 : throttle;
                break;
            case "One Axis Based":
                throttle =
                        throttle > 1 ? 1 :
                        throttle < 0 ? 0 :
                        throttle < 1 && throttle > 0 ?
                                throttle += .005 * (getAxis(increaseThrottleAxis) - getAxis(decreaseThrottleAxis)) : throttle;
                break;
            case "Two Axis Based":
                throttle = (getAxis(oneAxisThrottleAxis) + 1) / 2;
                break;
        }
        return throttle;
    }
}