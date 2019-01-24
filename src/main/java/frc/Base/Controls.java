package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public class Controls{

    private static double x;
    private static double y;
    private static double z;
    private static double throttle;

    private static boolean Activatevision;
    private static boolean CSVreading;
    private static boolean CSVwriting;
    private static boolean SetArmLevel1;
    private static boolean SetArmLevel2;
    private static boolean SetArmLevel3;
    private static boolean Home;

    public static Joystick joystick = new Joystick(0);

    public static double[] getJoyValues() {
        if (joystick.getName().equals("")) {
            x = joystick.getRawAxis(Constants.XAxisT);
            y = joystick.getRawAxis(Constants.YAxisT);
            z = joystick.getRawAxis(Constants.ZAxisT);
            throttle = Robot.driveTrain.Throttle(joystick.getRawAxis(3), joystick.getRawAxis(2));
        }
        else if (joystick.getName().equals("")) {
            x = joystick.getRawAxis(Constants.XAxisT);
            y = joystick.getRawAxis(Constants.YAxisT);
            z = joystick.getRawAxis(Constants.ZAxisT);
            throttle = joystick.getRawAxis(Constants.ThrottleAxisJ);
        }
        return new double[]{x,y,z,throttle};
    }

    public static boolean[] getJoyButtons() {
        if (joystick.getName().equals("")) {
            Activatevision = joystick.getRawButton(Constants.ActivateVisionT);
            CSVreading = joystick.getRawButton(Constants.StartReadingT);
            CSVwriting = joystick.getRawButton(Constants.StartWritingT);
            SetArmLevel1 = joystick.getRawButton(Constants.SetArmLevel1T);
            SetArmLevel2 = joystick.getRawButton(Constants.SetArmLevel1T);
            SetArmLevel3 = joystick.getRawButton(Constants.SetArmLevel1T);
            Home = joystick.getRawButton(Constants.HomeT);
        }
        else if (joystick.getName().equals("")) {
            Activatevision = joystick.getRawButton(Constants.ActivateVisionJ);
            CSVreading = joystick.getRawButton(Constants.StartReadingJ);
            CSVwriting = joystick.getRawButton(Constants.StartWritingJ);
            SetArmLevel1 = joystick.getRawButton(Constants.SetArmLevel1J);
            SetArmLevel2 = joystick.getRawButton(Constants.SetArmLevel1J);
            SetArmLevel3 = joystick.getRawButton(Constants.SetArmLevel1J);
            Home = joystick.getRawButton(Constants.HomeJ);
        }
        return new boolean[]{Activatevision, CSVreading, CSVwriting, SetArmLevel1, SetArmLevel2, SetArmLevel3, Home};
    }

    public static double getX() { return getJoyValues()[0]; }
    public static double getY() { return getJoyValues()[1];}
    public static double getZ() { return getJoyValues()[2];}
    public static double getThrottle() { return getJoyValues()[3];}

    public static boolean activateVision() { return getJoyButtons()[0];}
    public static boolean startReading() { return getJoyButtons()[1];}
    public static boolean startWriting() { return getJoyButtons()[2];}
    public static boolean setArmLevel1() { return getJoyButtons()[3];}
    public static boolean setArmLevel2() { return getJoyButtons()[4];}
    public static boolean setArmLevel3() { return getJoyButtons()[5];}
    public static boolean home() { return getJoyButtons()[6];}
}