package frc.Base;

public class Operator {
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
    private static void setVaccumControls(int succ) {
        Controls.succBTN = succ;
    }
    private static void setClimberControls(int climb) {
        Controls.climbBTN = climb;
    }
    private static void setLimeLightControls(int vision) {
        Controls.visionButton = vision;
    }
    private static void updateDriveController() {
        switch(Controls.driveJoy.getName()) {
            case "e":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "a":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "j":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "z":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
            case "n":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
        }
    }
    private static void updateSubDriveController() {
        switch(Controls.systemJoy.getName()) {
            case "e":
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "a":
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "j":
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "z":
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "n":
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
        }
    }
    public static void updateNoDriver() {
        switch (Controls.systemJoy.getName()) {
            case "e":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "a":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "j":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "z":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "n":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
        }
    }
    public static void updateNoSubDriver() {
        switch (Controls.driveJoy.getName()) {
            case "e":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "a":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "j":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "z":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
            case "n":
                setAxis(1,2,3);
                setThrottleType("");
                setThrottleControls(1,2,0,0,0);
                setArmControls(1,2,3,4);
                setVaccumControls(1);
                setClimberControls(1);
                setLimeLightControls(1);
        }
    }
    public static void updateControllers() {
        updateDriveController();
        updateSubDriveController();
    }
    public static void update() {
        if (Controls.driveJoy == null) { updateNoDriver();}
        else if (Controls.systemJoy == null) { updateNoSubDriver();}
        else { updateControllers();}
    }
}
