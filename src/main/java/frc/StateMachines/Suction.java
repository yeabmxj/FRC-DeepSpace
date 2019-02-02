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
                if (Controls.suction()) {setState(SUCCAGE);}
                else{
                    Robot.vaccum.succ(0);
                    Robot.vaccum.setAngle(90);
                }
                break;
            case SUCCAGE:
                if (Controls.suction()) {setState(STOP);}
                else {Robot.vaccum.succ(Constants.SparkSuctionSpeed);}
                break;
        }
    }
}
