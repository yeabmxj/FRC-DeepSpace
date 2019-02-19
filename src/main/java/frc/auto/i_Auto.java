package frc.auto;

import frc.Base.*;
import frc.robot.Robot;

public class i_Auto extends Input {

    public void update() {
        setFSMState(Controls.getButton(Controls.autonomousButton) ? "AUTO" : "DEACTIVATED");
        setMessage(Robot.e_limeLightVision.getTarget() != 0 ? "REACH LINE" : "SPIN");
        Robot.m_auto.update();
    }
}
