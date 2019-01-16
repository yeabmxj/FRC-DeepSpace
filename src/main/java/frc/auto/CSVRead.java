package frc.auto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import frc.robot.Constants;

public class CSVRead{

    public static double AUTOX = 0;
    public static double AUTOY = 0;
    public static double AUTOZ = 0;
    public static double AUTOGYROANGLE = 0;

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
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally {
            try {
                fileReader.close();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
