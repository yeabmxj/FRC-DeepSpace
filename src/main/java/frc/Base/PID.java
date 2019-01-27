package frc.Base;

public class PID {

    private static double P, I, D = 0;
    public static double  error;
    private static double integral;
    private static double derivative = 0;
    public static double output = 0;

    public static void getPID(double setPoint, double reading) {
        error = setPoint - reading;
        integral += (error*.02);
        derivative = (error - integral) / .02;
        output = (P*error) + (I*integral) + (D*derivative);
    }
}
