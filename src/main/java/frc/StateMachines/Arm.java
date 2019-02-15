package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

import java.util.Objects;

public class Arm extends State {

    /* List of states
     * Input
     * Currently Moving
     * Manual Move
     */

    public static final int Input = 0;
    public static final int Moving = 1;

    public void initState() {
        setFSMState("HOME");
    }

    public void update() {
        switch (state) {
            case Input:
                System.out.println(INUSE ? getFSMState() + " is currently in use" : "not in use");
                setFSMState(INUSE ? getFSMState() :
                        (Controls.setArmLevel1() ? "LEVEL 1" :
                        Controls.setArmLevel2() ? "LEVEL 2" :
                        Controls.setArmLevel3() ? "LEVEL 3" :
                        Controls.home() ? "HOME" : "INPUT"));
                setState(
                        getFSMState().equals("LEVEL 1") ||
                        getFSMState().equals("LEVEL 2") ||
                        getFSMState().equals("LEVEL 3") ||
                        getFSMState().equals("HOME") ? Moving : Input);
                break;
            case Moving:
                Robot.armManipulator.update();
                setFSMState(Robot.armManipulator.getSpeed() == 0 ? "STOPPED" : "INPUT");
                setState(getFSMState().equals("STOPPED") ? Input : Moving);
                break;
                }
        }

    }