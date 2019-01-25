package frc.StateMachines;

import edu.wpi.first.wpilibj.DriverStation;
import frc.Base.SwitchStateBase;
import frc.Base.Controls;
import frc.robot.Robot;

public class Drive extends SwitchStateBase {

    public static final int Input = 0;
    public static final int Mecanum = 1;
    public static final int Tank = 2;

    public void update() {
        switch(state) {
            case Input:
                setState(Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? Mecanum : Tank);
                break;
            case Mecanum:
                if (Controls.activateVision()) {
                    setState(SandyDrive.Seeking);
                }
                Robot.driveTrain.MecanumDrive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.driveTrain.getYaw(), Controls.getThrottle());
                break;
            case Tank:
                if (Controls.activateVision()) {
                    setState(SandyDrive.Seeking);
                }
                Robot.driveTrain.TankDrive(Controls.getY(), Controls.getX(), Controls.getThrottle());
                break;
        }
    }

}