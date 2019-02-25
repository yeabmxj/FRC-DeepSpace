package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.Input;
import frc.robot.Robot;

public class i_Arm extends Input {

    public void update() {
        System.out.println(INUSE ? "MOVING" : "STOPPED");

        setFSMState(INUSE ? getFSMState() :
                Controls.getButton(Controls.levelUpButton) ||
                Controls.getButton(Controls.levelDownButton) ? "MOVING" : "STOPPED");

        setMessage(INUSE ? getFSMState() : (
                Controls.getButton(Controls.levelUpButton) ? "LEVEL UP" :
                        Controls.getButton(Controls.levelDownButton) ? "LEVEL DOWN" : "STOP"));
        Robot.m_arm.update();
    }
}