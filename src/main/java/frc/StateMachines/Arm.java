package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class Arm extends State {

    /* List of states
     * Input
     * Currently Moving
     * Manual Move
     */

    public static final int Input = 0;
    public static final int CurrentlyMoving = 5;
    public static final int ManualMove = 6;

    public void update() {
        switch (state) {
            case Input:
                setFSMState("home");
                setFSMState(
                        Controls.setArmLevel1() ? "level 1" :
                        Controls.setArmLevel2() ? "level 2" :
                        Controls.setArmLevel3() ? "level 3" :
                        Controls.home() ? "home" : "input");
                setState(
                        getFSMState().equals("level 1") ||
                        getFSMState().equals("level 2") ||
                        getFSMState().equals("level 3") ||
                        getFSMState().equals("home") ? CurrentlyMoving : Input);
                break;
            case CurrentlyMoving:
                Robot.armManipulator.updateLevel();
                Robot.armManipulator.updateSpeed();
                setFSMState(Robot.armManipulator.getSpeed() == 0 ? "stopped" : "moving");
                setState(getFSMState().equals("stopped") ? Input : CurrentlyMoving);
                break;
                }
        }

    }
