package frc.StateMachines;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Controls;
import frc.robot.Robot;
import frc.robot.Constants;
import frc.Subsystems.*;

public class Drive extends Base{
    
    private double Throt = .5;

    public void update() {
        switch(state) {
            case Stop:
                Robot.mecanum.Drive(0, 0, 0, 0, 0);
                break;
            case Input:
                if (DriverStation.getInstance().isAutonomous()) { setState(Sandstorm);}
                else { setState(Driving);}
                break;
            case Sandstorm:
                break;
            case Driving:
                if (Controls.increaseThrottle()){ // Right button increases throttle
                    Throt += 0.02;
                }
                else if (Controls.decreaseThrottle()){ // Left button decreased throttle
                    Throt -= 0.02;
                }
                if(Throt > 1) {
                    Throt = Math.signum(Throt);
                }
                if (Throt <= 0) {
                    Throt = 0;
                }
                Robot.mecanum.Drive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.mecanum.getYaw(), Throt);
                break;
        }
    }

}