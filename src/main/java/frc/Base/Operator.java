package frc.Base;

import frc.Base.Joysticks.*;

public class Operator {
    /*
    Controller (Gamepad F310)
    T.16000M
    Controller (Xbox One For Windows)
    Logitech Extreme 3D
    Logitech RumblePad 2 USB
     */

    private static String controllerAvailability = "";
    public static String getControllerAvailability() { return controllerAvailability;}
    private static void setControllerAvailability(String value) { controllerAvailability = value;}

    private static void updateDriveController() {
        switch(Controls.mainJoystick.getName()) {
            case "Controller (Gamepad F310)": Controller_Gamepad_F310.DriveJOY(); break;
            case "T.16000M": T_16000M.DriveJOY(); break;
            case "Controller (Xbox One For Windows)": Controller_Xbox_One_For_Windows.DriveJOY(); break;
            case "Logitech Extreme 3D": Logitech_Extreme_3D.DriveJOY(); break;
            case "Logitech RumblePad 2 USB": Logitech_RumblePad_2_USB.DriveJOY(); break;
            case "MAYFLASH GameCube Controller Adapter": MAYFLASH_GameCube_Controller_Adapter.DriveJOY(); break;
        }
    }
    private static void updateSubDriveController() {
        switch(Controls.secondaryJoystick.getName()) {
            case "Controller (Gamepad F310)": Controller_Gamepad_F310.SystemJOY(); break;
            case "T.16000M": T_16000M.SystemJOY(); break;
            case "Controller (Xbox One For Windows)": Controller_Xbox_One_For_Windows.SystemJOY(); break;
            case "Logitech Extreme 3D": Logitech_Extreme_3D.SystemJOY(); break;
            case "Logitech RumblePad 2 USB": Logitech_RumblePad_2_USB.SystemJOY(); break;
            case "MAYFLASH GameCube Controller Adapter": MAYFLASH_GameCube_Controller_Adapter.SystemJOY(); break;
        }
    }
    private static void updateController() {
        switch (getControllerAvailability()) {
            case "Drive Joysticks Not Available":
                switch (Controls.secondaryJoystick.getName()) {
                    case "Controller (Gamepad F310)": Controller_Gamepad_F310.OmniJOY(); break;
                    case "T.16000M": T_16000M.OmniJOY(); break;
                    case "Controller (Xbox One For Windows)": Controller_Xbox_One_For_Windows.OmniJOY(); break;
                    case "Logitech Extreme 3D": Logitech_Extreme_3D.OmniJOY(); break;
                    case "Logitech RumblePad 2 USB": Logitech_RumblePad_2_USB.OmniJOY(); break;
                    case "MAYFLASH GameCube Controller Adapter": MAYFLASH_GameCube_Controller_Adapter.OmniJOY(); break;
                }
                break;
            case "System Joysticks Not Available":
                switch (Controls.mainJoystick.getName()) {
                    case "Controller (Gamepad F310)": Controller_Gamepad_F310.OmniJOY(); break;
                    case "T.16000M": T_16000M.OmniJOY(); break;
                    case "Controller (Xbox One For Windows)": Controller_Xbox_One_For_Windows.OmniJOY(); break;
                    case "Logitech Extreme 3D": Logitech_Extreme_3D.OmniJOY(); break;
                    case "Logitech RumblePad 2 USB": Logitech_RumblePad_2_USB.OmniJOY(); break;
                    case "MAYFLASH GameCube Controller Adapter": MAYFLASH_GameCube_Controller_Adapter.OmniJOY(); break;
                }
                break;
            case "Both Joysticks Are Available":
                updateDriveController();
                updateSubDriveController();
                break;
        }
    }
    public static void update() {
        setControllerAvailability(
                Controls.mainJoystick.getButtonCount() == 0 ? "Drive Joystick Not Available":
                Controls.secondaryJoystick.getButtonCount() == 0 ? "System Joystick Not Available":
                                                            "Both Joysticks Are Available");
        updateController();
    }
}