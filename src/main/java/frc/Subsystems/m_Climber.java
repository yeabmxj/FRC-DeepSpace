package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Timer;
import frc.Base.Constants;
import frc.Base.MotionCalculation;
import frc.robot.Robot;

public class m_Climber {

    private VictorSPX climbUp;
    private VictorSPX climbForward;

    private double[] Rise_and_Run;

    public m_Climber() {
        climbUp = new VictorSPX(Constants.UP_CLIMBER_VICTOR_ID);
        climbForward = new VictorSPX(Constants.FORWARD_CLIMBER_VICTOR_ID);
    }
    public void RiseandRun(double rise, double reach) {
        climbUp.set(ControlMode.PercentOutput, rise);
        climbForward.set(ControlMode.PercentOutput, reach);}

    public void climbCorrection() {
//        climbUp.set(ControlMode.PercentOutput,
//            MotionCalculation.normalize(0,3,Robot.e_navx.getPitch(), 3) / 2 +
//            MotionCalculation.normalize(climbHeight,1,climbencoder,1));
    }

    public void ClawClimb(TalonSRX claw, TalonSRX front_post, AHRS body_Navx) {
    	double back_angle_danger_zone = 25;
    	double needed_height = 1.5; //In feet
		double needed_claw_placement = 1;
    	body_Navx.reset();
    	if(front_post.getSelectedSensorPosition(0) < needed_height) {
			if (Math.abs(body_Navx.getPitch()) < back_angle_danger_zone) {
				claw.set(ControlMode.PercentOutput, .5);
			} else {
				if (claw.getSelectedSensorPosition(0) < needed_claw_placement) {
					claw.set(ControlMode.PercentOutput, .3);
				}
				else {
					claw.set(ControlMode.PercentOutput, 0);
				}
				front_post.set(ControlMode.PercentOutput, MotionCalculation.normalize(0, 3, body_Navx.getRoll(), 3));
			}
		}
    	else {
    		front_post.set(ControlMode.PercentOutput, 0);
		}
	}
	public void retract_claw(TalonSRX claw) {
    	if(claw.getSelectedSensorPosition(0) > 0) {
			claw.set(ControlMode.PercentOutput, .5);
		}
    	else {
    		claw.set(ControlMode.PercentOutput, 0);
		}
	}
	public void retract_front_post(TalonSRX front_post) {
		if(front_post.getSelectedSensorPosition(0) > 0) {
			front_post.set(ControlMode.PercentOutput, .5);
		}
		else {
			front_post.set(ControlMode.PercentOutput, 0);
		}
	}

    public void update() {
        switch (Robot.i_arm.getFSMState()) {
            case "EXTENDING":
                Robot.i_climber.INUSE = true;
                Rise_and_Run[0] = Timer.getFPGATimestamp() < 4 ? Constants.RISE_CLIMBER_SPEED : 0;
                Rise_and_Run[1] = Timer.getFPGATimestamp() < 4 ? Constants.RUN_CLIMBER_SPEED : 0;
                RiseandRun(Rise_and_Run[0], Rise_and_Run[1]);
                Robot.i_climber.INUSE = false;
                break;
            case "NOT EXTENDING":
                RiseandRun(0,0);
                break;
        }
    }
}
