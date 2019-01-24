package frc.auto.CSV;

import frc.Base.Constants;
import frc.Base.Controls;
import frc.robot.Robot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSV {

    public static double AUTOX;
    public static double AUTOY;
    public static double AUTOZ;
    public static double AUTOGYROANGLE;
    public static double AUTOTHROTTLE;

    public static int count = 0;

    public static void readCSV(String fileName) {
        BufferedReader fileReader = null;

        try {
            String line = "";

            fileReader = new BufferedReader(new FileReader(fileName));
            fileReader.readLine();

            while((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(Constants.COMMA);
                if (tokens.length > 0) {
                    AUTOX = (double)(Long.parseLong(tokens[Constants.XVALUEINDEXID]));
                    AUTOY = (double)Long.parseLong(tokens[Constants.YVALUEINDEXID]);
                    AUTOZ = (double)Long.parseLong(tokens[Constants.ZVALUEINDEXID]);
                    AUTOGYROANGLE = (double)Long.parseLong(tokens[Constants.GYROANGLEVALUEINDEXID]);
                    AUTOTHROTTLE = (double)Long.parseLong(tokens[Constants.THROTTLEVALUEINDEXID]);
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        try {
            fileReader.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCSV(String FileName) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(FileName);

            count++;

            if (count < 2) {
                fileWriter.append(Constants.FILEHEADER);
                fileWriter.append(Constants.NEWLINE);
            }

            fileWriter.append((char) Controls.getX());
            fileWriter.append(Constants.COMMA);
            fileWriter.append((char)Controls.getY());
            fileWriter.append(Constants.COMMA);
            fileWriter.append((char)Controls.getZ());
            fileWriter.append(Constants.COMMA);
            fileWriter.append((char)Robot.driveTrain.getYaw());
            fileWriter.append(Constants.COMMA);
            fileWriter.append((char)Robot.driveTrain.Throttle(Controls.joystick.getRawAxis(3), Controls.joystick.getRawAxis(2)));
            fileWriter.append(Constants.NEWLINE);
        }
        catch(Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        try {
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter !!!");
            e.printStackTrace();
        }
    }
}
