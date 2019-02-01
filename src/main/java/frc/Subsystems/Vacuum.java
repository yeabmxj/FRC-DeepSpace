package frc.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Robot;

public class Vacuum {

    public Servo armHead;
    public Spark pump;

    int servochannel = 7;
    int pumpchannel = 7;
    double groundangle;

    public Vacuum() {
        armHead = new Servo(servochannel);
        pump = new Spark(pumpchannel);
    }
    public void succ(double succspeed) {
        pump.set(succspeed);
    }
    public void setAngle(double tiltswitch) {
        double angle = tiltswitch / 360;
        armHead.set(angle);
    }
    public void faceround() {
        armHead.set(groundangle);
    }
}
