package frc.StateMachines;

import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Wrist extends State {

	public void update() {
		setFSMState("ORIGIN");
		Robot.m_wrist.update();
		setFSMState(INUSE ? getFSMState() :
				Controls.getPOV() == -1 ||
				Controls.getPOV() == 0 ||
				Controls.getPOV() == 45 ||
				Controls.getPOV() == 90 ||
				Controls.getPOV() == 135 ||
				Controls.getPOV() == 180 ||
				Controls.getPOV() == 225 ||
				Controls.getPOV() == 270 ||
				Controls.getPOV() == 315 ? "MOVING" : "STOPPED"
		);
		setMessage(
				Controls.getPOV() == -1 ? "+0,+0" :
				Controls.getPOV() == 0 ? "+1,+0" :
				Controls.getPOV() == 45 ? "+5,+5" :
				Controls.getPOV() == 90 ? "+0,+1" :
				Controls.getPOV() == 135 ? "-5,+5" :
				Controls.getPOV() == 180 ? "-1,+0" :
				Controls.getPOV() == 225 ? "-5,-5" :
				Controls.getPOV() == 270 ? "+0,-1" :
				Controls.getPOV() == 315 ? "+5,-5" : "+0,+0"
				);
	}

}
