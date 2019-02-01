package frc.Base;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Robot;

import java.util.HashMap;
import java.util.Map;

public class Controls{
    public static Joystick driveJoy = new Joystick(0);
    private static Joystick systemJoy = new Joystick(1);

    private static double throt = .5;

    static String driver;
    static String subdriver;

    public static final int Input = 0;
    private static final int IncreaseThrottle = 1;
    private static final int DecreaseThrottle = 2;

    private static int Tstate = 0;
    public static void setTState(int s) { Tstate = s;}

    private static int xAxis;
    private static int yAxis;
    private static int zAxis;
    private static int tAxis;
    private static int incThrotButton;
    private static int decThrotButton;

    private static int visionButton;
    private static int CSVreadButton;
    private static int CSVwriteButton;
    private static int level1Button;
    private static int level2Button;
    private static int level3Button;
    private static int homeButton;

    static NetworkTableEntry d;
    static NetworkTableEntry sd;

    Map<String, Object> options = new HashMap<String, Object>();

    public Controls() {
        options.put("Eyosias", 0);
        options.put("Noah", 1);
        options.put("Alex", 2);
        options.put("Zach", 3);
        options.put("Jacob", 4);

        d = Robot.tab.add("None", 0)
                .withWidget(BuiltInWidgets.kTextView)
                .withProperties(options)
                .getEntry();
        sd = Robot.tab.add("None", 0)
                .withWidget(BuiltInWidgets.kTextView)
                .withProperties(options)
                .getEntry();

        driver = d.getString("None");
        subdriver = sd.getString("None");
    }

    public static void setDriveJoyIDS(int XID, int YID, int ZID, int TID, int ITID, int DTID){
        xAxis = XID;
        yAxis = YID;
        zAxis = ZID;
        tAxis = TID;
        incThrotButton = ITID;
        decThrotButton = DTID;
    }
    public static void setSubDriverJoyIDS(int VBTN, int RBTN, int WBTN, int L1BTN, int L2BTN, int L3BTN, int HBTN){
        visionButton = VBTN;
        CSVreadButton = RBTN;
        CSVwriteButton = WBTN;
        level1Button = L1BTN;
        level2Button = L2BTN;
        level3Button = L3BTN;
        homeButton = HBTN;
    }

    public static void updateDriver() {
        switch (driver) {
            case "Eyosias":
                setDriveJoyIDS(1,2,3,4,5,6);
                updateSubDriver();
                break;
            case "Zach":
                setDriveJoyIDS(1,2,3,4,5,6);
                updateSubDriver();
                break;
            case "Alex":
                setDriveJoyIDS(1,2,3,4,5,6);
                updateSubDriver();
                break;
            case "Noah":
                setDriveJoyIDS(1,2,3,4,5,6);
                updateSubDriver();
                break;
        }
    }

    private static void updateSubDriver() {
        switch (subdriver) {
            case "Eyosias":
                setSubDriverJoyIDS(1,2,3,4,5,6,7);
                break;
            case "Zach":
                setSubDriverJoyIDS(1,2,3,4,5,6,7);
                break;
            case "Alex":
                setSubDriverJoyIDS(1,2,3,4,5,6,7);
                break;
            case "Noah":
                setSubDriverJoyIDS(1,2,3,4,5,6,7);
                break;
        }
    }
    public static double getX() { return driveJoy.getRawAxis(xAxis);}
    public static double getY() { return driveJoy.getRawAxis(yAxis);}
    public static double getZ() { return driveJoy.getRawAxis(zAxis);}
    public static double getT() { return driveJoy.getRawAxis(tAxis);}
    public static boolean increaseThrottle() { return driveJoy.getRawButton(incThrotButton);}
    public static boolean decreaseThrottle() { return driveJoy.getRawButton(decThrotButton);}

    public static boolean Vision() { return systemJoy.getRawButton(visionButton);}
    public static boolean CSVREAD() { return systemJoy.getRawButton(CSVreadButton);}
    public static boolean CSVWRITE() { return systemJoy.getRawButton(CSVwriteButton);}
    public static boolean home() { return systemJoy.getRawButton(homeButton);}
    public static boolean setArmLevel1() { return systemJoy.getRawButton(level1Button);}
    public static boolean setArmLevel2() { return systemJoy.getRawButton(level2Button);}
    public static boolean setArmLevel3() { return systemJoy.getRawButton(level3Button);}
}