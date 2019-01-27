package frc.Base;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Controls{
    public static Joystick driveJoy = new Joystick(0);
    private static Joystick systemJoy = new Joystick(1);

    private static SendableChooser<String> driver = new SendableChooser<>();
    private static SendableChooser<String> subdriver = new SendableChooser<>();

    public static String Driver = "";
    public static String SubDriver = "";

    private static double throt = .5;

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

    public static void init() {
        driver.addOption("Driver", "Eyosias");
        driver.addOption("Driver", "Zach");
        driver.addOption("Driver", "Alex");
        driver.addOption("Driver", "Noah");

        subdriver.addOption("Sub Driver" , "Eyosias");
        subdriver.addOption("Sub Driver" , "Zach");
        subdriver.addOption("Sub Driver" , "Alex");
        subdriver.addOption("Sub Driver" , "Noah");
    }

    public void setDriver() { Driver = driver.getSelected();}
    public String getDriver() { return Driver;}

    public void setSubDriver() { SubDriver = subdriver.getSelected();}
    public String getSubDriver() { return SubDriver;}

    public static void updateDriver() {
        switch (Driver) {
            case "Eyosias":
                xAxis = 1;
                yAxis = 2;
                zAxis = 3;
                tAxis = 4;
                incThrotButton = 5;
                decThrotButton = 6;
                updateSubDriver();
                break;
            case "Zach":
                xAxis = 1;
                yAxis = 2;
                zAxis = 3;
                tAxis = 4;
                incThrotButton = 5;
                decThrotButton = 6;
                updateSubDriver();
                break;
            case "Alex":
                xAxis = 1;
                yAxis = 2;
                zAxis = 3;
                tAxis = 4;
                incThrotButton = 5;
                decThrotButton = 6;
                updateSubDriver();
                break;
            case "Noah":
                xAxis = 1;
                yAxis = 2;
                zAxis = 3;
                tAxis = 4;
                incThrotButton = 5;
                decThrotButton = 6;
                updateSubDriver();
                break;
        }
    }

    private static void updateSubDriver() {
        switch (SubDriver) {
            case "Eyosias":
                visionButton = 1;
                CSVreadButton = 2;
                CSVwriteButton = 3;
                level1Button = 4;
                level2Button = 5;
                level3Button = 6;
                homeButton = 7;
                break;
            case "Zach":
                visionButton = 1;
                CSVreadButton = 2;
                CSVwriteButton = 3;
                level1Button = 4;
                level2Button = 5;
                level3Button = 6;
                homeButton = 7;
                break;
            case "Alex":
                visionButton = 1;
                CSVreadButton = 2;
                CSVwriteButton = 3;
                level1Button = 4;
                level2Button = 5;
                level3Button = 6;
                homeButton = 7;
                break;
            case "Noah":
                visionButton = 1;
                CSVreadButton = 2;
                CSVwriteButton = 3;
                level1Button = 4;
                level2Button = 5;
                level3Button = 6;
                homeButton = 7;
                break;
        }
    }
    public static double getX() { return driveJoy.getRawAxis(xAxis);}
    public static double getY() { return driveJoy.getRawAxis(yAxis);}
    public static double getZ() { return driveJoy.getRawAxis(zAxis);}
    public static double getT() { return driveJoy.getRawAxis(tAxis);}
    public static boolean increaseThrottle() { return driveJoy.getRawButton(incThrotButton);}
    public static boolean decreaseThrottle() { return driveJoy.getRawButton(decThrotButton);}

    public static boolean activateVision() { return systemJoy.getRawButton(visionButton);}
    public static boolean startReading() { return systemJoy.getRawButton(CSVreadButton);}
    public static boolean startWriting() { return systemJoy.getRawButton(CSVwriteButton);}
    public static boolean home() { return systemJoy.getRawButton(homeButton);}
    public static boolean setArmLevel1() { return systemJoy.getRawButton(level1Button);}
    public static boolean setArmLevel2() { return systemJoy.getRawButton(level2Button);}
    public static boolean setArmLevel3() { return systemJoy.getRawButton(level3Button);}
}