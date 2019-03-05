package frc.auto;

import edu.wpi.first.wpilibj.Joystick;
import frc.Base.MotionCalculation;
import frc.robot.Robot;

public class Routine {
	Joystick joystick = new Joystick(0);
	public boolean chooseHeldItem() { return joystick.getRawButton(0);}
	double count = 2;
	String heldItem;
	double HatchAngle;
	double BallAngle;
	double ShipAngle;
	String status;

	public void Run() {
		count +=
				count > 4 ? -2 :
				chooseHeldItem() ? 1 : 0;
		heldItem =
				count % 2 == 0 && count % 4 != 0 ? "None":
				count % 3 == 0 ? "Hatch":
				count % 4 == 0 ? "Ball": "None";
	}
	public void move() {
		switch (heldItem) {
			case "Hatch":
				switch (Robot.message.getLastResponse()) {
					case "yellow":
						if ((Robot.e_limeLightVision.getX() + Robot.e_navx.getRoll()) == HatchAngle) {
							Robot.m_drivetrain.TankLeft(MotionCalculation.normalize(0,5,Robot.e_limeLightVision.getX(), 5));
							Robot.m_drivetrain.TankRight(MotionCalculation.normalize(0,5,Robot.e_limeLightVision.getX(), 5));
							status = "motion executed";

							if(MotionCalculation.isFinished()) {
								Robot.m_drivetrain.TankLeft(MotionCalculation.normalize(1.5,1,Robot.e_limeLightVision.getDistance(), 1));
								Robot.m_drivetrain.TankRight(MotionCalculation.normalize(1.5,1,Robot.e_limeLightVision.getDistance(), 1));
								status = "motion complete";
							}
						}
						else {
							status = "motion not executed: wrong angle";
						}
						break;
					case "orange":
						status = "motion not executed";
						break;
				}
				break;
			case "Ball":
				switch (Robot.message.getLastResponse()) {
					case "yellow":
						status = "motion not executed";
						break;
					case "orange":
						if ((Robot.e_limeLightVision.getX() + Robot.e_navx.getRoll()) == HatchAngle) {
							Robot.m_drivetrain.TankLeft(MotionCalculation.normalize(0,5,Robot.e_limeLightVision.getX(), 5));
							Robot.m_drivetrain.TankRight(MotionCalculation.normalize(0,5,Robot.e_limeLightVision.getX(), 5));
							status = "motion executed";

							if(MotionCalculation.isFinished()) {
								Robot.m_drivetrain.TankLeft(MotionCalculation.normalize(1.5,1,Robot.e_limeLightVision.getDistance(), 1));
								Robot.m_drivetrain.TankRight(MotionCalculation.normalize(1.5,1,Robot.e_limeLightVision.getDistance(), 1));
								status = "motion complete";
							}
						}
						else {
							status = "motion not executed: wrong angle";
						}
						break;
				}
				break;
		}
	}
}
