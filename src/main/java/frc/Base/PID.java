package frc.Base;

public class PID {

    private double P, I, D = 0;
    private double  error, integral, derivative = 0;
    public static double output = 0;

    public void PID(double setPoint, double reading) {
        error = setPoint - reading;
        integral += (error*.02);
        derivative = (error - integral) / .02;
        output = (P*error) + (I*integral) + (D*derivative);
    }
}
