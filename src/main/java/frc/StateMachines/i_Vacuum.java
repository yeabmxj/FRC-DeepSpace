package frc.StateMachines;

import frc.Base.Constants;
import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Vacuum extends State {

    public void update() {
        setFSMState("SUCTION OFF");
        Robot.m_vacuum.update();

        System.out.println(INUSE ? "SUCTION ON" : "SUCTION OFF");

        setFSMState(INUSE ? getFSMState() :
                Controls.suction() ? "SUCTION ON" : "SUCTION OFF");
        }
    }