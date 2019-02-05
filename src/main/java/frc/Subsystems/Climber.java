package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.Base.Constants;
import frc.StateMachines.Arm;
import frc.robot.Robot;

public class Climber {

    public TalonSRX climbUp;
    public TalonSRX climbForward;

    int climbUptalonid = 1;
    int climbForwardtalonid = 1;

    public Climber() {
        climbUp = new TalonSRX(climbUptalonid);
        climbForward = new TalonSRX(climbForwardtalonid);
    }
    public void climb(double rise, double reach) {
        climbUp.set(ControlMode.Position, rise);
        climbForward.set(ControlMode.Position, reach);
    }
    public double getExtensionUP() {
        return climbUp.getSelectedSensorPosition(0);
    }
    public double getExtensionForward() {
        return climbForward.getSelectedSensorPosition(0);
    }
    public void update() {
        switch(Robot.climb.getFSMState()) {
            case "Extending":
                if (Robot.climber.getExtensionUP() != Constants.ClimbUpFactor || Robot.climber.getExtensionForward() != Constants.ClimbForwardFactor) {Robot.climber.climb(Constants.ClimbUpFactor, Constants.ClimbForwardFactor); }
                else { Robot.arm.setState(Arm.Input); }
                break;
            case "Not Extending":
                if(Robot.climber.getExtensionUP() != 0 || Robot.climber.getExtensionForward() != 0) { Robot.climber.climb(-Constants.ClimbUpFactor, -Constants.ClimbForwardFactor); }
                else { Robot.climber.climb(0,0); }
                break;
        }
    }
}
