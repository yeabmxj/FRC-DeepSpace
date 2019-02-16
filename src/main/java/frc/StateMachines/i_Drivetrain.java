package frc.StateMachines;

import frc.Base.State;
import frc.robot.Robot;

public class i_Drivetrain extends State {


    public static final int Input = 0;

    public void update() {
            setFSMState(
                    Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? "Mecanum" :
                    Robot.dLibrary.getDriveTrainType().equals("Tank") ? "Tank" : "Input");
            Robot.m_drivetrain.update();
    }
}