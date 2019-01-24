package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.Base.Constants;
import frc.Base.PID;

public class ArmManipulator {
    TalonSRX arm;

    DigitalInput home;
    DigitalInput level1;
    DigitalInput level2;
    DigitalInput level3;

    PID armMan;

    public ArmManipulator() {
        arm = new TalonSRX(Constants.ArmTalonID);

        arm.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 5);

        home = new DigitalInput(Constants.HomeLimitSwitchID);
        level1 = new DigitalInput(Constants.Level1LimitSwitchID);
        level2 = new DigitalInput(Constants.Level2LimitSwitchID);
        level3 = new DigitalInput(Constants.Level3LimitSwitchID);
    }
    public double getHeight() {
        return arm.getSelectedSensorPosition(0);
    }
    public boolean getLevelPressed(int level) {
        return (level == 0 ? home : level == 1 ? level1 : level == 2 ? level2 : level == 3 ? level3 : null).get();
    }
    public void setSpeed(double output) {
        arm.set(ControlMode.Position, output);
    }
    public double getSpeed() {
        return arm.getMotorOutputPercent() / 100;
    }
}
