package frc.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;

public class m_Vacuum {
	private Servo armHead;
	private Spark pump;

	private int servochannel = 7;
	private int pumpchannel = 7;
	double groundangle = 10;

	public m_Vacuum() {
		armHead = new Servo(servochannel);
		pump = new Spark(pumpchannel);
	}
	public void setSuccageSpeed(double succspeed) {
		pump.set(succspeed);
	}
	public void setHeadAngle(double angle) {
		armHead.set(angle);
	}
}
