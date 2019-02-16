package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.Base.Constants;
import frc.robot.Robot;

public class m_Climber {

    public VictorSPX climbUp;
    public VictorSPX climbForward;

    public m_Climber() {
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
        switch(Robot.i_climber.getFSMState()) {
            case "Extending":
                if (Robot.m_climber.getExtensionUP() != Constants.CLIMB_UP_HEIGHT || Robot.m_climber.getExtensionForward() != Constants.CLIMB_FORWARD_HEIGHT) {
                    Robot.m_climber.climb(Constants.CLIMB_UP_HEIGHT, Constants.CLIMB_FORWARD_HEIGHT); }
                else { Robot.i_arm.setState(Robot.i_climber.INPUT); }
                break;
            case "Not Extending":
                if(Robot.m_climber.getExtensionUP() != 0 || Robot.m_climber.getExtensionForward() != 0) {
                    Robot.m_climber.climb(-Constants.CLIMB_UP_HEIGHT, -Constants.CLIMB_FORWARD_HEIGHT); }
                else { Robot.m_climber.climb(0,0); }
                break;
        }
    }
}
