package frc.StateMachines;

import frc.Base.SwitchStateBase;
import frc.auto.CSV.CSV;
import frc.Base.Constants;
import frc.Base.Controls;
import frc.robot.Robot;

public class SandyDrive extends SwitchStateBase {

    public static final int Seeking = 1;
    public static final int AimandApproach = 2;
    public static final int Stopped = 3;
    public static final int Sandstorm = 4;

    private double left = 0;
    private double right = 0;

    private double KP = .1;
    private double KPAIM = .1;
    private double KPDISTANCE = .1;

    private double MinAdd = .05;

    private double SteeringAdjust = 0;
    private double HeadingError = 0;
    private double DistanceError = 0;
    private double DistanceAdjust = 0;

    public void update() {
        switch (state) {
            case Seeking:
                if (Controls.activateVision()) {
                    if (Robot.limeLightVision.getTarget() == 0) {
                        SteeringAdjust = .3;
                    }
                    else {
                        HeadingError = Robot.limeLightVision.getX();
                        SteeringAdjust = KP * Robot.limeLightVision.getX();
                    }
                    left += SteeringAdjust;
                    right -= SteeringAdjust;
                    Robot.driveTrain.TankDrive(left, right, .5);
                    if (SteeringAdjust == 0) {
                        setState(AimandApproach);
                    }
                }
                else {
                    setState(Drive.Input);
                }
                break;
            case AimandApproach:
                if (Controls.activateVision()) {
                    HeadingError = -Robot.limeLightVision.getX();
                    DistanceError = -Robot.limeLightVision.getY();
                    SteeringAdjust = 0;
                    if (Robot.limeLightVision.getX() > 1) {
                        SteeringAdjust = KPAIM * HeadingError - MinAdd;
                    }
                    else if (Robot.limeLightVision.getX() < 1) {
                        SteeringAdjust = KPAIM * HeadingError + MinAdd;
                    }
                    DistanceError = Robot.limeLightVision.getY();
                    DistanceAdjust = KPDISTANCE * DistanceError;

                    left += SteeringAdjust + DistanceAdjust;
                    right -= SteeringAdjust + DistanceAdjust;

                    Robot.driveTrain.TankDrive(left, right, .5);

                    if (Robot.driveTrain.getDistanceToWall() < Constants.DistanceToTape || Robot.driveTrain.getVoltage() < Constants.IRWallDistanceVoltage) {
                        Robot.driveTrain.StopMotors();
                    }
                }
                else {
                    setState(Drive.Input);
                }
                break;
            case Stopped:
                Robot.driveTrain.StopMotors();
                break;
            case Sandstorm:
                Robot.driveTrain.MecanumDrive(CSV.AUTOY, CSV.AUTOX, CSV.AUTOZ, CSV.AUTOGYROANGLE, .5);

                if (Controls.activateVision()) {
                    setState(Seeking);
                }
                else {
                    setState(Drive.Input);
                }
                break;
        }
    }
}
