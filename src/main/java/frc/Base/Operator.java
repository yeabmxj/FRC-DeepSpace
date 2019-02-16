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

    private static void updateDriveController() {
        switch(Controls.driveJoy.getName()) {
            case "Controller (Gamepad F310)":
                Controller_Gamepad_F310.DriveJOY();
                break;
            case "T.16000M":
                T_16000M.DriveJOY();
                break;
            case "Controller (Xbox One For Windows)":
                Controller_Xbox_One_For_Windows.DriveJOY();
                break;
            case "Logitech Extreme 3D":
                Logitech_Extreme_3D.DriveJOY();
                break;
            case "Logitech RumblePad 2 USB":
                Logitech_RumblePad_2_USB.DriveJOY();
                break;
            case "MAYFLASH GameCube Controller Adapter":
                MAYFLASH_GameCube_Controller_Adapter.DriveJOY();
                break;
        }
    }
    private static void updateSubDriveController() {
        switch(Controls.systemJoy.getName()) {
            case "Controller (Gamepad F310)":
                Controller_Gamepad_F310.SystemJOY();
                break;
            case "T.16000M":
                T_16000M.SystemJOY();
                break;
            case "Controller (Xbox One For Windows)":
                Controller_Xbox_One_For_Windows.SystemJOY();
                break;
            case "Logitech Extreme 3D":
                Logitech_Extreme_3D.SystemJOY();
                break;
            case "Logitech RumblePad 2 USB":
                Logitech_RumblePad_2_USB.SystemJOY();
                break;
            case "MAYFLASH GameCube Controller Adapter":
                MAYFLASH_GameCube_Controller_Adapter.SystemJOY();
                break;
        }
    }
    private static void updateControllerNotDetected() {
        if (Controls.driveJoy == null || Controls.driveJoy.getButtonCount() == 0) {
            switch (Controls.systemJoy.getName()) {
                case "Controller (Gamepad F310)":
                    Controller_Gamepad_F310.OmniJOY();
                    break;
                case "T.16000M":
                    T_16000M.OmniJOY();
                    break;
                case "Controller (Xbox One For Windows)":
                    Controller_Xbox_One_For_Windows.OmniJOY();
                    break;
                case "Logitech Extreme 3D":
                    Logitech_Extreme_3D.OmniJOY();
                    break;
                case "Logitech RumblePad 2 USB":
                    Logitech_RumblePad_2_USB.OmniJOY();
                    break;
                case "MAYFLASH GameCube Controller Adapter":
                    MAYFLASH_GameCube_Controller_Adapter.OmniJOY();
                    break;
            }
        }
        else if (Controls.systemJoy == null || Controls.systemJoy.getButtonCount() == 0) {
            switch (Controls.driveJoy.getName()) {
                case "Controller (Gamepad F310)":
                    Controller_Gamepad_F310.OmniJOY();
                    break;
                case "T.16000M":
                    T_16000M.OmniJOY();
                    break;
                case "Controller (Xbox One For Windows)":
                    Controller_Xbox_One_For_Windows.OmniJOY();
                    break;
                case "Logitech Extreme 3D":
                    Logitech_Extreme_3D.OmniJOY();
                    break;
                case "Logitech RumblePad 2 USB":
                    Logitech_RumblePad_2_USB.OmniJOY();
                    break;
                case "MAYFLASH GameCube Controller Adapter":
                    MAYFLASH_GameCube_Controller_Adapter.OmniJOY();
                    break;
            }
        }
    }
    public static void update() {
        if (
            Controls.driveJoy.getButtonCount() == 0 ||
            Controls.driveJoy == null ||
            Controls.driveJoy.getButtonCount() == 0 ||
            Controls.systemJoy == null) { updateControllerNotDetected();}
        else {
            updateDriveController();
            updateSubDriveController();
        }
    }
}