package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.Base.Constants;
import frc.Base.Controls;
import frc.Base.MotionCalculation;
import frc.Base.State;
import frc.robot.Robot;

public class m_Arm extends State {

    private TalonSRX DART;

    private double target;

    public m_Arm() {
        DART = new TalonSRX(Constants.ARM_TALON_ID);
    }
    public void setSpeed(double speed) { DART.set(ControlMode.PercentOutput, speed);}
    public double getSpeed() { return DART.getSelectedSensorVelocity(0);}
    public void setTarget() {
        target = Robot.i_arm.getMessage().equals("HOME") ? Constants.HOME_HEIGHT :
                 Robot.i_arm.getMessage().equals("LEVEL 1") ? Constants.LEVEL_1_HEIGHT :
                 Robot.i_arm.getMessage().equals("LEVEL 2") ? Constants.LEVEL_2_HEIGHT :
                 Robot.i_arm.getMessage().equals("LEVEL 3") ? Constants.LEVEL_3_HEIGHT : 0;
    }
    public void update() {
        switch (Robot.i_arm.getFSMState()) {
            case "MOVING":
                Robot.i_arm.INUSE = true;

                MotionCalculation.setSystem("Arm");
                setTarget();
                int direction = Double.compare(target, Robot.eNavx.getRoll());

                System.out.println(Robot.i_arm.getMessage() + " " + target + " " + Robot.eNavx.getRoll());
                setSpeed(direction * .5 * MotionCalculation.normalize(target,0, Robot.eNavx.getRoll(), 5));
                if (MotionCalculation.isFinished()) { setSpeed(0);}
                Robot.i_arm.INUSE = false;
                break;
            case "STOPPED":
                setSpeed(0);
                break;
        }
    }
}
