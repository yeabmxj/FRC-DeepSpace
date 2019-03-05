package frc.Base;

public class MotionCalculation {

    public static double error = 0;

    private static double normalizedValue = 0;
    public static String s = "";
    public static void setSystem(String system) { s = system;}
    public static String getSystem() { return s;}

    public static boolean finished = false;

    public static double normalize(double target,double offset, double reading, double tolerance) {
        /*
        * the value is the error
        * the minimum value is the reading
        * the max value is the target
        */
        error = (target - offset) - (reading);
        if (getSystem().equals("Arm")) {
            normalizedValue = ((target - offset) - (reading)) / ((target - offset) - reading);
        }
        else if (getSystem().equals("Auto")) {
            normalizedValue = ((target - offset) - (reading)) / (target - offset);}
        finished = Math.abs(error) < tolerance;
        System.out.println(error);
        return normalizedValue;
    }

    public static double getError() {
        return error;
    }

    public static boolean isFinished() {
        return finished;
    }
}
