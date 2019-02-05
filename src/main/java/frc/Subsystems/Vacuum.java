package frc.Subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;

public class Vacuum {

    public Servo armHead;
    public Spark pump;

    int servochannel = 7;
    int pumpchannel = 7;
    double groundangle = 10;

    public Vacuum() {
        armHead = new Servo(servochannel);
        pump = new Spark(pumpchannel);
    }
    public void succ(double succspeed) {
        pump.set(succspeed);
    }
    public void setAngle(double angle) {
        armHead.set(angle);
    }
    public void faceground() {
        armHead.set(groundangle);
    }
}
