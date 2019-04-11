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
			case "None":
				break;
			case "Hatch":
				Robot.m_drivetrain.TankDrive(0,0,0);
				break;
			case "Ball":
				break;
		}
	}
}
