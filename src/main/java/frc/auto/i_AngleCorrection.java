package frc.auto;

import frc.Base.Controls;
import frc.Base.Input;
import frc.robot.Robot;

public class i_AngleCorrection extends Input {
	public void update() {
		setFSMState(
				INUSE ? getFSMState() :
				Controls.getButton(Controls.autonomousButton) && !Robot.m_angleCorrection.Alignment ? "Alignment" :
				Controls.getButton(Controls.autonomousButton) && Robot.m_angleCorrection.Alignment ? "Recognize and Run to Target" : "Nope");
		Robot.m_angleCorrection.update();
	}
}
