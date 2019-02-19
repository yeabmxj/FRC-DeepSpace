package frc.auto;

import frc.Base.Constants;
import frc.Base.MotionCalculation;
import frc.Subsystems.m_Drivetrain;
import frc.robot.Robot;

public class m_Auto extends m_Drivetrain {

	public double ellipticalDriveDistance(double angle, double value, double valueOffset) {
		double angleAtTarget = 90 - angle;
		double newValue = value + valueOffset;
		double calculatedValue = (Math.sin(angle) * newValue) / Math.sin(angleAtTarget);
		double aValue = newValue > calculatedValue ? newValue : calculatedValue;
		double bValue = newValue < calculatedValue ? newValue : calculatedValue;
		double hValue = (Math.pow((aValue - bValue), 2)) / (Math.pow((aValue + bValue), 2));
		double perimeter = Math.PI * (aValue + bValue) * (1 + ((3 * hValue) / (10 + Math.sqrt(4 - (3 * hValue)))));
		return perimeter / 4; //This is the distance to be traveled
	}

	public void startLine(double distance) { MotionCalculation.normalize(distance,1,Robot.m_drivetrain.getEncodervalues(), 1);}
	public void startTurn(double angle) { MotionCalculation.normalize(angle,5,Robot.e_navx.getPitch(), 1);}
	public void startActuation(double setPoint) { MotionCalculation.normalize(setPoint,5,Robot.e_navx.getRoll(), 1);}

	public void update() {
		switch (Robot.i_auto.getFSMState()) {

			case "AUTO":
				switch (Robot.i_auto.getMessage()) {
					case "Spin":
						Robot.i_auto.INUSE = true;
						TankDrive(0, Robot.e_limeLightVision.getTarget() == 0 ? .5 : 0 , .5);
						Robot.i_auto.INUSE = false;
						break;
					case "Approach":
						Robot.i_auto.INUSE = true;
						MotionCalculation.setSystem("Auto");
						double AngleAtTarget = Math.abs(Robot.e_limeLightVision.getX());

						TankLeft(MotionCalculation.normalize(90, 0, Robot.e_navx.getPitch(), 5));
						TankRight(MotionCalculation.normalize(90, 0, Robot.e_navx.getPitch(), 5));
						if (MotionCalculation.isFinished()) {
							double DistanceToWall = Voltage_to_Feet((leftIRSensor.getVoltage() + rightIRSensor.getVoltage()) / 2);

							double SDistance = ellipticalDriveDistance(AngleAtTarget, DistanceToWall, Voltage_to_Feet(Constants.FRONT_TO_SMALL_ELLIPSE_ENCODER));
							double LDistance = ellipticalDriveDistance(AngleAtTarget, DistanceToWall, Voltage_to_Feet(Constants.FRONT_TO_BIG_ELLIPSE_ENCODER));

							TankLeft(MotionCalculation.normalize(-90, 0, Robot.e_navx.getPitch(), 5));
							TankRight(MotionCalculation.normalize(-90, 0, Robot.e_navx.getPitch(), 5));
							if (MotionCalculation.isFinished()) {
								TankLeft(MotionCalculation.normalize(SDistance, 0, getEncodervalues(), 1));
								TankRight(MotionCalculation.normalize(LDistance, 0, getEncodervalues(), 1));
								if (MotionCalculation.isFinished()) {
									Robot.i_auto.INUSE = false;
								}
							}
						}
						break;
				}
				break;
			case "DEACTIVATED":
				Robot.i_auto.update();
				break;
		}
	}
}
