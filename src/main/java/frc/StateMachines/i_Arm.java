package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.Input;
import frc.robot.Robot;

public class i_Arm extends Input {

    public void update() {
        System.out.println(INUSE ? "MOVING" : "STOPPED");

        setFSMState(INUSE ? getFSMState() :
                Controls.getButton(Controls.homeButton) ||
                Controls.getButton(Controls.level1Button) ||
                Controls.getButton(Controls.level2Button) ||
                Controls.getButton(Controls.level3Button) ? "MOVING" : "STOPPED");

        setMessage(INUSE ? getFSMState() : (
                Controls.getButton(Controls.homeButton) ? "HOME" :
                Controls.getButton(Controls.level1Button) ? "LEVEL 1" :
                Controls.getButton(Controls.level2Button) ? "LEVEL 2" :
                Controls.getButton(Controls.level3Button) ? "LEVEL 3" : "STOP"));
        Robot.m_arm.update();
    }
}