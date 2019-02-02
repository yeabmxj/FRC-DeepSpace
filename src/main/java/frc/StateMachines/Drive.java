package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class Drive extends State {

    public static final int Input = 0;
    private static final int Mecanum = 1;
    private static final int Tank = 2;

    public void update() {
        switch(state) {
            case Input:
                setState(Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? Mecanum : Tank);
                break;
            case Mecanum:
                Robot.driveTrain.MecanumDrive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.driveTrain.getYaw(), Controls.getT());
                break;
            case Tank:
                Robot.driveTrain.TankDrive(Controls.getY(), Controls.getX(), Controls.getT());
                break;
        }
    }
}