package frc.Base;

public class MotionCalculation {

    public static double error = 0;
    private static double normalizedValue = 0;

    private static boolean finished = false;

    public static double normalize(double target, double reading) {
        /*
        * the value is the error
        * the minimum value is the reading
        * the max value is the target
        */
        error = target - reading;
        normalizedValue = ((error - reading) / (target - reading));
        return normalizedValue;
    }

    public static double getError() { return error;}

    public static boolean isFinished(double tolerance) {
        finished = (error <= Math.abs(tolerance));
        return finished;
    }
}
