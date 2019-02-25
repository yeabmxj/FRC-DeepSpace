package frc.StateMachines;

import frc.robot.Robot;

public class i_Drivetrain extends frc.Base.Input {

    public void update() {
            setFSMState(
                    Robot.b_drivetrain.getDriveTrainType().equals("Mecanum") ? "Mecanum" :
                    Robot.b_drivetrain.getDriveTrainType().equals("Tank") ? "Tank" : "Stopped");
            Robot.m_drivetrain.update();
    }
}