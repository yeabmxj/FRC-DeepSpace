package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Arm extends State {

    public void update() {
        setFSMState("HOME");
        Robot.m_arm.update();
        System.out.println(INUSE ? "MOVING" : "STOPPED");

        setFSMState(INUSE ? getFSMState() :
                Controls.home() ||
                Controls.setArmLevel1() ||
                Controls.setArmLevel2() ||
                Controls.setArmLevel3() ? "MOVING" : "STOPPED");

        setMessage(INUSE ? getFSMState() :
                (Controls.setArmLevel1() ? "LEVEL 1" :
                Controls.setArmLevel2() ? "LEVEL 2" :
                Controls.setArmLevel3() ? "LEVEL 3" :
                Controls.home() ? "HOME" : "STOP"));
             }
}