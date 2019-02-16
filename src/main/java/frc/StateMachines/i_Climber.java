package frc.StateMachines;

import edu.wpi.first.wpilibj.Timer;
import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Climber extends State {
    public static final int INPUT = 0;
    public static final int EXTENDING = 2;

    public void update() {
        switch (state){
            case INPUT:
                setFSMState("Not Extended");
                setFSMState(Timer.getFPGATimestamp() >= 135 && Controls.climb() ? "Extending" : "Not Extending");
                setState(getFSMState().equals("Extending") ? EXTENDING : INPUT);
                Robot.m_climber.update();
                break;
        }
    }
}
