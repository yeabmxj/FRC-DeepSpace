package frc.StateMachines;

import frc.Base.SwitchStateBase;
import frc.robot.Robot;

public class Succ extends SwitchStateBase {
    public static final int STOP = 0;
    public static final int SUCCAGE = 1;

    double succc = .5;

    public void update() {
        switch (state) {
            case STOP:
                Robot.vaccum.succ(0);
                //Robot.vaccum.setAngle(Robot.vacuum.getAngle);
                /*
                if (Controls.startSucc) {
                    setState(SUCCAGE);
                }
                */
                break;
            case SUCCAGE:
                Robot.vaccum.succ(succc);
                //Robot.vaccum.setAngle(Robot.vacuum.getAngle);
                /*
                if (Controls.startSucc) {
                    setState(STOP);
                }
                */
                break;
        }
    }
}
