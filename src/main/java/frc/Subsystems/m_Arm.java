package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.Base.Constants;
import frc.Base.MotionCalculation;
import frc.Base.Input;
import frc.robot.Robot;

public class m_Arm extends Input {

    private TalonSRX DART;

    private double target;
    double ArmTarget;

    private int direction;

    public m_Arm() {
        DART = new TalonSRX(Constants.ARM_TALON_ID);
    }
    public void setSpeed(double speed) { DART.set(ControlMode.PercentOutput, speed);}
    public double getSpeed() { return DART.getSelectedSensorVelocity(0);}
    public void setTarget() {
        ArmTarget += Robot.i_arm.getMessage().equals("Level Up") ? 1 :
                Robot.i_arm.getMessage().equals("Level Down") ? -1 : 0;
        target =
                ArmTarget == 0 ? Constants.HOME_HEIGHT:
                ArmTarget == 1 ? Constants.LEVEL_1_HEIGHT:
                ArmTarget == 2 ? Constants.LEVEL_2_HEIGHT:
                ArmTarget == 3 ? Constants.LEVEL_3_HEIGHT: -1;
    }
    public void update() {
        switch (Robot.i_arm.getFSMState()) {
            case "MOVING":
                Robot.i_arm.INUSE = true;
                ArmTarget += Robot.i_arm.getMessage().equals("Level Up") ? 1 :
                        Robot.i_arm.getMessage().equals("Level Down") ? -1 : 0;
                setTarget();
                MotionCalculation.setSystem("Arm");
                double direction = Double.compare(target, Robot.e_navx.getRoll());
                setSpeed(direction * .5 * MotionCalculation.normalize(target,0, Robot.e_navx.getRoll(), 5));
                INUSE = false;
                break;
            case "STOPPED":
                setSpeed(0);
                break;
        }
    }
}
