package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class Drive extends State {


    public static final int Input = 0;

    public void update() {
        switch(state) {
            case Input:
                setFSMState("Input");
                setFSMState(
                            Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? "Mecanum" :
                            Robot.dLibrary.getDriveTrainType().equals("Tank") ? "Tank" : "Input");
                Robot.driveTrain.update();
                break;
        }
    }
}