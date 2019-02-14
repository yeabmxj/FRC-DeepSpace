package frc.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.Base.Constants;
import frc.Base.PID;
import frc.Base.State;
import frc.StateMachines.Arm;
import frc.robot.Robot;

public class ArmManipulator extends State {

    private TalonSRX DART;

    PID ARMCONTROL;

    public ArmManipulator() {
        DART = new TalonSRX(Constants.ARM_TALON_ID);
        ARMCONTROL = new PID("Arm Control");
    }
    public double getHeight() { return Robot.driveTrain.navx.getPitch(); }
    public void setSpeed(double speed) { DART.set(ControlMode.PercentOutput, speed);}
    public double getSpeed() { return DART.getSelectedSensorVelocity(0);}
    public void update() {
        switch (Robot.arm.getFSMState()) {
            case "INPUT":
                Robot.arm.setState(Arm.Input);
                break;
            case "HOME":
                Robot.arm.INUSE = true;
                setSpeed(ARMCONTROL.getPID(
                                Constants.HOME_HEIGHT,
                                Robot.driveTrain.navx.getRoll(),
                                Robot.driveTrain.navx.getVelocityY()) * Constants.ARM_SPEED_MULTIPLIER);
                if(ARMCONTROL.isFinished(.2,20)) {
                    Robot.arm.INUSE = false;
                }
                break;
            case "LEVEL 1":
                Robot.arm.INUSE = true;
                setSpeed(ARMCONTROL.getPID(
                        Constants.LEVEL_1_HEIGHT,
                        Robot.driveTrain.navx.getRoll(),
                        Robot.driveTrain.navx.getVelocityY()) * Constants.ARM_SPEED_MULTIPLIER);
                if(ARMCONTROL.isFinished(.2,20)) {
                    Robot.arm.INUSE = false;
                }
                break;
            case "LEVEL 2":
                Robot.arm.INUSE = true;
                setSpeed(ARMCONTROL.getPID(
                        Constants.LEVEL_2_HEIGHT,
                        Robot.driveTrain.navx.getRoll(),
                        Robot.driveTrain.navx.getVelocityY()) * Constants.ARM_SPEED_MULTIPLIER);
                if(ARMCONTROL.isFinished(.2,20)) {
                    Robot.arm.INUSE = false;
                }
                break;
            case "LEVEL 3":
                Robot.arm.INUSE = true;
                setSpeed(ARMCONTROL.getPID(
                        Constants.LEVEL_3_HEIGHT,
                        Robot.driveTrain.navx.getRoll(),
                        Robot.driveTrain.navx.getVelocityY()) * Constants.ARM_SPEED_MULTIPLIER);
                if(ARMCONTROL.isFinished(.2,20)) {
                    Robot.arm.INUSE = false;
                }
                break;
            case "STOPPED":
                setSpeed(0);
                Robot.arm.setState(Arm.Input);
                break;
        }
    }
}
