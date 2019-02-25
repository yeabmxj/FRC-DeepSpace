package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Timer;
import frc.Base.Constants;
import frc.robot.Robot;

public class m_Climber {

    public VictorSPX climbUp;
    public VictorSPX climbForward;

    private double[] RiseandRun;

    public m_Climber() {
        climbUp = new VictorSPX(Constants.UP_CLIMBER_VICTOR_ID);
        climbForward = new VictorSPX(Constants.FORWARD_CLIMBER_VICTOR_ID);
    }
    public void RiseandRun(double rise, double reach) {
        climbUp.set(ControlMode.PercentOutput, rise);
        climbForward.set(ControlMode.PercentOutput, reach);}

    public void update() {
        switch (Robot.i_arm.getFSMState()) {
            case "EXTENDING":
                Robot.i_climber.INUSE = true;
                RiseandRun[0] = Timer.getFPGATimestamp() < 4 ? Constants.RISE_CLIMBER_SPEED : 0;
                RiseandRun[1] = Timer.getFPGATimestamp() < 4 ? Constants.RUN_CLIMBER_SPEED : 0;
                RiseandRun(RiseandRun[0], RiseandRun[1]);
                Robot.i_climber.INUSE = false;
                break;
            case "NOT EXTENDING":
                RiseandRun(0,0);
                break;
        }
    }
}
