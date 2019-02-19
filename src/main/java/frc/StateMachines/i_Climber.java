package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.Input;
import frc.robot.Robot;

public class i_Climber extends Input {

    public void update() {
        setFSMState("NOT EXTENDING");
        Robot.m_climber.update();
        setFSMState(Controls.getButton(Controls.climbButton) ? "EXTENDING" : "NOT EXTENDING");
    }
}
