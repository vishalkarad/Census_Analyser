import com.bridgelabz.CensusAnalyser.IndianStateCensusData;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;


class CensusAnalyserException extends Exception {
    enum MyException_Type{
        FILE_NOT_FOUND,DELIMITER_INCORECT;
    }
    MyException_Type type;
    CensusAnalyserException(MyException_Type type,String message) {
        super(message);
        this.type=type;
        System.out.println(message);
    }
}
public class CensusAnalyserMain {


    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // count number of records
    public Integer readFile(String filePath) throws Exception {
        int count = 0;
       try (
           Reader reader = Files.newBufferedReader(Paths.get(filePath));
       ) {
            CsvToBean<IndianStateCensusData> csvToBean= new CsvToBeanBuilder(reader)
                    .withType(IndianStateCensusData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianStateCensusData> stateIterator = csvToBean.iterator();

            while (stateIterator.hasNext()){
                IndianStateCensusData indianStateCensusData = stateIterator.next();
                System.out.println("State : "+indianStateCensusData.getState());
                System.out.println("population: "+indianStateCensusData.getPopulation());
                System.out.println("areaInSqKm : "+indianStateCensusData.getAreaInSqKm());
                System.out.println("densityPerSqKm : "+indianStateCensusData.getDensityPerSqKm());
                System.out.println("======================================");
                count++;
            }
       }catch (NoSuchFileException e){
           throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
       }
       catch (RuntimeException e){
           throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr");
       }
        return count;
    }
}

