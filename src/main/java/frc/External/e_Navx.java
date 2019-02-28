package frc.External;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import frc.Base.Constants;

public class e_Navx {
    private AHRS navx;
    public e_Navx() {
        navx = new AHRS(SerialPort.Port.kUSB);
    }
    public boolean getAvailability() { return navx.isConnected();}
    public double getRoll() { return navx.getRoll();}
    public double getPitch() { return  navx.getPitch();}
    public double getYaw() { return navx.getYaw();}
    public void resetGyro() { navx.reset();}
}
