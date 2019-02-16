package frc.Base.Joysticks;

import frc.Base.Controls;

public class MAYFLASH_GameCube_Controller_Adapter extends Controls {
    public static void DriveJOY() {
        xAxis = 1;
        yAxis = 4;
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
        homeButton = 1;
        level1Button = 2;
        level2Button = 3;
        level3Button = 4;
        suctionButton = 5;
        climbButton = 6;
        autonomousButton = 7;
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

        homeButton = 1;
        level1Button = 2;
        level2Button = 3;
        level3Button = 4;
        suctionButton = 5;
        climbButton = 6;
        autonomousButton = 7;
    }
}
