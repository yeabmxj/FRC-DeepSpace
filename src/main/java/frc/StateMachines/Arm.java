package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.FSMBase;
import frc.robot.Robot;

public class Arm {

    /* List of states
     * Input
     * Home
     * Level 1
     * Level 2
     * Level 3
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
    public int getSetLevel() { return level;}

    public static final int Input = 0;
    public static final int CurrentlyMoving = 5;
    public static final int ManualMove = 6;

    public void update() {
        switch(switchState) {
            case Input:
                setFSMState("home");
                setLevel(Controls.setArmLevel1() ? 1 : Controls.setArmLevel2() ? 2 : Controls.setArmLevel3() ? 3: 0);
                setSwitchState(CurrentlyMoving);
                break;
            case CurrentlyMoving:
                setFSMState("moving");
                Robot.armManipulator.setSpeed(Robot.armManipulator.getLevelPressed(getSetLevel()) ? 0 : .5);
                setFSMState(Robot.armManipulator.getSpeed()  == 0 ? "stopped" : "moving");
                setSwitchState(Input);
                break;
        }
    }

}
