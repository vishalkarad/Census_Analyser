import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.imageio.ImageReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CensusAnalyserMain {

    private static final String csvFilepath="StateCensusData.csv";
    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // count number of records
    public Integer readFile() throws Exception{
        int count = 0;
        Reader reader = Files.newBufferedReader(Paths.get(csvFilepath));
        CSVReader csvReader = new CSVReader(reader);
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            count++;
        }
        return count;
    }
}
