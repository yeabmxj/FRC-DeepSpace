package frc.Base.Joysticks;

import frc.Base.Controls;

public class Controller_Gamepad_F310 extends Controls {
    public static void DriveJOY() {
        setAxis(1,4,1);
        setThrottleType("");
        setMonoThrottleAxis(3);
    }
    public static void SystemJOY() {
        setArmControls(1,2,3,4);
        setVaccumControls(5);
        setClimberControls(6);
        setLimeLightControls(7);
    }
    public static void OmniJOY() {
        setAxis(1,4,1);
        setMonoThrottleAxis(3);
        setArmControls(1,2,3,4);
        setVaccumControls(5);
        setClimberControls(6);
        setLimeLightControls(7);
    }
}
