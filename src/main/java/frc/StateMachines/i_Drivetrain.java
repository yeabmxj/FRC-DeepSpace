package frc.StateMachines;

import frc.robot.Robot;

public class i_Drivetrain extends frc.Base.Input {


    public static final int Input = 0;

    public void update() {
            setFSMState(
                    Robot.b_drivetrain.getDriveTrainType().equals("Mecanum") ? "Mecanum" :
                    Robot.b_drivetrain.getDriveTrainType().equals("Tank") ? "Tank" : "Input");
            Robot.m_drivetrain.update();
    }
}