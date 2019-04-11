package frc.External;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import frc.Base.Constants;

public class e_Navx {
    private AHRS navx;
    public e_Navx() {
        navx = new AHRS(SPI.Port.kMXP);
    }
    public boolean getAvailability() { return navx.isConnected();}
    public double getRoll() { return navx.getRoll();}
    public double getPitch() { return  navx.getPitch();}
    public double getYaw() { return navx.getYaw();}
    public void resetGyro() { navx.reset();}
}
