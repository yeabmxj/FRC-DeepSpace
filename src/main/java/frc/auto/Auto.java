package frc.auto;

import frc.Base.Constants;
import frc.Base.State;
import frc.Base.PID;
import frc.StateMachines.Drive;
import frc.robot.Robot;

public class Auto extends State {
    public static final int ReachLine = 0;
    public static final int Spin = 1;
    public static final int SpinAlign = 2;
    public static final int Approach = 3;
    public static final int Realign = 4;
    public static final int GetinRange = 5;

    double distancetoRPline = 10;
    double distanceTolerance = .2;
    double turnTolerance = .2;
    double irDistance = 200;

    public void update() {
        switch (state) {
            case ReachLine:
                PID.getPID(distancetoRPline, Robot.driveTrain.getEncodervalues());
                Robot.driveTrain.TankDrive(PID.output, 0, .5);
                setState(PID.error <= distanceTolerance ? Spin : ReachLine);
                break;
            case Spin:
                if (Robot.limeLightVision.getTarget() == 0) { Robot.driveTrain.TankDrive(0,.5,0);
                setState(Spin);}
                else if (Robot.limeLightVision.getTarget() == 1) { setState(SpinAlign);}
                break;
            case SpinAlign:
                Robot.driveTrain.resetGyro();
                PID.getPID(Robot.limeLightVision.getX(), Robot.driveTrain.navx.getDisplacementX());
                Robot.driveTrain.TankDrive(0,PID.output,.5);
                setState(PID.error <= turnTolerance ? Approach : SpinAlign);
                break;
            case Approach:
                Robot.driveTrain.resetEncoders();
                PID.getPID(irDistance, Robot.driveTrain.getEncodervalues());
                Robot.driveTrain.TankDrive(PID.output,0,.5);
                setState(PID.error <= distanceTolerance ? Realign : Approach);
                break;
            case Realign:
                Robot.driveTrain.resetGyro();
                double dtoalign = Robot.driveTrain.distanceToWallSensor.getVoltage() * Math.sin(Robot.limeLightVision.getX());
                double turnAngle = 90 - Math.sin(Robot.limeLightVision.getX());
                PID.getPID(turnAngle , Robot.driveTrain.navx.getDisplacementX());
                Robot.driveTrain.TankDrive(0,PID.output,.5);
                if (PID.error <= turnTolerance) {
                    Robot.driveTrain.resetEncoders();
                    PID.getPID(dtoalign, Robot.driveTrain.getEncodervalues());
                    Robot.driveTrain.TankDrive(PID.output,0,.5);
                    if (PID.error <= distanceTolerance) {
                        Robot.driveTrain.resetGyro();
                        PID.getPID(90, Robot.driveTrain.navx.getDisplacementX());
                        Robot.driveTrain.TankDrive(0,PID.output,.5);
                        setState(PID.error <= turnTolerance ? GetinRange : Realign);
                    }
                }
                break;
            case GetinRange:
                Robot.driveTrain.resetEncoders();
                PID.getPID(Constants.IR_WALL_DISTANCE_VOLTAGE, Robot.driveTrain.distanceToWallSensor.getVoltage());
                Robot.driveTrain.TankDrive(PID.error,0,.5);
                if (PID.error <= distanceTolerance) {
                    setState(Drive.Input);
                }
                break;
        }
    }
}
