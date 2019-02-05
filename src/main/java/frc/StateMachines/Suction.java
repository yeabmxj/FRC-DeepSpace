package frc.StateMachines;

import frc.Base.Constants;
import frc.Base.Controls;
import frc.Base.State;
import frc.robot.Robot;

public class Suction extends State {
    public static final int STOP = 0;
    public static final int SUCCAGE = 1;

    public void update() {
        switch (state) {
            case STOP:
                Robot.vaccum.succ(0);
                Robot.vaccum.setAngle(90);
                setState(Controls.suction() ? SUCCAGE : STOP);
                break;
            case SUCCAGE:
                Robot.vaccum.succ(Constants.SPARK_SUCTION_SPEED);
                setState(Controls.suction() ? STOP : SUCCAGE);
                break;
        }
    }
}
