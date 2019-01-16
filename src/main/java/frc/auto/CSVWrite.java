package frc.auto;

import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants;
import frc.robot.Controls;
import frc.robot.Robot;

public class CSVWrite {

    public static void writeCSV(String FileName) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(FileName);
            fileWriter.append(Constants.FILEHEADER);
            fileWriter.append(Constants.NEWLINE);

            while(!DriverStation.getInstance().isAutonomous()) {
                fileWriter.append((char)Controls.getX());
                fileWriter.append(Constants.COMMA);
                fileWriter.append((char)Controls.getY());
                fileWriter.append(Constants.COMMA);
                fileWriter.append((char)Controls.getZ());
                fileWriter.append(Constants.COMMA);
                fileWriter.append((char)Robot.driveTrain.getYaw());
                fileWriter.append(Constants.NEWLINE);
            }
            System.out.println("CSV file was created successfully !!!");
        }
        catch(Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
        finally {
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
}