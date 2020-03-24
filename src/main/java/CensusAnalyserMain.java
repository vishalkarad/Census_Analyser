import com.bridgelabz.CensusAnalyser.*;
import com.google.gson.Gson;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyserMain {

    OpenCSV openCSV = new OpenCSV();

    public static void main(String[] args)  {
        System.out.println("******************** Welcome to Census Analyseer ********************");
    }
    // Read CSV file
    public Integer readFile(String filePath) throws Exception {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            //CSV_Interface csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndianStateCensusData> listCSVfile = openCSV.geCSVfileList(reader,IndianStateCensusData.class);
            return listCSVfile.size();

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
           // CSV_Interface csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<StateCodePOJO> listCSVfile = openCSV.geCSVfileList(reader,StateCodePOJO.class);
            return listCSVfile.size();
        } catch (NoSuchFileException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.FILE_NOT_FOUND,"Enter a right file name and type");
        } catch (RuntimeException e){
            throw new CensusAnalyserException(CensusAnalyserException.MyException_Type.DELIMITER_INCORECT,"Check delimetr and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (null);
    }
}