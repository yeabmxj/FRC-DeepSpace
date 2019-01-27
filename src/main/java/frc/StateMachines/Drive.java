package frc.StateMachines;

import edu.wpi.first.wpilibj.DriverStation;
import frc.Base.SwitchStateBase;
import frc.Base.Controls;
import frc.robot.Robot;

public class Drive {

    public static final int Input = 0;
    public static final int Mecanum = 1;
    public static final int Tank = 2;

    double throttle = .5;

    public int state = 0;
    public void setState(int s) { state = s;}

    public void update() {
        switch(state) {
            case Input:
                setState(Robot.dLibrary.getDriveTrainType().equals("Mecanum") ? Mecanum : Tank);
                break;
            case Mecanum:
                if (Controls.increaseThrottle()) { throttle += .02;}
                if (Controls.decreaseThrottle()) { throttle -= .02;}
                if (throttle > 1) { throttle = 1;}
                if (throttle < 0) { throttle = 0;}
                Robot.driveTrain.MecanumDrive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.driveTrain.getYaw(), throttle);
                break;
            case Tank:
                if (Controls.increaseThrottle()) { throttle += .02;}
                if (Controls.decreaseThrottle()) { throttle -= .02;}
                if (throttle > 1) { throttle = 1;}
                if (throttle < 0) { throttle = 0;}
                Robot.driveTrain.TankDrive(Controls.getY(), Controls.getX(), throttle);
                break;
        }
    }

}