package frc.auto;

import frc.Base.*;
import frc.robot.Robot;

public class i_Auto extends State {
    public static final int Input = 0;
    public static final int Go = 1;

    public void update() {
        switch (state) {
            case Input:
                setFSMState(Controls.Autonomous() ? "" : "Manual");
                setState(
                        getFSMState().equals("") ? Go :
                        getFSMState().equals("Manual") ? Input : Input);
                break;
            case Go:
                Robot.m_drivetrain.updateAuto();
                break;
        }
    }
}
