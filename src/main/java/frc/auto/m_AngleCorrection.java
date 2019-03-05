package frc.auto;

import frc.Base.MotionCalculation;
import frc.Subsystems.m_Drivetrain;
import frc.robot.Robot;

public class m_AngleCorrection extends m_Drivetrain {

	boolean Alignment;

	String Target() {
		return
				Robot.e_navx.getYaw() < 46 && Robot.e_navx.getYaw() > 44 ? "Hatch Left":
				Robot.e_navx.getYaw() < -44 && Robot.e_navx.getYaw() > -46 ? "Hatch Right":
				Robot.e_navx.getYaw() < 92 && Robot.e_navx.getYaw() > 88 ? "Ball Rocket":
				Robot.e_navx.getYaw() < -88 && Robot.e_navx.getYaw() > -92 ? "Ball Cargo" : "Target Not Found";
	}

	public void update() {
		switch (Robot.i_angleCorrection.getFSMState()) {
			case "Alignment":
				Robot.i_angleCorrection.INUSE = true;
				MotionCalculation.normalize(0,5, Robot.e_limeLightVision.getX(),5);
				Alignment = MotionCalculation.isFinished();
				Robot.i_angleCorrection.INUSE = false;
				break;
			case "Recognize and Run to Target":
				Robot.i_angleCorrection.INUSE = true;
				MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1);
				Robot.i_angleCorrection.INUSE = false;
				break;
			case "":
				break;
		}
	}
}
