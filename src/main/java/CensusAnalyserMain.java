import com.bridgelabz.CensusAnalyser.CensusAnalyserException;
import com.bridgelabz.CensusAnalyser.IndianStateCensusData;
import com.bridgelabz.CensusAnalyser.StateCodePOJO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyserMain {


    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public Integer readFile(String filePath) throws Exception {
       try (
           Reader reader = Files.newBufferedReader(Paths.get(filePath));
       ) {
            Iterator<IndianStateCensusData> stateCSVIterator = this.getCSVfileIterator(reader,IndianStateCensusData.class);
            Iterable<IndianStateCensusData> csvIterable = () -> stateCSVIterator;
            int numOfRecords = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
            int numOfrecords;
            return numOfRecords;

       }catch (NoSuchFileException e){
           throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
       }
       catch (RuntimeException e){
           throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
       }
    }
    // Read state code csv file
    public Integer loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            Iterator<StateCodePOJO> statesCSVIterator = this.getCSVfileIterator(reader,StateCodePOJO.class);
            Iterable<StateCodePOJO> iterableStateCode = ()-> statesCSVIterator;
            int countRecord = (int) StreamSupport.stream(iterableStateCode.spliterator(),false).count();
            return countRecord;
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
    // Open CSV
    private <E> Iterator<E> getCSVfileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        }catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        }catch(Exception e){
            e.printStackTrace();
        }
        return(null);
    }
}