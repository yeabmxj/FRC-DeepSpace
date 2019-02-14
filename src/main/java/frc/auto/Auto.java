package frc.auto;

import frc.Base.*;
import frc.robot.Robot;

public class Auto extends State {
    public static final int Input = 0;
    public static final int Go = 1;

    public void update() {
        switch (state) {
            case Input:
                setFSMState(Controls.Vision() ? "Auto" : "Manual");
                setState(
                        getFSMState().equals("Auto") ? Go :
                        getFSMState().equals("Manual") ? Input : Input);
                break;
            case Go:
                Robot.driveTrain.updateAuto();
                break;
        }
    }
}
