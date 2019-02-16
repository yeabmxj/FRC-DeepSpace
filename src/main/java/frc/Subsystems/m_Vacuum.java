package frc.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import frc.Base.Constants;
import frc.Base.MotionCalculation;
import frc.robot.Robot;

public class m_Vacuum {
	private Spark pump;


	public m_Vacuum() {
		pump = new Spark(Constants.VACUUM_SPARK_ID);
	}

	private void setSuccageSpeed(double succspeed) {
		pump.set(succspeed);
	}

	public void update() {
		switch (Robot.i_vacuum.getFSMState()) {
			case "EXTENDING":
				setSuccageSpeed(Constants.SPARK_SUCTION_SPEED);
				break;
			case "NOT EXTENDING":
				setSuccageSpeed(0);
				break;
		}
	}
}
