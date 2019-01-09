package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controls{
    public static Joystick joystick = new Joystick(0);

    public static double getX() { return joystick.getRawAxis(Constants.XAxis);}
    public static double getY() { return joystick.getRawAxis(Constants.YAxis);}
    public static double getZ() { return joystick.getRawAxis(Constants.ZAxis);}

    public static boolean increaseThrottle() { return joystick.getRawButton(Constants.IncreaseThrottleButton);}
    public static boolean decreaseThrottle() { return joystick.getRawButton(Constants.DecreaseThrottleButton);}
}