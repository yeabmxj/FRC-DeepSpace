package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Climber {

    public TalonSRX climbUp;
    public TalonSRX climbForward;

    int climbUptalonid = 1;
    int climbForwardtalonid = 1;

    public Climber() {
        climbUp = new TalonSRX(climbUptalonid);
        climbForward = new TalonSRX(climbForwardtalonid);
    }
    public void climb(double rise, double reach) {
        climbUp.set(ControlMode.Position, rise);
        climbForward.set(ControlMode.Position, reach);
    }
    public double getExtensionUP() {
        return climbUp.getSelectedSensorPosition(0);
    }
    public double getExtensionForward() {
        return climbForward.getSelectedSensorPosition(0);
    }
}
