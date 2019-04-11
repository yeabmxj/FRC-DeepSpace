package frc.auto;

import frc.Base.MotionCalculation;
import frc.Subsystems.m_Drivetrain;
import frc.robot.Robot;

public class m_AngleCorrection extends m_Drivetrain {

	boolean Alignment;

	String Target() {
		return
				Robot.e_navx.getYaw() < 50 && Robot.e_navx.getYaw() > 40 ? "Hatch Left":
				Robot.e_navx.getYaw() < -40 && Robot.e_navx.getYaw() > -50 ? "Hatch Right":
				Robot.e_navx.getYaw() < 95 && Robot.e_navx.getYaw() > 85 ? "Ball Rocket":
				Robot.e_navx.getYaw() < -85 && Robot.e_navx.getYaw() > -95 ? "Ball Cargo" : "Target Not Found";
	}

	public void update() {
		switch (Robot.i_angleCorrection.getFSMState()) {
			case "Alignment":
				Robot.i_angleCorrection.INUSE = true;
				MotionCalculation.setSystem("Auto");
				TankLeft(.5 * Math.abs(.5 + MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1)));
				TankRight(.5 * Math.abs(.5 - MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1)));

				Alignment = MotionCalculation.isFinished();
				Robot.i_angleCorrection.INUSE = false;
				break;
			case "Recognize and Run to Target":
				Robot.i_angleCorrection.INUSE = true;
				MotionCalculation.setSystem("Auto");
				TankLeft(.5 + .5 * MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1));
				TankRight(.5 - .5 * MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1));
				Robot.i_angleCorrection.INUSE = false;
				break;
			case "Nope":
				System.out.println("Button not pressed");
//				TankLeft(Math.abs(MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1)));
//				TankRight(Math.abs(MotionCalculation.normalize(1.5,1, Robot.e_limeLightVision.getDistance(),1)));
				break;
		}
	}
}
