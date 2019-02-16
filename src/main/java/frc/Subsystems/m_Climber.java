package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.Base.Constants;
import frc.Base.MotionCalculation;
import frc.robot.Robot;

public class m_Climber {

    public VictorSPX climbUp;
    public VictorSPX climbForward;

    public m_Climber() {
        climbUp = new VictorSPX(Constants.UP_CLIMBER_VICTOR_ID);
        climbForward = new VictorSPX(Constants.FORWARD_CLIMBER_VICTOR_ID);
    }
    public void setRise_and_Reach(double rise, double reach) {
        climbUp.set(ControlMode.PercentOutput, rise);
        climbForward.set(ControlMode.PercentOutput, reach);}
    public double getRise() { return climbUp.getSelectedSensorPosition(0);}
    public double getReach() { return climbForward.getSelectedSensorPosition(0);}

    public void update() {
        switch (Robot.i_arm.getFSMState()) {
            case "EXTENDING":
                Robot.i_climber.INUSE = true;
                setRise_and_Reach(
                        MotionCalculation.normalize(Constants.CLIMB_UP_HEIGHT,.5, Robot.m_climber.getRise(), 1),
                        MotionCalculation.normalize(Constants.CLIMB_FORWARD_HEIGHT,.5, Robot.m_climber.getReach(), 1));
                if(MotionCalculation.isFinished()) {
                    Robot.i_climber.INUSE = false;
                }
                break;
            case "NOT EXTENDING":
                setRise_and_Reach(0,0);
                break;
        }
    }
}
