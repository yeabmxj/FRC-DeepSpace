package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.Input;
import frc.robot.Robot;

public class i_Arm extends Input {

    public void update() {
        setFSMState(INUSE ? getFSMState() :
                Controls.getButton(Controls.levelUpButton) ||
                        Controls.getButton(Controls.levelDownButton) ? "MANUAL" :
                        !Robot.e_navx.getAvailability() ?  "MANUAL" : "STOPPED");

        setMessage(INUSE ? getFSMState() : (
                Controls.getButton(Controls.levelUpButton) ? "LEVEL UP" :
                        Controls.getButton(Controls.levelDownButton) ? "LEVEL DOWN" : "STOPPED"));
        Robot.m_arm.update();
    }
}