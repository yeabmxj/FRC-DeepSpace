package frc.Base;

public class MotionCalculation {

    public static double error = 0;
    private static double normalizedValue = 0;

    private static boolean finished = false;

    public static double normalize(double target, double reading, double value) {
        /*
        * the value is the error
        * the minimum value is the reading
        * the max value is the target
        */
        normalizedValue = ((value - reading) / (target - reading));
        return normalizedValue;
    }

    public static double getRate(double target, double reading) {
        error = target - reading;
        return error;
    }

    public static boolean isFinished(double tolerance) {
        finished = (error <= Math.abs(tolerance));
        return finished;
    }
}
