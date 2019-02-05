package frc.Base;
public class Operator {
    /*
    Controller (Gamepad F310)
    T.16000M
    Controller (Xbox One For Windows)
    Logitech Extreme 3D
    Logitech RumblePad 2 USB
     */
    private static String driver = "";
    private static String subdriver = "";
    private static String ThrottleType = "";
    private static void setThrottleType(String type) {ThrottleType = type;}
    public static String getThrottleType() { return ThrottleType;}
    private static void setAxis(int x, int y, int z) {
        Controls.xAxis = x;
        Controls.yAxis = y;
        Controls.zAxis = z;
    }
    private static void setThrottleControls(int incBTN, int decBTN, int incAxis, int decAxis, int monoThrotAxis) {
        if (getThrottleType().equals("ButtonBased")){
            Controls.incThrotButton = incBTN;
            Controls.decThrotButton = decBTN;
        }
        else if (getThrottleType().equals("TwoAxisBased")){
            Controls.incThrotAxis = incAxis;
            Controls.decThrotAxis = decAxis;
        }
        else if (getThrottleType().equals("OneAxisBased")){
            Controls.monoThrotAxis = monoThrotAxis;
        }
    }
    private static void setArmControls(int home, int l1, int l2, int l3) {
        Controls.homeButton = home;
        Controls.level1Button = l1;
        Controls.level2Button = l2;
        Controls.level3Button = l3;
    }
    private static void setVaccumControls(int succ) { Controls.succBTN = succ; }
    private static void setClimberControls(int climb) { Controls.climbBTN = climb; }
    private static void setLimeLightControls(int vision) { Controls.visionButton = vision; }

    public static String getDriver() {
        return driver =
                Controls.driveJoy.getName().equals("Controller (Xbox One For Windows)") ? "Eyosias" :
                Controls.driveJoy.getName().equals("a") ? "Alex" :
                Controls.driveJoy.getName().equals("j") ? "Jacob" :
                Controls.driveJoy.getName().equals("z") ? "Zach" :
                Controls.driveJoy.getName().equals("n") ? "Noah" : "Default";
    }
    public static String getSubDriver() {
        return subdriver =
                Controls.systemJoy.getName().equals("e") ? "Eyosias" :
                Controls.systemJoy.getName().equals("a") ? "Alex" :
                Controls.systemJoy.getName().equals("j") ? "Jacob" :
                Controls.systemJoy.getName().equals("z") ? "Zach" :
                Controls.systemJoy.getName().equals("n") ? "Noah" : "Default";
    }
    private static void updateDriveController() {
        switch(getDriver()) {
            case "Eyosias":
                setAxis(1,4,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Alex":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Jacob":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Zach":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Noah":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
        }
    }
    private static void updateSubDriveController() {
        switch(getSubDriver()) {
            case "Eyosias":
                setAxis(1,4,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Alex":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Jacob":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Zach":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "Noah":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
        }
    }
    private static void updateNoDriver() {
        switch (getSubDriver()) {
            case "Eyosias":
                setAxis(1,4,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Alex":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Jacob":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Zach":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Noah":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
        }
    }
    private static void updateNoSubDriver() {
        switch (getDriver()) {
            case "Eyosias":
                setAxis(1,4,3);
                setThrottleType("ButtonBased");
                setThrottleControls(1,2,0,0,0);
                setArmControls(3,4,5,6);
                setVaccumControls(7);
                setClimberControls(8);
                setLimeLightControls(9);
            case "Alex":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Jacob":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Zach":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "Noah":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
        }
    }
    private static void updateControllers() {
        updateDriveController();
        updateSubDriveController();
    }
    public static void update() {
        if (Controls.driveJoy == null) { updateNoDriver();}
        else if (Controls.systemJoy == null) { updateNoSubDriver();}
        else { updateControllers();}
    }
}