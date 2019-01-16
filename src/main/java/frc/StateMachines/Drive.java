package frc.StateMachines;

import frc.robot.Controls;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import frc.auto.CSVRead;
import frc.robot.Constants;

public class Drive extends Base{
    
    public static final int Driving = 0;
    public static final int Seeking = 1;
    public static final int AimandApproach = 2;
    public static final int Stopped = 3;
    public static final int SandStorm = 4;

    public double left = 0;
    public double right = 0;

    public double KP = .1;
    public double KPAIM = .1;
    public double KPDISTANCE = .1;

    public double MinAdd = .05;

    public double Throt = .5;
    public double SteeringAdjust = 0;
    public double HeadingError = 0;
    public double DistanceError = 0;
    public double DistanceAdjust = 0;

    public void update() {
        switch(state) {
            case Driving:
                if (Controls.increaseThrottle()){ // Right button increases throttle
                    Throt += 0.02;
                }
                else if (Controls.decreaseThrottle()){ // Left button decreased throttle
                    Throt -= 0.02;
                }
                if(Throt > 1) {
                    Throt = Math.signum(Throt);
                }
                if (Throt <= 0) {
                    Throt = 0;
                }
                if (Robot.dLibrary.getDriveTrainType() == "Mecanum") {
                    Robot.driveTrain.MecanumDrive(Controls.getY(), Controls.getX(), Controls.getZ(), Robot.driveTrain.getYaw(), Throt);
                }
                else if (Robot.dLibrary.getDriveTrainType() == "Tank") {
                    Robot.driveTrain.TankDrive(Controls.getY(), Controls.getX(), Throt);
                }
                if (Controls.activateVision()) {
                    setState(Seeking);
                }
                if (DriverStation.getInstance().isAutonomous() && Controls.startReading()) {
                    setState(SandStorm);
                }
                break;
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
                    Robot.driveTrain.AutoDrive(left, right, Constants.AUTOSPEED);
                    if (SteeringAdjust == 0) {
                        setState(AimandApproach);
                    }
                }
                else {
                    setState(Driving);
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

                Robot.driveTrain.AutoDrive(left, right, Constants.AUTOSPEED);

                if (Robot.driveTrain.getDistanceToWall() < Constants.DistanceToTape || Robot.driveTrain.getVoltage() < Constants.IRWallDistanceVoltage) {
                    Robot.driveTrain.StopMotors();
                }
            }
            else {
                setState(Driving);
            }
                break;
            case Stopped:
                Robot.driveTrain.StopMotors();
                break;
            case SandStorm:
                Robot.driveTrain.MecanumDrive(CSVRead.AUTOY, CSVRead.AUTOX, CSVRead.AUTOZ, CSVRead.AUTOGYROANGLE, .5);
                
                if (Controls.activateVision()) {
                    setState(Seeking);
                }
                else {
                    setState(Driving);
                }
                break;
        }
    }

}