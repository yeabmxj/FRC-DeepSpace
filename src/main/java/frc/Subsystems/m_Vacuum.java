package frc.Subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import frc.Base.Constants;
import frc.Base.Controls;
import frc.robot.Robot;


public class m_Vacuum {
	private Spark pump;
	//private Relay solenoidControl;
	private double pressCount = 0;

	public m_Vacuum() {
		pump = new Spark(Constants.VACUUM_SPARK_ID);
		//solenoidControl = new Relay(Constants.SOLENOID_CONTROL_RELAY_ID, Relay.Direction.kBoth);
	}

	private void setSuccageSpeed(double succspeed) {
		pump.set(succspeed);
	}
	private void setPressCount() {
		pressCount =
			Controls.getButton(Controls.solenoidButton) && pressCount == 0 ? 1:
			Controls.getButton(Controls.solenoidButton) && pressCount == 1 ? 0: 1;
	}
//	private void setSolenoidExtension() {
//		setPressCount();
//		solenoidControl.set(
//				pressCount == 0 ? Relay.Value.kForward:
//				pressCount == 1 ? Relay.Value.kReverse:
//				Relay.Value.kOff);
//	}

	public void update() {
		switch (Robot.i_vacuum.getFSMState()) {
			case "SUCTION ON":
				setSuccageSpeed(Constants.SPARK_SUCTION_SPEED);
//				setSolenoidExtension();
				break;
			case "SUCTION OFF":
				setSuccageSpeed(0);
//				setSolenoidExtension();
				break;
		}
	}
}
