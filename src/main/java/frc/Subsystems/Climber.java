package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.Base.Constants;
import frc.StateMachines.Arm;
import frc.robot.Robot;

public class Climber {

    public VictorSPX climbUp;
    public VictorSPX climbForward;

    public Climber() {
        climbUp = new VictorSPX(Constants.UP_CLIMBER_VICTOR_ID);
        climbForward = new VictorSPX(Constants.FORWARD_CLIMBER_VICTOR_ID);
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
                if (Robot.climber.getExtensionUP() != Constants.CLIMB_UP_HEIGHT || Robot.climber.getExtensionForward() != Constants.CLIMB_FORWARD_HEIGHT) {
                    Robot.climber.climb(Constants.CLIMB_UP_HEIGHT, Constants.CLIMB_FORWARD_HEIGHT); }
                else { Robot.arm.setState(Arm.Input); }
                break;
            case "Not Extending":
                if(Robot.climber.getExtensionUP() != 0 || Robot.climber.getExtensionForward() != 0) {
                    Robot.climber.climb(-Constants.CLIMB_UP_HEIGHT, -Constants.CLIMB_FORWARD_HEIGHT); }
                else { Robot.climber.climb(0,0); }
                break;
        }
    }
}
