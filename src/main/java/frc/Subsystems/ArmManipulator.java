package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.Base.Constants;
import frc.robot.Robot;

public class ArmManipulator {
    TalonSRX armManipulator;

    DigitalInput home;
    DigitalInput level1;
    DigitalInput level2;
    DigitalInput level3;

    private int level;

    public void setLevel(int l) { level = l;}
    public int getSetLevel() { return level; }

    public ArmManipulator() {
        armManipulator = new TalonSRX(Constants.ARM_TALON_ID);

        armManipulator.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 5);

        home = new DigitalInput(Constants.HOME_LIMIT_SWITCH_ID);
        level1 = new DigitalInput(Constants.LEVEL_1_LIMIT_SWITCH_ID);
        level2 = new DigitalInput(Constants.LEVEL_2_LIMIT_SWITCH_ID);
        level3 = new DigitalInput(Constants.LEVEL_3_LIMIT_SWITCH_ID);
    }
    public double getHeight() {
        return armManipulator.getSelectedSensorPosition(0);
    }
    public boolean getLevelPressed(int level) {
        return (level == 0 ? home : level == 1 ? level1 : level == 2 ? level2 : level == 3 ? level3 : null).get();
    }
    public double getCurrentLevel() {
        return level = (home.get() ? 0 : level1.get() ? 1 : level2.get() ? 2 : level3.get() ? 3 : 5);
    }
    public void setSpeed(double output) {
        armManipulator.set(ControlMode.Position, output);
    }
    public double getSpeed() {
        return armManipulator.getMotorOutputPercent() / 100;
    }

    public void updateLevel() {
        setLevel(
            Robot.arm.getFSMState().equals("level 1") ? 1 :
            Robot.arm.getFSMState().equals("level 2") ? 2 :
            Robot.arm.getFSMState().equals("level 3") ? 3 :
            Robot.arm.getFSMState().equals("home") ? 0 : 5);
    }
    public void updateSpeed() {
        setSpeed(
            getLevelPressed(getSetLevel()) ? 0 :
            getCurrentLevel() > getSetLevel() &&
            getCurrentLevel() != 5 ? -.5 :
            getCurrentLevel() < getSetLevel() ? .5 :
            getCurrentLevel() == 5 ? 0:0);
    }
}
