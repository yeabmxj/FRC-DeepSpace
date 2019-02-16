package frc.StateMachines;

import frc.Base.Constants;
import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class i_Vacuum extends State {
    public static final int STOP = 0;
    public static final int SUCCAGE = 1;

    public void update() {
        switch (state) {
            case STOP:
                Robot.m_vacuum.setSuccageSpeed(0);
                Robot.m_vacuum.setHeadAngle(90);
                setState(Controls.suction() ? SUCCAGE : STOP);
                break;
            case SUCCAGE:
                Robot.m_vacuum.setSuccageSpeed(Constants.SPARK_SUCTION_SPEED);
                setState(Controls.suction() ? STOP : SUCCAGE);
                break;
        }
    }
}
