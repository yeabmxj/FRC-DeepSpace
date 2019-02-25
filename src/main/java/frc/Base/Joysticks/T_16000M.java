package frc.Base.Joysticks;

import frc.Base.Controls;

public class T_16000M extends Controls {
    public static void DriveJOY() {
        xAxis = 0;
        yAxis = 1;
        zAxis = 1;
        axisDirection = -1;

        ThrottleType = "Button Based";

        increaseThrottleButton = 1;
        decreaseThrottleButton = 2;
        increaseThrottleAxis = 1;
        decreaseThrottleAxis = 2;
        oneAxisThrottleAxis = 3;
    }
    public static void SystemJOY() {
        levelUpButton = 2;
        levelDownButton = 3;
        suctionButton = 5;
        climbButton = 6;
        autonomousButton = 7;
        solonoidButton = 8;
    }
    public static void OmniJOY() {xAxis = 1;
        yAxis = 4;
        zAxis = 1;
        axisDirection = -1;

        ThrottleType = "Button Based";

        increaseThrottleButton = 1;
        decreaseThrottleButton = 2;
        increaseThrottleAxis = 1;
        decreaseThrottleAxis = 2;
        oneAxisThrottleAxis = 3;

        levelUpButton = 2;
        levelDownButton = 3;
        suctionButton = 5;
        climbButton = 6;
        autonomousButton = 7;
        solonoidButton = 8;
    }
}
