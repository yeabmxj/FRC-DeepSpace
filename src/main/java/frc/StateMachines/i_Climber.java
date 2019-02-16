package frc.StateMachines;

import edu.wpi.first.wpilibj.Timer;
import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Climber extends State {

    public void update() {
        setFSMState("NOT EXTENDING");
        Robot.m_climber.update();
        setFSMState(Controls.climb() ? "EXTENDING" : "NOT EXTENDING");
    }
}
