package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.FSMBase;
import frc.robot.Robot;

public class Arm {

    /* List of states
     * Input
     * Currently Moving
     * Manual Move
     */

    private static String currentState = "";
    private int switchState = 0;
    private int level;

    public void setSwitchState(int s) { switchState = s;}
    public void setLevel(int l) { level = l;}
    public static void setFSMState(String state) { currentState = state;}
    public String getFSMState() { return currentState;}
    public int getSetLevel() { return level; }

    public static final int Input = 0;
    public static final int CurrentlyMoving = 5;
    public static final int ManualMove = 6;

    public void update() {
        switch (switchState) {
            case Input:
                setFSMState("home");
                setFSMState(Controls.setArmLevel1() ? "level 1" :
                            Controls.setArmLevel2() ? "level 2" :
                            Controls.setArmLevel3() ? "level 3" :
                            Controls.home() ? "home" : "input");
                setSwitchState(getFSMState().equals("level 1") ||
                               getFSMState().equals("level 2") ||
                               getFSMState().equals("level 3") ||
                               getFSMState().equals("home") ? CurrentlyMoving : Input);
                break;
            case CurrentlyMoving:
                setLevel(getFSMState().equals("level 1") ? 1 :
                         getFSMState().equals("level 2") ? 2 :
                         getFSMState().equals("level 3") ? 3 :
                         getFSMState().equals("home") ? 0 : 5);
                Robot.armManipulator.setSpeed(
                        Robot.armManipulator.getLevelPressed(Robot.arm.getSetLevel()) ? 0 :
                        Robot.armManipulator.getCurrentLevel() > Robot.arm.getSetLevel() &&
                        Robot.armManipulator.getCurrentLevel() != 5 ? -.5 :
                        Robot.armManipulator.getCurrentLevel() < Robot.arm.getSetLevel() ? .5 :
                        Robot.armManipulator.getCurrentLevel() == 5 ? 0:0);
                setFSMState(Robot.armManipulator.getSpeed() == 0 ? "stopped" : "moving");
                setSwitchState(getFSMState().equals("stopped") ? Input : CurrentlyMoving);
                break;
                }
        }

    }
