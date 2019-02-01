package frc.StateMachines;

import edu.wpi.first.wpilibj.Timer;
import frc.Base.SwitchStateBase;
import frc.robot.Robot;

public class Climb extends SwitchStateBase {
    public static final int INPUT = 0;
    public static final int NOTEXTENDED = 1;
    public static final int EXTENDING = 2;
    public static final int REACHED = 3;

    double up = 10;
    double forward = 10;

    public void update() {
        switch (state){
            case INPUT:
                if(Timer.getFPGATimestamp() >= 135){
                    setState(EXTENDING);
                }
                else {
                    setState(NOTEXTENDED);
                }
                break;
            case NOTEXTENDED:
                Robot.climber.Climb(0,0);
                break;
            case EXTENDING:
                if (Robot.climber.getExtensionUP() != up || Robot.climber.getExtensionForward() != forward) {
                    Robot.climber.Climb(up, forward);
                }
                else {
                    setState(REACHED);
                }
                break;
            case REACHED:
                if(Robot.climber.getExtensionUP() != 0 || Robot.climber.getExtensionForward() != 0) {
                    Robot.climber.Climb(-up, -forward);
                }
                else {
                    Robot.climber.Climb(0,0);
                }
                break;
        }
    }
}
